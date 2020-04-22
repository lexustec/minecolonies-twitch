package ch.lexustec.api.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigTwitch extends AbstractConfigTwitch
{
    /**
     * Builds common configuration.
     *
     * @param builder config builder
     */
    protected CommonConfigTwitch(final ForgeConfigSpec.Builder builder)
    {
        createCategory(builder, "MinecoloniesTwitch");
        finishCategory(builder);
    }
}
