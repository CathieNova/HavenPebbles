package net.cathienova.havenpebbles.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.compat.PebbleGatheringRecipe;
import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.cathienova.havenpebbles.effect.PebbleEffectSet;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@JeiPlugin
public class HavenPebblesJei implements IModPlugin {
    private static final Identifier PLUGIN_ID = Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "jei");

    @Override
    public Identifier getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PebbleGatheringCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
                PebbleGatheringCategory.TYPE,
                PebbleGatheringRecipe.fromConfig(HavenPebblesConfig.getInteractionDrops())
        );

        for (DeferredItem<Item> pebble : ModItems.PEBBLES) {
            Item item = pebble.get();
            List<Component> information = effectInformation(HavenPebblesConfig.getPebbleEffects(item));
            registration.addItemStackInfo(
                    List.of(new ItemStack(item)),
                    information.toArray(Component[]::new)
            );
        }
    }

    private static List<Component> effectInformation(PebbleEffectSet effectSet) {
        List<Component> information = new ArrayList<>();

        if (effectSet.effects().isEmpty()) {
            information.add(Component.translatable("jei.havenpebbles.effects.none"));
            return information;
        }

        information.add(Component.translatable(
                effectSet.selection() == PebbleEffectSet.Selection.RANDOM
                        ? "jei.havenpebbles.effects.random"
                        : "jei.havenpebbles.effects.all"
        ));

        for (PebbleEffectSet.Effect effect : effectSet.effects()) {
            String level = romanNumeral(effect.amplifier() + 1);

            if (effectSet.selection() == PebbleEffectSet.Selection.RANDOM) {
                information.add(Component.translatable(
                        "jei.havenpebbles.effects.entry_random",
                        Component.translatable(effect.effect().getDescriptionId()),
                        level,
                        formatSeconds(effect.duration()),
                        formatPercent(effect.chance())
                ));
            } else {
                information.add(Component.translatable(
                        "jei.havenpebbles.effects.entry_all",
                        Component.translatable(effect.effect().getDescriptionId()),
                        level,
                        formatSeconds(effect.duration())
                ));
            }
        }

        return information;
    }

    private static String formatSeconds(int ticks) {
        double seconds = ticks / 20.0D;
        if (Math.abs(seconds - Math.rint(seconds)) < 0.0001D) {
            return String.format(Locale.ROOT, "%.0f", seconds);
        }
        return String.format(Locale.ROOT, "%.2f", seconds);
    }

    private static String formatPercent(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.0001D) {
            return String.format(Locale.ROOT, "%.0f", value);
        }
        return String.format(Locale.ROOT, "%.2f", value);
    }

    private static String romanNumeral(int value) {
        if (value < 1 || value > 3999) {
            return Integer.toString(value);
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        int remaining = value;

        for (int i = 0; i < values.length; i++) {
            while (remaining >= values[i]) {
                result.append(numerals[i]);
                remaining -= values[i];
            }
        }

        return result.toString();
    }
}