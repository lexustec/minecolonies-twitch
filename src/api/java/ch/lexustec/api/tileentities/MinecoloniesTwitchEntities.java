package ch.lexustec.api.tileentities;

import com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding;
import com.minecolonies.api.tileentities.TileEntityColonyBuilding;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class MinecoloniesTwitchEntities
{
    @ObjectHolder("twitchbuilding")
    public static TileEntityType<? extends TileEntityColonyBuilding> TWITCHBUILDING;
}
