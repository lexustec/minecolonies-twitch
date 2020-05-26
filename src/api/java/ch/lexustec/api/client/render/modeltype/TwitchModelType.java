package ch.lexustec.api.client.render.modeltype;

import com.ldtteam.blockout.Log;
import com.minecolonies.api.client.render.modeltype.IModelType;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class TwitchModelType implements IModelType
{

    private static final ResourceLocation TEXTURE_STEVE = new ResourceLocation("textures/entity/steve.png");
    private String streamerName;
    //private GameProfile profile;

    public TwitchModelType (String myName)
    {
        super();
        this.streamerName = myName;
    }


    @Override
    public String getName()
    {
        return this.streamerName;
    }

    @Override
    public ResourceLocation getTexture(final AbstractEntityCitizen abstractEntityCitizen)
    {

        Log.getLogger().info("TwitchTextureRequest YAY for "+getName());
        return getPlayerTexture(getName());
    }

    private static ResourceLocation getPlayerTexture(String name) {
        GameProfile profile;
        ResourceLocation res = getDefault();
        profile = new GameProfile(null, name);
        if (profile != null && profile.getName() != null) {
            Minecraft minecraft = Minecraft.getInstance();
            Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(profile);
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN))
                res = minecraft.getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            Log.getLogger().info("ResourcePath = " + res.toString());
            return res;
        }
        Log.getLogger().info("ResourcePath = " + res.toString());
        return res;
    }

    public static ResourceLocation getDefault() {

                return TEXTURE_STEVE;

    }

}
