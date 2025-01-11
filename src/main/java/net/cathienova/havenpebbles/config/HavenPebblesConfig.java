package net.cathienova.havenpebbles.config;

import net.cathienova.havenpebbles.HavenPebbles;
import net.neoforged.fml.config.ModConfig;

import java.util.List;
import java.util.Map;

public class HavenPebblesConfig {
    public static boolean enablePebbles;
    public static boolean emitPebbleSound;
    public static List<? extends String> blockPebbleMappings;

    public static void bake(ModConfig config) {
        enablePebbles = HavenPebbles.c_config.enablePebbles.get();
        emitPebbleSound = HavenPebbles.c_config.emitPebbleSound.get();
        blockPebbleMappings = HavenPebbles.c_config.blockPebbleMappings.get();
    }
}
