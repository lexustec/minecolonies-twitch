package ch.lexustec.coremod.blocks.huts;

import ch.lexustec.api.util.constant.Constants;
import com.ldtteam.structurize.management.StructureName;
import com.ldtteam.structurize.management.Structures;
import com.ldtteam.structurize.util.StructureLoadingUtils;
import com.minecolonies.api.blocks.AbstractBlockHut;
import ch.lexustec.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.util.Log;
import org.jetbrains.annotations.NotNull;

public class BlockHutTwitch extends AbstractBlockHut<BlockHutTwitch>
{
    public BlockHutTwitch()
    {
        super();
        Log.getLogger().info("BlockHutTwitch");
        StructureName.HUTS.add("blockhuttwitchhut");


    }
    @NotNull
    @Override
    public String getName()
    {
        return "blockhuttwitchhut";
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return ModBuildings.twitchhut;
    }
}
