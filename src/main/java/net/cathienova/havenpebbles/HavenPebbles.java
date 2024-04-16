package net.cathienova.havenpebbles;

import com.mojang.logging.LogUtils;
import net.cathienova.havenpebbles.config.CommonConfig;
import net.cathienova.havenpebbles.item.ModCreativeTab;
import net.cathienova.havenpebbles.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(HavenPebbles.MODID)
public class HavenPebbles
{
    public static final String MODID = "havenpebbles";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HavenPebbles(IEventBus bus)
    {
        bus.addListener(this::commonSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        ModItems.ITEMS.register(bus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(bus);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

}
