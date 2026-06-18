package net.cathienova.havenpebbles.network;

import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ModNetworking {
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToClient(
                PebbleSoundPayload.TYPE,
                PebbleSoundPayload.STREAM_CODEC,
                ModNetworking::playPebbleSound
        );
    }

    private static void playPebbleSound(PebbleSoundPayload payload, IPayloadContext context) {
        if (!HavenPebblesConfig.emitPebbleSound) {
            return;
        }

        context.player().level().playLocalSound(payload.pos(), SoundEvents.BEEHIVE_ENTER, SoundSource.PLAYERS, 0.75F, 0.75F, false);
    }
}