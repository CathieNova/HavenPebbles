package net.cathienova.havenpebbles.config;

import net.cathienova.havenpebbles.HavenPebbles;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = HavenPebbles.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ConfigSubscriber {
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent event) {
        HavenPebblesConfig.bake(event.getConfig());
    }
}