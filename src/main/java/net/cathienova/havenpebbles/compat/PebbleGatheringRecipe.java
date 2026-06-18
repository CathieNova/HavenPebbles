package net.cathienova.havenpebbles.compat;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.interaction.PebbleDrop;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public record PebbleGatheringRecipe(Identifier id, Block block, ItemStack result, String dimension, double chance) {
    public static List<PebbleGatheringRecipe> fromConfig(List<PebbleDrop> drops) {
        List<PebbleGatheringRecipe> recipes = new ArrayList<>();
        int index = 0;

        for (PebbleDrop drop : drops) {
            for (PebbleDrop.Output output : drop.outputs()) {
                Identifier blockId = BuiltInRegistries.BLOCK.getKey(drop.block());
                Identifier itemId = BuiltInRegistries.ITEM.getKey(output.item());
                String signature = drop.dimension() + ";" + blockId + ";" + itemId + ";" + index;
                Identifier id = Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "gathering/" + Integer.toUnsignedString(signature.hashCode(), 16));
                recipes.add(new PebbleGatheringRecipe(id, drop.block(), new ItemStack(output.item(), output.count()), drop.dimension(), drop.effectiveChance(output)));
                index++;
            }
        }

        return List.copyOf(recipes);
    }
}