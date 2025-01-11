package net.cathienova.havenpebbles;

import com.mojang.logging.LogUtils;
import net.cathienova.havenpebbles.config.CommonConfig;
import net.cathienova.havenpebbles.config.HavenPebblesConfig;
import net.cathienova.havenpebbles.events.PebbleHandler;
import net.cathienova.havenpebbles.item.ModCreativeTab;
import net.cathienova.havenpebbles.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod(HavenPebbles.MODID)
public class HavenPebbles
{
    public static final String MODID = "havenpebbles";
    public static final Logger LOGGER = LogUtils.getLogger();
    static final ModConfigSpec commonSpec;
    public static final CommonConfig c_config;

    static
    {
        final Pair<CommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        commonSpec = specPair.getRight();
        c_config = specPair.getLeft();
    }

    public HavenPebbles(IEventBus bus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, commonSpec);
        ModItems.ITEMS.register(bus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(bus);

        bus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        PebbleHandler.loadMappings();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

}
