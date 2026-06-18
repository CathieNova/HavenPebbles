package net.cathienova.havenpebbles.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

public class DataGenerators {
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        event.addProvider(new ModItemModels(output));
        event.addProvider(new ModEngLangProvider(output));
        event.addProvider(new ModRecipes.Generation(output, event.getLookupProvider()));
        event.addProvider(new ModItemTags(output, event.getLookupProvider()));
        event.addProvider(new AdvancementProvider(output, event.getLookupProvider(), List.of(new ModAdvancements())));
    }
}