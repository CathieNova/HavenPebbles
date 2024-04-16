package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HavenPebbles.MODID);

    public static final DeferredItem<Item> andesite_pebble = ITEMS.register("andesite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> basalt_pebble = ITEMS.register("basalt_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> blackstone_pebble = ITEMS.register("blackstone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> calcite_pebble = ITEMS.register("calcite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> deepslate_pebble = ITEMS.register("deepslate_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> diorite_pebble = ITEMS.register("diorite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> dripstone_pebble = ITEMS.register("dripstone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> granite_pebble = ITEMS.register("granite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> netherrack_pebble = ITEMS.register("netherrack_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> tuff_pebble = ITEMS.register("tuff_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final DeferredItem<Item> stone_pebble = ITEMS.register("stone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
}