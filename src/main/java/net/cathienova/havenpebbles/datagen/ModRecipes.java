package net.cathienova.havenpebbles.datagen;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        pebbleRecipe(ModItems.andesite_pebble.get(), Blocks.ANDESITE, "pebble_to_andesite");
        pebbleRecipe(ModItems.basalt_pebble.get(), Blocks.BASALT, "pebble_to_basalt");
        pebbleRecipe(ModItems.blackstone_pebble.get(), Blocks.BLACKSTONE, "pebble_to_blackstone");
        pebbleRecipe(ModItems.calcite_pebble.get(), Blocks.CALCITE, "pebble_to_calcite");
        pebbleRecipe(ModItems.stone_pebble.get(), Blocks.COBBLESTONE, "pebble_to_cobblestone");
        pebbleRecipe(ModItems.deepslate_pebble.get(), Blocks.COBBLED_DEEPSLATE, "pebble_to_deepslate");
        pebbleRecipe(ModItems.diorite_pebble.get(), Blocks.DIORITE, "pebble_to_diorite");
        pebbleRecipe(ModItems.dripstone_pebble.get(), Items.POINTED_DRIPSTONE, "pebble_to_dripstone");
        pebbleRecipe(ModItems.granite_pebble.get(), Blocks.GRANITE, "pebble_to_granite");
        pebbleRecipe(ModItems.netherrack_pebble.get(), Blocks.NETHERRACK, "pebble_to_netherrack");
        pebbleRecipe(ModItems.tuff_pebble.get(), Blocks.TUFF, "pebble_to_tuff");
    }

    private void pebbleRecipe(ItemLike pebble, ItemLike result, String name) {
        shaped(RecipeCategory.MISC, result)
                .pattern("PP")
                .pattern("PP")
                .define('P', pebble)
                .unlockedBy("has_pebble", has(pebble))
                .save(output, recipeKey(name));
    }

    private static ResourceKey<Recipe<?>> recipeKey(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(HavenPebbles.MODID, path));
    }

    public static class Generation extends RecipeProvider.Runner {
        public Generation(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipes(registries, output);
        }

        @Override
        public String getName() {
            return "HavenPebbles Recipes";
        }
    }
}