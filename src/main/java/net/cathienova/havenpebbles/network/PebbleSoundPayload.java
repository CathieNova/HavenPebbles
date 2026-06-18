package net.cathienova.havenpebbles.network;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record PebbleSoundPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<PebbleSoundPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(HavenPebbles.MODID, "pebble_sound"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PebbleSoundPayload> STREAM_CODEC = StreamCodec.of(
            (buffer, payload) -> buffer.writeBlockPos(payload.pos()),
            buffer -> new PebbleSoundPayload(buffer.readBlockPos())
    );

    @Override
    public Type<PebbleSoundPayload> type() {
        return TYPE;
    }
}