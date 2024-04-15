package net.cathienova.havenpebbles;

import com.mojang.logging.LogUtils;
import net.cathienova.havenpebbles.config.CommonConfig;
import net.cathienova.havenpebbles.events.PebbleHandler;
import net.cathienova.havenpebbles.item.ModCreativeModeTab;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod(HavenPebbles.MODID)
public class HavenPebbles
{
    public static final String MODID = "havenpebbles";
    public static final Logger LOGGER = LogUtils.getLogger();
    static final ForgeConfigSpec commonSpec;
    public static final CommonConfig c_config;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        commonSpec = specPair.getRight();
        c_config = specPair.getLeft();
    }

    public HavenPebbles()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec, "HavenPebbles-Config.toml");

        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new PebbleHandler());

        modEventBus.addListener(this::commonSetup);
        ModCreativeModeTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
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
