package ch.lexustec.api.configuration;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Mod root configuration.
 */
public class ConfigTwitch
{
    /**
     * Loaded everywhere, not synced
     */
    private final CommonConfigTwitch commonConfig;

    /**
     * Loaded clientside, not synced
     */
    private final ClientConfigTwitch clientConfig;

    /**
     * Loaded serverside, synced on connection
     */
    private final ServerConfigTwitch serverConfig;

    /**
     * Builds configuration tree.
     */
    public ConfigTwitch()
    {
        final Pair<CommonConfigTwitch, ForgeConfigSpec> com = new ForgeConfigSpec.Builder().configure(builder -> new CommonConfigTwitch(builder));
        final Pair<ClientConfigTwitch, ForgeConfigSpec> cli = new ForgeConfigSpec.Builder().configure(ClientConfigTwitch::new);
        final Pair<ServerConfigTwitch, ForgeConfigSpec> ser = new ForgeConfigSpec.Builder().configure(ServerConfigTwitch::new);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, cli.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ser.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.getRight());

        commonConfig = com.getLeft();
        clientConfig = cli.getLeft();
        serverConfig = ser.getLeft();
    }

    public CommonConfigTwitch getCommon()
    {
        return commonConfig;
    }

    public ClientConfigTwitch getClient()
    {
        return clientConfig;
    }

    public ServerConfigTwitch getServer()
    {
        return serverConfig;
    }
}
