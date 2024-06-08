package net.cathienova.havenpebbles.item;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HavenPebbles.MODID);

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> andesite_pebble = ITEMS.register("andesite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> basalt_pebble = ITEMS.register("basalt_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> blackstone_pebble = ITEMS.register("blackstone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> calcite_pebble = ITEMS.register("calcite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> deepslate_pebble = ITEMS.register("deepslate_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> diorite_pebble = ITEMS.register("diorite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> dripstone_pebble = ITEMS.register("dripstone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> granite_pebble = ITEMS.register("granite_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> netherrack_pebble = ITEMS.register("netherrack_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> tuff_pebble = ITEMS.register("tuff_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
    public static final RegistryObject<Item> stone_pebble = ITEMS.register("stone_pebble",
            () -> new PebbleItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast()
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 140), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100), 1.0f).build())));
}