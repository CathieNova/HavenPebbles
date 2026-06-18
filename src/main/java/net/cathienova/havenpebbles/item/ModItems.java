package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HavenPebbles.MODID);
    private static final FoodProperties PEBBLE_FOOD = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build();
    private static final ItemLore PEBBLE_LORE = new ItemLore(List.of(
            Component.translatable("item.havenpebbles.pebble.tooltip")
                    .withStyle(ChatFormatting.GRAY)
                    .withStyle(style -> style.withItalic(false))
    ));

    public static final DeferredItem<Item> andesite_pebble = ITEMS.<Item>registerItem("andesite_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> basalt_pebble = ITEMS.<Item>registerItem("basalt_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> blackstone_pebble = ITEMS.<Item>registerItem("blackstone_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> calcite_pebble = ITEMS.<Item>registerItem("calcite_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> deepslate_pebble = ITEMS.<Item>registerItem("deepslate_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> diorite_pebble = ITEMS.<Item>registerItem("diorite_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> dripstone_pebble = ITEMS.<Item>registerItem("dripstone_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> granite_pebble = ITEMS.<Item>registerItem("granite_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> netherrack_pebble = ITEMS.<Item>registerItem("netherrack_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> tuff_pebble = ITEMS.<Item>registerItem("tuff_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));
    public static final DeferredItem<Item> stone_pebble = ITEMS.<Item>registerItem("stone_pebble", PebbleItem::new,
            properties -> properties.food(PEBBLE_FOOD).component(DataComponents.LORE, PEBBLE_LORE));

    public static final List<DeferredItem<Item>> PEBBLES = List.of(
            andesite_pebble,
            basalt_pebble,
            blackstone_pebble,
            calcite_pebble,
            deepslate_pebble,
            diorite_pebble,
            dripstone_pebble,
            granite_pebble,
            netherrack_pebble,
            stone_pebble,
            tuff_pebble
    );
}