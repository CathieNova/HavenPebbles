package net.cathienova.havenpebbles.config;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = HavenPebbles.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigSubscriber {
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent event) {
        HavenPebblesConfig.bake(event.getConfig());
    }
}