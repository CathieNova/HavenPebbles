package net.cathienova.havenpebbles.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.InMemoryCommentedFormat;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerConfig implements IConfigSpec {
    public static final List<String> PEBBLE_IDS = List.of(
            "andesite_pebble",
            "basalt_pebble",
            "blackstone_pebble",
            "calcite_pebble",
            "deepslate_pebble",
            "diorite_pebble",
            "dripstone_pebble",
            "granite_pebble",
            "netherrack_pebble",
            "stone_pebble",
            "tuff_pebble"
    );

    private volatile ILoadedConfig loadedConfig;
    private volatile boolean defaultsCreated;

    public Map<String, String> pebbleEffects() {
        UnmodifiableConfig config = currentConfig();
        Object configuredValue = config.getRaw("pebbleEffects");
        UnmodifiableConfig configured = configuredValue instanceof UnmodifiableConfig value ? value : null;
        Map<String, String> effects = new LinkedHashMap<>();

        for (String pebbleId : PEBBLE_IDS) {
            String fallback = pebbleId.equals("blackstone_pebble") ? "random" : "default";
            Object value = configured == null ? null : configured.getRaw(pebbleId);
            effects.put(pebbleId, value instanceof String text && !text.isBlank() ? text : fallback);
        }

        return Map.copyOf(effects);
    }

    public UnmodifiableConfig pebbleEffectTypes() {
        Object configuredValue = currentConfig().getRaw("pebbleEffectTypes");
        return configuredValue instanceof UnmodifiableConfig configured ? configured : defaultEffectTypes();
    }

    public Object gathering() {
        return currentConfig().getRaw("gathering");
    }

    public boolean takeDefaultsCreated() {
        boolean created = defaultsCreated;
        defaultsCreated = false;
        return created;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void validateSpec(ModConfig config) {
    }

    @Override
    public boolean isCorrect(UnmodifiableCommentedConfig config) {
        return true;
    }

    @Override
    public void correct(CommentedConfig config) {
        config.clear();
        config.clearComments();
        populateDefaults(config);
        defaultsCreated = true;
    }

    @Override
    public void acceptConfig(@Nullable ILoadedConfig config) {
        loadedConfig = config;
    }

    private UnmodifiableConfig currentConfig() {
        ILoadedConfig current = loadedConfig;
        return current == null ? defaultConfig() : current.config();
    }

    private static CommentedConfig defaultConfig() {
        CommentedConfig config = newConfig();
        populateDefaults(config);
        return config;
    }

    private static void populateDefaults(CommentedConfig config) {
        config.set("pebbleEffects", defaultPebbleEffects());
        config.setComment("pebbleEffects", "Each pebble has its own effect type when consumed.\nUse \"none\" to disable effects for a pebble.");

        config.set("pebbleEffectTypes", defaultEffectTypes());
        config.setComment("pebbleEffectTypes", "Each type contains an all or random type and a list of effects.");

        config.set("gathering", defaultGathering());
    }

    private static Config defaultPebbleEffects() {
        CommentedConfig effects = newConfig();
        for (String pebbleId : PEBBLE_IDS) {
            effects.set(pebbleId, pebbleId.equals("blackstone_pebble") ? "random" : "default");
        }
        return effects;
    }

    private static Config defaultEffectTypes() {
        CommentedConfig effectTypes = newConfig();
        effectTypes.set("random", effectType("random", randomEffects()));
        effectTypes.set("default", effectType("all", allEffects()));
        return effectTypes;
    }

    private static Config effectType(String type, List<List<Object>> effects) {
        CommentedConfig effectType = newConfig();
        effectType.set("type", type);
        effectType.setComment("type", "Using 'all' will add all effects in the list, using 'random' will randomly give one effect.");
        effectType.set("effects", effects);
        effectType.setComment(
                "effects",
                type.equals("all")
                        ? "Effect format: [effect, durationTicks, amplifier]."
                        : "Effect format: [effect, durationTicks, amplifier, chancePercent]."
        );
        return effectType;
    }

    private static List<List<Object>> allEffects() {
        return List.of(
                effect("minecraft:slowness", 600, 0),
                effect("minecraft:mining_fatigue", 600, 0)
        );
    }

    private static List<List<Object>> randomEffects() {
        return List.of(
                effect("minecraft:slowness", 600, 0, 75.0D),
                effect("minecraft:mining_fatigue", 600, 0, 75.0D)
        );
    }

    private static List<Object> effect(String effect, int duration, int amplifier) {
        return List.of(effect, duration, amplifier);
    }

    private static List<Object> effect(String effect, int duration, int amplifier, double chance) {
        return List.of(effect, duration, amplifier, chance);
    }

    private static Config defaultGathering() {
        CommentedConfig gathering = newConfig();
        gathering.set(
                "overworld",
                gatheringEntry(
                        List.of("minecraft:overworld"),
                        List.of("minecraft:dirt", "minecraft:grass_block"),
                        List.of(
                                output("havenpebbles:andesite_pebble", 15, 1),
                                output("havenpebbles:calcite_pebble", 10, 1),
                                output("havenpebbles:deepslate_pebble", 10, 1),
                                output("havenpebbles:diorite_pebble", 10, 1),
                                output("havenpebbles:dripstone_pebble", 10, 1),
                                output("havenpebbles:granite_pebble", 10, 1),
                                output("havenpebbles:tuff_pebble", 10, 1),
                                output("havenpebbles:stone_pebble", 40, 1)
                        )
                )
        );
        gathering.set(
                "nether",
                gatheringEntry(
                        List.of("minecraft:the_nether"),
                        List.of("minecraft:netherrack"),
                        List.of(
                                output("havenpebbles:netherrack_pebble", 90, 1),
                                output("havenpebbles:basalt_pebble", 10, 1),
                                output("havenpebbles:blackstone_pebble", 5, 1)
                        )
                )
        );
        return gathering;
    }

    private static Config gatheringEntry(
            List<String> dimensions,
            List<String> useBlocks,
            List<List<Object>> outputs
    ) {
        CommentedConfig entry = newConfig();
        entry.set("dimensions", dimensions);
        entry.setComment("dimensions", "Dimension whitelist, supports * for every dimension.");
        entry.set("useBlocks", useBlocks);
        entry.setComment("useBlocks", "Blocks that can be crouch-right-clicked with an empty main hand.");
        entry.set("outputs", outputs);
        entry.setComment("outputs", "Output format: [item, weight, count].");
        return entry;
    }

    private static List<Object> output(String item, int weight, int count) {
        return List.of(item, weight, count);
    }

    static CommentedConfig newConfig() {
        return CommentedConfig.of(LinkedHashMap::new, InMemoryCommentedFormat.withUniversalSupport());
    }
}
