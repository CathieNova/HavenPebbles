package net.cathienova.havenpebbles.interaction;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public record PebbleDrop(String dimension, Block block, List<Output> outputs) {
    public PebbleDrop {
        outputs = List.copyOf(outputs);
    }

    public boolean matches(Level level, Block clickedBlock) {
        return block == clickedBlock && (dimension.equals("*") || dimension.equals(level.dimension().identifier().toString()));
    }

    public ItemStack roll(RandomSource random) {
        int totalWeight = totalWeight();
        if (totalWeight <= 0) {
            return ItemStack.EMPTY;
        }

        int selectedWeight = random.nextInt(totalWeight);
        for (Output output : outputs) {
            selectedWeight -= output.weight();
            if (selectedWeight < 0) {
                return new ItemStack(output.item(), output.count());
            }
        }

        return ItemStack.EMPTY;
    }

    public int totalWeight() {
        return outputs.stream().mapToInt(Output::weight).sum();
    }

    public double effectiveChance(Output output) {
        int totalWeight = totalWeight();
        return totalWeight <= 0 ? 0.0D : 100.0D * output.weight() / totalWeight;
    }

    public record Output(Item item, int weight, int count) {
    }
}