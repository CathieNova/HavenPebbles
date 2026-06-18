package net.cathienova.havenpebbles.datagen;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Consumer;

public class ModAdvancements implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver) {
        AdvancementHolder obtainPebble = Advancement.Builder.advancement()
                .display(
                        ModItems.stone_pebble.get(),
                        Component.translatable("advancements.havenpebbles.pebble_in_your_pocket.title"),
                        Component.translatable("advancements.havenpebbles.pebble_in_your_pocket.description"),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/stone"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_pebble", InventoryChangeTrigger.TriggerInstance.hasItems(pebblePredicate(registries)))
                .save(saver, Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "pebble_in_your_pocket"));

        Advancement.Builder.advancement()
                .parent(obtainPebble)
                .display(
                        ModItems.stone_pebble.get(),
                        Component.translatable("advancements.havenpebbles.forbidden_cookie.title"),
                        Component.translatable("advancements.havenpebbles.forbidden_cookie.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("eat_pebble", ConsumeItemTrigger.TriggerInstance.usedItem(pebblePredicate(registries)))
                .save(saver, Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "forbidden_cookie"));
    }

    private static ItemPredicate.Builder pebblePredicate(HolderLookup.Provider registries) {
        return ItemPredicate.Builder.item().of(
                registries.lookupOrThrow(Registries.ITEM),
                ModItems.andesite_pebble.get(),
                ModItems.basalt_pebble.get(),
                ModItems.blackstone_pebble.get(),
                ModItems.calcite_pebble.get(),
                ModItems.deepslate_pebble.get(),
                ModItems.diorite_pebble.get(),
                ModItems.dripstone_pebble.get(),
                ModItems.granite_pebble.get(),
                ModItems.netherrack_pebble.get(),
                ModItems.stone_pebble.get(),
                ModItems.tuff_pebble.get()
        );
    }
}