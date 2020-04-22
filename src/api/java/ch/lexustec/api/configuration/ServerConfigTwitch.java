package ch.lexustec.api.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfigTwitch extends AbstractConfigTwitch
{
    /**
     * Streamer List
     */

    /**
     * Builds server configuration.
     *
     * @param builder config builder
     */
    protected ServerConfigTwitch(final ForgeConfigSpec.Builder builder)
    {
        createCategory(builder, "streamer");

        finishCategory(builder);
    }
}
