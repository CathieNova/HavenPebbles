package net.cathienova.havenpebbles.datagen;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModItemModels extends ModelProvider {
    public ModItemModels(PackOutput output) {
        super(output, HavenPebbles.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.andesite_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.basalt_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.blackstone_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.calcite_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.deepslate_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.diorite_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.dripstone_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.granite_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.netherrack_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.stone_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
        itemModels.generateFlatItem(ModItems.tuff_pebble.get(), ModelTemplates.FLAT_ITEM.extend().build());
    }
}