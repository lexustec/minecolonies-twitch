package ch.lexustec.coremod.network.messages;

import com.ldtteam.blockout.Log;
import com.ldtteam.structurize.util.LanguageHandler;
import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import com.minecolonies.api.colony.permissions.Action;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.network.IMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BuyStreamerMessage implements IMessage
{

    /**
     * Indexable information about the chosen item
     */
    public enum BuyStreamerType
    {
        HAY_BALE(1, Items.HAY_BLOCK),
        BOOK(2, Items.BOOK),
        EMERALD(3, Items.EMERALD),
        DIAMOND(4, Items.DIAMOND);

        private final int  index;
        private final Item item;

        BuyStreamerType(final int index, final Item item)
        {
            this.index = index;
            this.item = item;
        }

        public int getIndex()
        {
            return index;
        }

        public Item getItem()
        {
            return item;
        }

        public static BuyStreamerMessage.BuyStreamerType getFromIndex(final int index)
        {
            for (final BuyStreamerMessage.BuyStreamerType type : BuyStreamerMessage.BuyStreamerType.values())
            {
                if (type.index == index)
                {
                    return type;
                }
            }
            return DIAMOND;
        }
    }
    /**
     * ID of the colony
     */
    private int colonyId;

    /**
     * Index of the chosen item, sent to the server
     */
    private String streamerName;

    /**
     * The dimension of the
     */
    private int dimension;
    
    public BuyStreamerMessage(){super();}

    public BuyStreamerMessage(@NotNull final String streamerName, @NotNull final int colonyId, @NotNull final int dimension)
    {
        super();
        this.streamerName = streamerName;
        this.colonyId = colonyId;
        this.dimension = dimension;
    }


    @Override
    public void toBytes(final PacketBuffer buf)
    {
        buf.writeInt(colonyId);
        buf.writeInt(dimension);
        buf.writeString(streamerName);
    }

    @Override
    public void fromBytes(final PacketBuffer buf)
    {
        colonyId = buf.readInt();
        dimension = buf.readInt();
        streamerName = buf.readString();
    }

    @Nullable
    @Override
    public LogicalSide getExecutionSide()
    {
        return null;
    }

    @Override
    public void onExecute(final NetworkEvent.Context ctxIn, final boolean isLogicalServer)
    {

        final IColony colony = IColonyManager.getInstance().getColonyByDimension(colonyId, dimension);
        if (colony == null || !colony.getPermissions().hasPermission(ctxIn.getSender(), Action.MANAGE_HUTS))
        {
            return;
        }
        final PlayerEntity player = ctxIn.getSender();

        //if can hire and space
        BlockPos spawnpos = player.getPosition();
        spawnpos.add(1,1,1);
        final ICitizenData data = colony.getCitizenManager().createAndRegisterNewCitizenData();
        data.getCitizenSkillHandler().init(1090);
        data.setIsFemale(false);
        data.setName(streamerName);
        LanguageHandler.sendPlayersMessage(colony.getMessagePlayerEntities(), "com.minecolonies.coremod.progress.hireCitizen");
        colony.getCitizenManager().spawnOrCreateCitizen(data, colony.getWorld(), spawnpos, true);
        final Optional<AbstractEntityCitizen> citizen = data.getCitizenEntity();
        if(citizen != null)
        {
            Log.getLogger().info("Got the citizen");

        }
    }
}
