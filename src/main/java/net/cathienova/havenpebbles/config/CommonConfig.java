package net.cathienova.havenpebbles.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class CommonConfig {
    public final ModConfigSpec.BooleanValue enablePebbles;
    public final ModConfigSpec.BooleanValue emitPebbleSound;
    public final ModConfigSpec.ConfigValue<List<? extends String>> blockPebbleMappings;

    public CommonConfig(ModConfigSpec.Builder builder) {
        enablePebbles = builder.comment("Enable pebbles?").define("enablePebbles", true);
        emitPebbleSound = builder.comment("When right clicking to get pebbles, should it emit sound?").define("emitPebbleSound", true);

        blockPebbleMappings = builder.comment("Block to pebble mappings")
                .defineList(
                        "blockPebbleMappings",
                        List.of(
                                "minecraft:dirt;havenpebbles:andesite_pebble:15,havenpebbles:calcite_pebble:10,havenpebbles:deepslate_pebble:10,havenpebbles:diorite_pebble:10,havenpebbles:dripstone_pebble:10,havenpebbles:granite_pebble:10,havenpebbles:tuff_pebble:10,havenpebbles:stone_pebble:40",
                                "minecraft:grass_block;havenpebbles:andesite_pebble:15,havenpebbles:calcite_pebble:10,havenpebbles:deepslate_pebble:10,havenpebbles:diorite_pebble:10,havenpebbles:dripstone_pebble:10,havenpebbles:granite_pebble:10,havenpebbles:tuff_pebble:10,havenpebbles:stone_pebble:40",
                                "minecraft:netherrack;havenpebbles:netherrack_pebble:90,havenpebbles:basalt_pebble:10,havenpebbles:blackstone_pebble:5"
                        ),
                        obj -> obj instanceof String && ((String) obj).contains(";")
                );
    }
}
