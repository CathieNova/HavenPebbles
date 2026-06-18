package net.cathienova.havenpebbles;

import com.mojang.logging.LogUtils;
import net.cathienova.havenpebbles.config.ClientConfig;
import net.cathienova.havenpebbles.config.ServerConfig;
import net.cathienova.havenpebbles.datagen.DataGenerators;
import net.cathienova.havenpebbles.item.ModCreativeTab;
import net.cathienova.havenpebbles.item.ModItems;
import net.cathienova.havenpebbles.network.ModNetworking;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod(HavenPebbles.MODID)
public class HavenPebbles
{
    public static final String MODID = "havenpebbles";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfigSpec clientSpec;
    public static final IConfigSpec serverSpec;
    public static final ClientConfig client_config;
    public static final ServerConfig server_config;

    static
    {
        final Pair<ClientConfig, ModConfigSpec> clientPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        clientSpec = clientPair.getRight();
        client_config = clientPair.getLeft();

        server_config = new ServerConfig();
        serverSpec = server_config;
    }

    public HavenPebbles(IEventBus bus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.CLIENT, clientSpec);
        modContainer.registerConfig(ModConfig.Type.SERVER, serverSpec);

        ModItems.ITEMS.register(bus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(bus);

        bus.addListener(DataGenerators::gatherData);
        bus.addListener(ModNetworking::registerPayloads);
    }
}