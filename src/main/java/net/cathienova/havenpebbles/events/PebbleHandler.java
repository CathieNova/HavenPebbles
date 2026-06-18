package net.cathienova.havenpebbles.events;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.cathienova.havenpebbles.interaction.PebbleDrop;
import net.cathienova.havenpebbles.network.PebbleSoundPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.util.TriState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = HavenPebbles.MODID)
public class PebbleHandler {
    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != InteractionHand.MAIN_HAND || event.getLevel().isClientSide()) {
            return;
        }

        Player player = event.getEntity();
        if (!player.isCrouching() || !player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            return;
        }

        Level level = event.getLevel();
        BlockPos blockPos = event.getPos();
        Block block = level.getBlockState(blockPos).getBlock();
        boolean matched = false;
        boolean gathered = false;

        for (PebbleDrop drop : HavenPebblesConfig.getInteractionDrops()) {
            if (!drop.matches(level, block)) {
                continue;
            }

            matched = true;
            ItemStack selectedItem = drop.roll(level.getRandom());
            if (selectedItem.isEmpty()) {
                continue;
            }

            double newX = blockPos.getX() + 0.3D + 0.3D * level.getRandom().nextDouble();
            double newY = blockPos.getY() + 0.75D;
            double newZ = blockPos.getZ() + 0.3D + 0.3D * level.getRandom().nextDouble();
            level.addFreshEntity(new ItemEntity(level, newX, newY, newZ, selectedItem));
            gathered = true;
        }

        if (!matched) {
            return;
        }

        if (gathered && level instanceof ServerLevel serverLevel) {
            PacketDistributor.sendToPlayersNear(
                    serverLevel,
                    null,
                    blockPos.getX() + 0.5D,
                    blockPos.getY() + 0.5D,
                    blockPos.getZ() + 0.5D,
                    16.0D,
                    new PebbleSoundPayload(blockPos)
            );
        }

        event.setUseBlock(TriState.FALSE);
        event.setUseItem(TriState.FALSE);
        event.setCanceled(true);
    }
}