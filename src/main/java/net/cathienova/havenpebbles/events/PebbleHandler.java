package net.cathienova.havenpebbles.events;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.*;

@EventBusSubscriber
public class PebbleHandler {
    private static final Map<Block, List<WeightedPebble>> blockPebbleMapping = new HashMap<>();
    private static boolean spawnPebble = true;

    public static void loadMappings() {
        blockPebbleMapping.clear();
        HavenPebblesConfig.blockPebbleMappings.forEach(mapping -> {
            String[] parts = mapping.split(";");
            if (parts.length == 2) {
                String blockName = parts[0];
                String[] pebbles = parts[1].split(",");
                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(blockName));
                if (block != null) {
                    List<WeightedPebble> weightedPebbles = new ArrayList<>();
                    for (String pebbleData : pebbles) {
                        String[] pebbleParts = pebbleData.split(":");
                        if (pebbleParts.length == 2 || pebbleParts.length == 3) {
                            String itemName = pebbleParts[0] + ":" + pebbleParts[1];
                            int weight = Integer.parseInt(pebbleParts[2]);
                            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(itemName));
                            if (item != null) {
                                weightedPebbles.add(new WeightedPebble(item, weight));
                            }
                        }
                    }
                    blockPebbleMapping.put(block, weightedPebbles);
                }
            }
        });
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!HavenPebblesConfig.enablePebbles) return;

        // Ensure only the main hand triggers the logic
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        if (event.getLevel().isClientSide()) {
            HavenPebbles.LOGGER.info("Event ignored on client side.");
            return;
        }

        Player player = event.getEntity();
        if (player.isCrouching() && player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            Level world = event.getLevel();
            BlockPos blockPos = event.getPos().above();
            Block block = world.getBlockState(blockPos.below()).getBlock();

            List<WeightedPebble> weightedPebbles = blockPebbleMapping.get(block);
            // HavenPebbles.LOGGER.info("Player crouching and right-clicked block: " + block);
            // HavenPebbles.LOGGER.info("Pebbles for block: " + weightedPebbles);

            if (weightedPebbles == null || weightedPebbles.isEmpty()) return;

            int totalWeight = weightedPebbles.stream().mapToInt(WeightedPebble::getWeight).sum();
            if (totalWeight <= 0) return;

            Random random = new Random();
            int randomWeight = random.nextInt(totalWeight);
            ItemStack selectedPebble = null;

            for (WeightedPebble pebble : weightedPebbles) {
                randomWeight -= pebble.getWeight();
                if (randomWeight < 0) {
                    selectedPebble = new ItemStack(pebble.getPebble());
                    break;
                }
            }

            if (selectedPebble != null) {
                double randomXOffset = 0.3 + (0.6 - 0.3) * random.nextDouble();
                double randomZOffset = 0.3 + (0.6 - 0.3) * random.nextDouble();

                double newX = blockPos.getX() + randomXOffset;
                double newY = blockPos.getY() - 0.25;
                double newZ = blockPos.getZ() + randomZOffset;

                ItemEntity itemEntity = new ItemEntity(world, newX, newY, newZ, selectedPebble);
                world.addFreshEntity(itemEntity);

                if (HavenPebblesConfig.emitPebbleSound) {
                    world.playSound(null, blockPos, SoundEvents.BEEHIVE_ENTER, SoundSource.PLAYERS, 0.75F, 0.75F);
                }
            }

            event.setUseBlock(TriState.FALSE);
            event.setUseItem(TriState.FALSE);
            event.setCanceled(true);
        }
    }

    private static class WeightedPebble {
        private final Item pebble;
        private final int weight;

        public WeightedPebble(Item pebble, int weight) {
            this.pebble = pebble;
            this.weight = weight;
        }

        public Item getPebble() {
            return pebble;
        }

        public int getWeight() {
            return weight;
        }
    }
}
