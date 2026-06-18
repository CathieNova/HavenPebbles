package net.cathienova.havenpebbles.datagen;

import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEngLangProvider extends LanguageProvider {
    public ModEngLangProvider(PackOutput output) {
        super(output, HavenPebbles.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.andesite_pebble.get(), "Andesite Pebble");
        add(ModItems.basalt_pebble.get(), "Basalt Pebble");
        add(ModItems.blackstone_pebble.get(), "Blackstone Pebble");
        add(ModItems.calcite_pebble.get(), "Calcite Pebble");
        add(ModItems.deepslate_pebble.get(), "Deepslate Pebble");
        add(ModItems.diorite_pebble.get(), "Diorite Pebble");
        add(ModItems.dripstone_pebble.get(), "Dripstone Pebble");
        add(ModItems.granite_pebble.get(), "Granite Pebble");
        add(ModItems.netherrack_pebble.get(), "Netherrack Pebble");
        add(ModItems.stone_pebble.get(), "Stone Pebble");
        add(ModItems.tuff_pebble.get(), "Tuff Pebble");
        add("item.havenpebbles.pebble.tooltip", "Eat me if you dare...");
        add("creativetab.havenpebbles", "§5Haven §2Pebbles");
        add("advancements.havenpebbles.pebble_in_your_pocket.title", "Pocket Sand!");
        add("advancements.havenpebbles.pebble_in_your_pocket.description", "You found an emotional support rock.");
        add("advancements.havenpebbles.forbidden_cookie.title", "Forbidden Cookie");
        add("advancements.havenpebbles.forbidden_cookie.description", "Crunchy on the outside. Also crunchy on the inside.");
        add("jei.havenpebbles.gathering", "Pebble Gathering");
        add("jei.havenpebbles.dimension", "Dimension: %s");
        add("jei.havenpebbles.any_dimension", "Any Dimension");
        add("jei.havenpebbles.action_line_one", "Crouch Right Click");
        add("jei.havenpebbles.action_line_two", "with an empty main hand");
        add("jei.havenpebbles.chance", "Chance: %s%%");
        add("jei.havenpebbles.effects.none", "Eating this pebble will not give you any effects.");
        add("jei.havenpebbles.effects.all", "Eating this pebble will give you:");
        add("jei.havenpebbles.effects.random", "Eating this pebble will randomly give you:");
        add("jei.havenpebbles.effects.entry_all", "%s %s - %ss");
        add("jei.havenpebbles.effects.entry_random", "%s %s - %ss (%s%% chance)");
    }
}