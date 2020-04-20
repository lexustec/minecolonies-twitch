package ch.lexustec.coremod.blocks.huts;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.util.Log;
import org.jetbrains.annotations.NotNull;

public class BlockHutTwitch extends AbstractBlockHut<BlockHutTwitch>
{
    public BlockHutTwitch()
    {
        super();
        Log.getLogger().info("BlockHutTwitch");
    }
    @NotNull
    @Override
    public String getName()
    {
        return "blockhuttwitch";
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return ModBuildings.home;
    }
}
