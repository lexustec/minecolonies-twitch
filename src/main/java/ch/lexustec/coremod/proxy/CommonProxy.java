package ch.lexustec.coremod.proxy;

import ch.lexustec.api.util.constant.Constants;
import com.ldtteam.blockout.Log;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import ch.lexustec.apiimp.initializer.ModBuildingsInitializer;
import com.minecolonies.coremod.proxy.IProxy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

/**
 * CommonProxy of the minecolonies mod (Server and Client).
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class CommonProxy implements IProxy
{

    /**
     * Creates instance of proxy.
     */
    public CommonProxy()
    {

    }

    @SubscribeEvent
    public static void registerBuildingTypes(@NotNull final RegistryEvent.Register<BuildingEntry> event)
    {
        Log.getLogger().info("RegisterBuildingType");
        ModBuildingsInitializer.init(event);
    }
}
