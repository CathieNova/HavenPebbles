package net.cathienova.havenpebbles.datagen;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends ItemTagsProvider {
    public static final TagKey<Item> PEBBLES = ItemTags.create(Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "pebbles"));

    public ModItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HavenPebbles.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PEBBLES)
                .add(ModItems.andesite_pebble.get())
                .add(ModItems.basalt_pebble.get())
                .add(ModItems.blackstone_pebble.get())
                .add(ModItems.calcite_pebble.get())
                .add(ModItems.deepslate_pebble.get())
                .add(ModItems.diorite_pebble.get())
                .add(ModItems.dripstone_pebble.get())
                .add(ModItems.granite_pebble.get())
                .add(ModItems.netherrack_pebble.get())
                .add(ModItems.stone_pebble.get())
                .add(ModItems.tuff_pebble.get());
    }
}