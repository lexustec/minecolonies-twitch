package ch.lexustec.coremod.network.messages;

import ch.lexustec.api.client.render.modeltype.TwitchModelType;
import ch.lexustec.coremod.entity.citizen.citizenhandlers.TwitchJobHandler;
import com.ldtteam.blockout.Log;
import com.minecolonies.api.client.render.modeltype.CitizenModel;
import com.minecolonies.api.client.render.modeltype.IModelType;
import com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry;
import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.network.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class UpdateModeltypeMessage implements IMessage
{
    private String streamerName;

    private int entity;


    public UpdateModeltypeMessage(){super();}

    public UpdateModeltypeMessage(@NotNull final String streamerName, @NotNull final int myEntityID)
    {
        super();
        this.streamerName = streamerName;
        this.entity = myEntityID;
    }


    @Override
    public void toBytes(final PacketBuffer buf)
    {

        buf.writeString(streamerName);
        buf.writeInt(entity);
    }

    @Override
    public void fromBytes(final PacketBuffer buf)
    {
        streamerName = buf.readString();
        entity = buf.readInt();
    }

    @Nullable
    @Override
    public LogicalSide getExecutionSide()
    {
        return LogicalSide.CLIENT;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onExecute(final NetworkEvent.Context ctxIn, final boolean isLogicalServer)
    {

        final Entity myEntity = Minecraft.getInstance().world.getEntityByID(entity);
        if(myEntity instanceof AbstractEntityCitizen)
        {
            final AbstractEntityCitizen citizen = ( AbstractEntityCitizen ) myEntity;
            Log.getLogger().info("Got Citizen Client Side");
            TwitchModelType model = new TwitchModelType(this.streamerName);
            IModelTypeRegistry registry = IModelTypeRegistry.getInstance();
            registry.register(model,new CitizenModel<>(), new CitizenModel<>());
            citizen.setModelId(model);
            citizen.setCitizenJobHandler(new TwitchJobHandler(citizen));
            citizen.markDirty();

        }
    }

}
