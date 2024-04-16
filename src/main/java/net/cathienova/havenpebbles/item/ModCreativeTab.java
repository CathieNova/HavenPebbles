package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTab
{
    public static String havenpebbles_tab_title = "creativetab.havenpebbles";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavenPebbles.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HAVENPEBBLES_TAB = CREATIVE_MODE_TABS.register("havenpebbles_tab", () ->
    {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();
        builder.displayItems((itemDisplayParameters, add) ->
        {
            add.accept(ModItems.tuff_pebble.get());
            add.accept(ModItems.stone_pebble.get());
            add.accept(ModItems.netherrack_pebble.get());
            add.accept(ModItems.dripstone_pebble.get());
            add.accept(ModItems.diorite_pebble.get());
            add.accept(ModItems.deepslate_pebble.get());
            add.accept(ModItems.calcite_pebble.get());
            add.accept(ModItems.blackstone_pebble.get());
            add.accept(ModItems.basalt_pebble.get());
            add.accept(ModItems.andesite_pebble.get());
        });

        builder.icon(() -> new ItemStack(ModItems.stone_pebble.get()));
        builder.title(Component.translatable(havenpebbles_tab_title));

        return builder.build();
    });
}