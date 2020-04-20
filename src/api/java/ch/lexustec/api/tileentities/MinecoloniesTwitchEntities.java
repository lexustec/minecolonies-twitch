package ch.lexustec.api.tileentities;

import ch.lexustec.api.util.constant.Constants;
import com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding;
import com.minecolonies.api.tileentities.TileEntityColonyBuilding;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

// MinecoloniesTileEntities
@ObjectHolder(Constants.MOD_ID)
public class MinecoloniesTwitchEntities
{
    @ObjectHolder("twitchbuilding")
    public static TileEntityType<? extends TileEntityColonyBuilding> TWITCHBUILDING;
}
