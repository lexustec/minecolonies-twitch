package ch.lexustec.coremod.colony.buildings.workerbuildings;

import ch.lexustec.coremod.client.gui.WindowHutTwitch;
import com.ldtteam.blockout.views.Window;
import com.minecolonies.api.colony.IColony;
import ch.lexustec.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.IColonyView;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.coremod.colony.buildings.AbstractBuilding;
import com.minecolonies.coremod.colony.buildings.views.AbstractBuildingView;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.minecolonies.api.util.constant.Constants.MAX_BUILDING_LEVEL;

public class BuildingTwitch extends AbstractBuilding
{
    /**
     * The string describing the hut.
     */
    private static final String TWITCHHUT = "blockhuttwitchhut";

    /**
     * Instantiates a new citizen hut.
     *
     * @param c the colony.
     * @param l the location.
     */
    public BuildingTwitch(final IColony c, final BlockPos l)
    {
        super(c, l);

    }

    @Override
    public void deserializeNBT(final CompoundNBT compound)
    {
        super.deserializeNBT(compound);

    }


    @Override
    public CompoundNBT serializeNBT()
    {
        final CompoundNBT compound = super.serializeNBT();
        return compound;
    }


    @Override
    public void onWakeUp()
    {

    }

    @NotNull
    @Override
    public String getSchematicName()
    {
        return TWITCHHUT;
    }

    @Override
    public void registerBlockPosition(@NotNull final BlockState blockState, @NotNull final BlockPos pos, @NotNull final World world)
    {
        super.registerBlockPosition(blockState, pos, world);

        //BlockPos registrationPosition = pos;
        //if (blockState.getBlock() instanceof BedBlock)
        //{
        //    if (blockState.get(BedBlock.PART) == BedPart.FOOT)
        //    {
        //        registrationPosition = registrationPosition.offset(blockState.get(BedBlock.HORIZONTAL_FACING));
        //    }
        //
        //    if (!bedList.contains(registrationPosition))
        //    {
        //        bedList.add(registrationPosition);
        //    }
        //}
    }

    @Override
    public void onDestroyed()
    {
        super.onDestroyed();
        //getAssignedCitizen().stream()
        //  .filter(Objects::nonNull)
        //  .forEach(citizen -> citizen.setHomeBuilding(null));
    }

    /**
     * Updates the child creation timer and tries to assign homeless citizens on colony tick.
     *
     * @param colony the colony which ticks.
     */
    @Override
    public void onColonyTick(@NotNull final IColony colony)
    {

    }
    /**
     * Try to spawn a new citizen as child.
     * Mom / dad entities are required and chosen randomly in this hut.
     * Childs inherit stats from their parents, avergaged +-2
     * Childs get assigned to a free housing slot in the colony to be raised there,
     * if the house has an adult living there the child takes its name and gets raised by it.
     */
    public void trySpawnStreamer()
    {

    }

    @Override
    public int getMaxBuildingLevel()
    {
        return MAX_BUILDING_LEVEL;
    }

    @Override
    public void onUpgradeComplete(final int newLevel)
    {

    }
    @Override
    public BuildingEntry getBuildingRegistryEntry()
    {
        return ModBuildings.twitchhut;
    }

    @Override
    public void serializeToView(@NotNull final PacketBuffer buf)
    {
        super.serializeToView(buf);
    }
    @Override
    public void setBuildingLevel(final int level)
    {
        super.setBuildingLevel(level);
    }
    @Override
    public void onBuildingMove(final IBuilding oldBuilding)
    {

    }

    /**
     * The view of the citizen hut.
     */
    public static class View extends AbstractBuildingView
    {
        @NotNull
        private final List<Integer> residents = new ArrayList<>();

        /**
         * Creates an instance of the citizen hut window.
         *
         * @param c the colonyView.
         * @param l the position the hut is at.
         */
        public View(final IColonyView c, final BlockPos l)
        {
            super(c, l);
        }

        @NotNull
        @Override
        public Window getWindow()
        {
            return new WindowHutTwitch(this);
        }


        @Override
        public void deserialize(@NotNull final PacketBuffer buf)
        {
            super.deserialize(buf);
        }






    }



}


