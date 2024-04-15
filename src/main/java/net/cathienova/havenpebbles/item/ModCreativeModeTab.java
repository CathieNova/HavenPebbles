package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab HAVENUTILS_TAB = new CreativeModeTab(HavenPebbles.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.netherrack_pebble.get());
        }
    };
}
