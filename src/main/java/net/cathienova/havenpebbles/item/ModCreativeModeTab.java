package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavenPebbles.MODID);

    public static final RegistryObject<CreativeModeTab> HAVENPEBBLES_TAB = CREATIVE_MODE_TABS.register("havenpebbles_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.netherrack_pebble.get().asItem()))
                    .title(Component.translatable("itemGroup.havenpebbles"))
                    .displayItems(((pParameters, pOutput) ->
                    {
                        pOutput.accept(ModItems.tuff_pebble.get());
                        pOutput.accept(ModItems.stone_pebble.get());
                        pOutput.accept(ModItems.netherrack_pebble.get());
                        pOutput.accept(ModItems.dripstone_pebble.get());
                        pOutput.accept(ModItems.diorite_pebble.get());
                        pOutput.accept(ModItems.deepslate_pebble.get());
                        pOutput.accept(ModItems.calcite_pebble.get());
                        pOutput.accept(ModItems.blackstone_pebble.get());
                        pOutput.accept(ModItems.basalt_pebble.get());
                        pOutput.accept(ModItems.andesite_pebble.get());
                    })).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}