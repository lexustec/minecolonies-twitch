package ch.lexustec.apiimp.initializer;

import ch.lexustec.api.blocks.ModBlocks;
import ch.lexustec.api.colony.buildings.ModBuildings;
import ch.lexustec.api.util.constant.Constants;
import ch.lexustec.coremod.colony.buildings.workerbuildings.BuildingTwitch;
import com.ldtteam.blockout.Log;
import com.minecolonies.api.MinecoloniesAPIProxy;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class ModBuildingsInitializer
{
    private ModBuildingsInitializer()
    {
        throw new IllegalStateException("Tried to initialize: ModBuildingsInitializer but this is a Utility class.");
    }

    public static void init(final RegistryEvent.Register<BuildingEntry> event)
    {
        final IForgeRegistry<BuildingEntry> reg = event.getRegistry();


        Log.getLogger().info("Init function");
        ModBuildings.twitchhut = new BuildingEntry.Builder()
          .setBuildingBlock(ModBlocks.blockHutTwitch)
          .setBuildingProducer(BuildingTwitch::new)
          .setBuildingViewProducer(()->BuildingTwitch.View::new)
          .setRegistryName(new ResourceLocation("minecolonies",ModBuildings.TWITCH_ID))
          .createBuildingEntry();

        reg.register(ModBuildings.twitchhut);
        for( BuildingEntry build:reg.getValues())
        {
            Log.getLogger().info(build.getRegistryName());
        }


    }
}
