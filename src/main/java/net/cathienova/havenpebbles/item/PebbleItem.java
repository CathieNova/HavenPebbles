package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PebbleItem extends Item {
    public PebbleItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 16;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        Item item = stack.getItem();
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide()) {
            HavenPebblesConfig.getPebbleEffects(item).apply(entity, level.getRandom());
        }

        return result;
    }
}