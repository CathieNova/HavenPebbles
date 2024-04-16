package net.cathienova.havenpebbles.events;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static net.cathienova.havenpebbles.config.CommonConfig.CONFIG;

@Mod.EventBusSubscriber(modid = HavenPebbles.MODID)
public class PebbleHandler
{
    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if (!CONFIG.enablePebbles.get())
            return;

        Player player = event.getEntity();
        if (event.getEntity().isCrouching() && player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
        {
            Level world = event.getLevel();
            BlockPos blockPos = event.getPos().above();
            Block block = world.getBlockState(blockPos.below()).getBlock();

            // Pebble items with their respective chances (weights)
            WeightedPebble[] pebbles = getWeightedPebbles(block, world);

            // Calculate total weight
            int totalWeight = 0;
            if (pebbles != null)
            {
                for (WeightedPebble weightedPebble : pebbles)
                {
                    totalWeight += weightedPebble.getWeight();
                }
            }

            // Randomly select a pebble based on its chance (weight)
            Random random = new Random();
            if (totalWeight <= 0)
            {
                return;
            }

            int randomWeight = random.nextInt(totalWeight);
            ItemStack selectedPebble = null;

            for (WeightedPebble weightedPebble : pebbles)
            {
                randomWeight -= weightedPebble.getWeight();
                if (randomWeight < 0)
                {
                    selectedPebble = new ItemStack(weightedPebble.getPebble());
                    break;
                }
            }

            // Spawn the selected pebble item
            if (selectedPebble != null)
            {
                Random randomV = new Random();
                double randomXOffset = 0.3 + (0.6 - 0.3) * randomV.nextDouble();
                double randomZOffset = 0.3 + (0.6 - 0.3) * randomV.nextDouble();

                double newX = blockPos.getX() + randomXOffset;
                double newY = blockPos.getY() - 0.25;  // Keeping the original Y value
                double newZ = blockPos.getZ() + randomZOffset;

                ItemEntity itemEntity = new ItemEntity(world, newX, newY, newZ, selectedPebble);

                world.addFreshEntity(itemEntity);

                if (CONFIG.emitPebbleSound.get())
                    world.playSound(null, blockPos, SoundEvents.BEEHIVE_ENTER, SoundSource.PLAYERS, 0.75F, 0.75F);
            }
            event.setUseItem(Event.Result.DENY);
            event.setCanceled(true);
        }
    }

    @NotNull
    private static WeightedPebble[] getWeightedPebbles(Block block, Level level)
    {
        if ((block == Blocks.DIRT || block == Blocks.GRASS_BLOCK))
        {
            if (CONFIG.onlyDimensionalPebbles.get() && !level.dimension().equals(Level.OVERWORLD))
            {
                return null;
            }

            return new WeightedPebble[]{
                    new WeightedPebble(ModItems.andesite_pebble.get(), CONFIG.andesitePebbleWeight.get()),
                    new WeightedPebble(ModItems.calcite_pebble.get(), CONFIG.calcitePebbleWeight.get()),
                    new WeightedPebble(ModItems.deepslate_pebble.get(), CONFIG.deepslatePebbleWeight.get()),
                    new WeightedPebble(ModItems.diorite_pebble.get(), CONFIG.dioritePebbleWeight.get()),
                    new WeightedPebble(ModItems.dripstone_pebble.get(), CONFIG.dripstonePebbleWeight.get()),
                    new WeightedPebble(ModItems.granite_pebble.get(), CONFIG.granitePebbleWeight.get()),
                    new WeightedPebble(ModItems.tuff_pebble.get(), CONFIG.tuffPebbleWeight.get()),
                    new WeightedPebble(ModItems.stone_pebble.get(), CONFIG.stonePebbleWeight.get())
            };
        }
        else if (block == Blocks.NETHERRACK)
        {
            if (CONFIG.onlyDimensionalPebbles.get() && !level.dimension().equals(Level.NETHER))
            {
                return null;
            }
            return new WeightedPebble[]{
                    new WeightedPebble(ModItems.netherrack_pebble.get(), CONFIG.netherrackPebbleWeight.get()),
                    new WeightedPebble(ModItems.basalt_pebble.get(), CONFIG.basaltPebbleWeight.get()),
                    new WeightedPebble(ModItems.blackstone_pebble.get(), CONFIG.blackstonePebbleWeight.get())
            };
        }
        else
        {
            return null;
        }
    }

    private static class WeightedPebble
    {
        private final Item pebble;
        private final int weight;

        public WeightedPebble(Item pebble, int weight)
        {
            this.pebble = pebble;
            this.weight = weight;
        }

        public Item getPebble()
        {
            return pebble;
        }

        public int getWeight()
        {
            return weight;
        }
    }
}
