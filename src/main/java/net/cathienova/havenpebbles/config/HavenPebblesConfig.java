package net.cathienova.havenpebbles.config;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.effect.PebbleEffectSet;
import net.cathienova.havenpebbles.interaction.PebbleDrop;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HavenPebblesConfig {
    public static boolean emitPebbleSound = true;
    private static List<PebbleDrop> interactionDrops = List.of();
    private static Map<Identifier, PebbleEffectSet> pebbleEffects = Map.of();

    public static void bakeClient() {
        emitPebbleSound = HavenPebbles.client_config.emitPebbleSound.get();
    }

    public static void bakeServer() {
        interactionDrops = parseInteractionDrops(HavenPebbles.server_config.gathering());
        pebbleEffects = parsePebbleEffects(
                parsePebbleEffectTypes(HavenPebbles.server_config.pebbleEffectTypes()),
                HavenPebbles.server_config.pebbleEffects()
        );
    }

    public static List<PebbleDrop> getInteractionDrops() {
        return interactionDrops;
    }

    public static PebbleEffectSet getPebbleEffects(Item item) {
        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        return pebbleEffects.getOrDefault(itemId, PebbleEffectSet.EMPTY);
    }

    private static List<PebbleDrop> parseInteractionDrops(Object configuredDrops) {
        List<PebbleDrop> parsedDrops = new ArrayList<>();

        if (configuredDrops instanceof UnmodifiableConfig configuredEntries) {
            for (UnmodifiableConfig.Entry entry : configuredEntries.entrySet()) {
                if (!(entry.getValue() instanceof UnmodifiableConfig configuredDrop)) {
                    HavenPebbles.LOGGER.warn("Ignoring invalid gathering entry '{}'", entry.getKey());
                    continue;
                }

                try {
                    parsedDrops.addAll(parseInteractionDrop(configuredDrop, entry.getKey()));
                } catch (RuntimeException exception) {
                    HavenPebbles.LOGGER.warn(
                            "Ignoring invalid gathering entry '{}': {}",
                            entry.getKey(),
                            exception.getMessage()
                    );
                }
            }
        } else if (configuredDrops instanceof List<?> legacyEntries) {
            for (int index = 0; index < legacyEntries.size(); index++) {
                Object value = legacyEntries.get(index);
                if (!(value instanceof UnmodifiableConfig configuredDrop)) {
                    HavenPebbles.LOGGER.warn("Ignoring invalid gathering entry {}", index + 1);
                    continue;
                }

                try {
                    parsedDrops.addAll(parseInteractionDrop(configuredDrop, Integer.toString(index + 1)));
                } catch (RuntimeException exception) {
                    HavenPebbles.LOGGER.warn(
                            "Ignoring invalid gathering entry {}: {}",
                            index + 1,
                            exception.getMessage()
                    );
                }
            }
        }

        return List.copyOf(parsedDrops);
    }

    private static List<PebbleDrop> parseInteractionDrop(UnmodifiableConfig configuredDrop, String entryName) {
        List<String> dimensions = readStringList(configuredDrop, "dimensions");
        List<String> blocks = readStringList(configuredDrop, "useBlocks");

        List<String> validDimensions = new ArrayList<>();
        for (String dimension : dimensions) {
            if (dimension.equals("*") || Identifier.tryParse(dimension) != null) {
                validDimensions.add(dimension);
            } else {
                HavenPebbles.LOGGER.warn("Ignoring invalid dimension '{}' in gathering entry '{}'", dimension, entryName);
            }
        }
        if (validDimensions.isEmpty()) {
            throw new IllegalArgumentException("at least one valid dimension is required");
        }

        List<Block> validBlocks = new ArrayList<>();
        for (String blockName : blocks) {
            Identifier blockId = Identifier.tryParse(blockName);
            if (blockId == null || !BuiltInRegistries.BLOCK.containsKey(blockId)) {
                HavenPebbles.LOGGER.warn("Ignoring unknown block '{}' in gathering entry '{}'", blockName, entryName);
                continue;
            }
            validBlocks.add(BuiltInRegistries.BLOCK.getValue(blockId));
        }
        if (validBlocks.isEmpty()) {
            throw new IllegalArgumentException("at least one valid block is required");
        }

        List<PebbleDrop.Output> outputs = parseOutputs(configuredDrop, entryName);
        if (outputs.isEmpty()) {
            throw new IllegalArgumentException("at least one valid output is required");
        }

        List<PebbleDrop> parsedDrops = new ArrayList<>();
        for (String dimension : validDimensions) {
            for (Block block : validBlocks) {
                parsedDrops.add(new PebbleDrop(dimension, block, outputs));
            }
        }
        return parsedDrops;
    }

    private static List<PebbleDrop.Output> parseOutputs(UnmodifiableConfig configuredDrop, String entryName) {
        Object configuredOutputs = configuredDrop.getRaw("outputs");
        if (!(configuredOutputs instanceof List<?> outputs)) {
            throw new IllegalArgumentException("outputs must be a list");
        }

        List<PebbleDrop.Output> parsedOutputs = new ArrayList<>();
        for (int outputIndex = 0; outputIndex < outputs.size(); outputIndex++) {
            Object configuredOutput = outputs.get(outputIndex);
            if (!(configuredOutput instanceof List<?> output)) {
                HavenPebbles.LOGGER.warn(
                        "Ignoring invalid output {} in gathering entry '{}'",
                        outputIndex + 1,
                        entryName
                );
                continue;
            }

            try {
                parsedOutputs.add(parseOutput(output));
            } catch (RuntimeException exception) {
                HavenPebbles.LOGGER.warn(
                        "Ignoring invalid output {} in gathering entry '{}': {}",
                        outputIndex + 1,
                        entryName,
                        exception.getMessage()
                );
            }
        }
        return parsedOutputs;
    }

    private static PebbleDrop.Output parseOutput(List<?> configuredOutput) {
        String itemName = readString(configuredOutput, 0, "item");
        Identifier itemId = Identifier.tryParse(itemName);
        if (itemId == null || !BuiltInRegistries.ITEM.containsKey(itemId)) {
            throw new IllegalArgumentException("unknown item '" + itemName + "'");
        }

        int weight = readInt(configuredOutput, 1, 1, "weight");
        int count = readInt(configuredOutput, 2, 1, "count");
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be greater than 0");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }

        return new PebbleDrop.Output(BuiltInRegistries.ITEM.getValue(itemId), weight, count);
    }

    private static Map<String, PebbleEffectSet> parsePebbleEffectTypes(UnmodifiableConfig configuredTypes) {
        Map<String, PebbleEffectSet> parsedTypes = new LinkedHashMap<>();

        for (UnmodifiableConfig.Entry entry : configuredTypes.entrySet()) {
            String typeName = entry.getKey().trim().toLowerCase(Locale.ROOT);
            if (!(entry.getValue() instanceof UnmodifiableConfig configuredType)) {
                HavenPebbles.LOGGER.warn("Ignoring invalid pebble effect type '{}'", entry.getKey());
                continue;
            }

            try {
                parsedTypes.put(typeName, parsePebbleEffectType(configuredType, entry.getKey()));
            } catch (RuntimeException exception) {
                HavenPebbles.LOGGER.warn(
                        "Ignoring invalid pebble effect type '{}': {}",
                        entry.getKey(),
                        exception.getMessage()
                );
            }
        }

        return Collections.unmodifiableMap(parsedTypes);
    }

    private static Map<Identifier, PebbleEffectSet> parsePebbleEffects(
            Map<String, PebbleEffectSet> effectTypes,
            Map<String, String> configuredPebbles
    ) {
        Map<Identifier, PebbleEffectSet> parsedEffects = new LinkedHashMap<>();
        PebbleEffectSet defaultEffects = effectTypes.getOrDefault("default", PebbleEffectSet.EMPTY);

        for (String pebbleId : ServerConfig.PEBBLE_IDS) {
            Identifier itemId = Identifier.fromNamespaceAndPath(HavenPebbles.MODID, pebbleId);
            String fallback = pebbleId.equals("blackstone_pebble") ? "random" : "default";
            String typeName = configuredPebbles.getOrDefault(pebbleId, fallback).trim().toLowerCase(Locale.ROOT);
            PebbleEffectSet configuredEffects;
            if (typeName.equals("none")) {
                configuredEffects = PebbleEffectSet.EMPTY;
            } else {
                configuredEffects = effectTypes.get(typeName);
                if (configuredEffects == null) {
                    HavenPebbles.LOGGER.warn(
                            "Unknown pebble effect type '{}' assigned to '{}'. Using default.",
                            typeName,
                            pebbleId
                    );
                    configuredEffects = defaultEffects;
                }
            }
            parsedEffects.put(itemId, configuredEffects);
        }

        return Collections.unmodifiableMap(parsedEffects);
    }

    private static PebbleEffectSet parsePebbleEffectType(UnmodifiableConfig configuredType, String typeName) {
        String selectionName = readString(configuredType, "type");
        PebbleEffectSet.Selection selection;
        if (selectionName.equalsIgnoreCase("random")) {
            selection = PebbleEffectSet.Selection.RANDOM;
        } else if (selectionName.equalsIgnoreCase("all")) {
            selection = PebbleEffectSet.Selection.ALL;
        } else {
            throw new IllegalArgumentException("type must be all or random");
        }

        Object configuredValue = configuredType.getRaw("effects");
        if (!(configuredValue instanceof List<?> configuredEffects)) {
            throw new IllegalArgumentException("effects must be a list");
        }

        List<PebbleEffectSet.Effect> effects = new ArrayList<>();
        for (int index = 0; index < configuredEffects.size(); index++) {
            Object configuredEffect = configuredEffects.get(index);
            if (!(configuredEffect instanceof List<?> effect)) {
                HavenPebbles.LOGGER.warn("Ignoring invalid effect {} for '{}'", index + 1, typeName);
                continue;
            }

            try {
                effects.add(parseEffect(effect, selection == PebbleEffectSet.Selection.RANDOM));
            } catch (RuntimeException exception) {
                HavenPebbles.LOGGER.warn(
                        "Ignoring invalid effect {} for '{}': {}",
                        index + 1,
                        typeName,
                        exception.getMessage()
                );
            }
        }

        return new PebbleEffectSet(selection, effects);
    }

    private static PebbleEffectSet.Effect parseEffect(List<?> configuredEffect, boolean usesChance) {
        String effectName = readString(configuredEffect, 0, "effect");
        Identifier effectId = Identifier.tryParse(effectName);
        if (effectId == null || !BuiltInRegistries.MOB_EFFECT.containsKey(effectId)) {
            throw new IllegalArgumentException("unknown effect '" + effectName + "'");
        }

        int duration = readInt(configuredEffect, 1, 600, "duration");
        int amplifier = readInt(configuredEffect, 2, 0, "amplifier");
        double chance = usesChance ? readDouble(configuredEffect, 3, 100.0D, "chance") : 100.0D;
        if (duration < 0) {
            throw new IllegalArgumentException("duration must be 0 or greater");
        }
        if (amplifier < 0) {
            throw new IllegalArgumentException("amplifier must be 0 or greater");
        }
        if (!Double.isFinite(chance) || chance < 0.0D || chance > 100.0D) {
            throw new IllegalArgumentException("chance must be between 0 and 100");
        }

        MobEffect effect = BuiltInRegistries.MOB_EFFECT.getValue(effectId);
        return new PebbleEffectSet.Effect(effect, duration, amplifier, chance);
    }

    private static String readString(UnmodifiableConfig config, String key) {
        Object value = config.getRaw(key);
        if (!(value instanceof String text) || text.isBlank()) {
            throw new IllegalArgumentException(key + " must be a non-empty string");
        }
        return text.trim();
    }

    private static String readString(List<?> values, int index, String name) {
        if (index >= values.size()) {
            throw new IllegalArgumentException(name + " is required");
        }
        Object value = values.get(index);
        if (!(value instanceof String text) || text.isBlank()) {
            throw new IllegalArgumentException(name + " must be a non-empty string");
        }
        return text.trim();
    }

    private static List<String> readStringList(UnmodifiableConfig config, String key) {
        Object value = config.getRaw(key);
        if (!(value instanceof List<?> values) || values.isEmpty()) {
            throw new IllegalArgumentException(key + " must be a non-empty list");
        }

        List<String> strings = new ArrayList<>();
        for (Object entry : values) {
            if (!(entry instanceof String text) || text.isBlank()) {
                throw new IllegalArgumentException(key + " must only contain non-empty strings");
            }
            strings.add(text.trim());
        }
        return strings;
    }

    private static int readInt(List<?> values, int index, int defaultValue, String name) {
        if (index >= values.size()) {
            return defaultValue;
        }
        Object value = values.get(index);
        if (!(value instanceof Number number)) {
            throw new IllegalArgumentException(name + " must be a whole number");
        }

        double decimal = number.doubleValue();
        if (!Double.isFinite(decimal) || decimal != Math.rint(decimal) || decimal < Integer.MIN_VALUE || decimal > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(name + " must be a whole number");
        }
        return (int) decimal;
    }

    private static double readDouble(List<?> values, int index, double defaultValue, String name) {
        if (index >= values.size()) {
            return defaultValue;
        }
        Object value = values.get(index);
        if (!(value instanceof Number number)) {
            throw new IllegalArgumentException(name + " must be a number");
        }
        return number.doubleValue();
    }
}