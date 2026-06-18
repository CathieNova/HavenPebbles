package net.cathienova.havenpebbles.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public final ModConfigSpec.BooleanValue emitPebbleSound;

    public ClientConfig(ModConfigSpec.Builder builder) {
        emitPebbleSound = builder.comment("When a item is gathered, should it play the gathering sound? (uses BEEHIVE_ENTER)")
                .define("emitPebbleSound", true);
    }
}