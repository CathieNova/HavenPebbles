package net.cathienova.havenpebbles.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PebbleItem extends Item {
    public PebbleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // Get the nutrition and saturation values from the item's food properties
        String grayText = ChatFormatting.GRAY + "Eat me if you dare...";

        // Convert the gray text string to a Component
        Component grayComponent = Component.nullToEmpty(grayText);

        // Add the gray text Component to the tooltip
        tooltip.add(grayComponent);
    }
}