package ch.lexustec.apiimp.initializer;

import ch.lexustec.api.blocks.ModBlocks;
import ch.lexustec.api.tileentities.MinecoloniesTwitchEntities;
import ch.lexustec.api.tileentities.TileEntityTwitch;
import com.minecolonies.api.tileentities.TileEntityColonyBuilding;
import com.minecolonies.api.tileentities.TileEntityEnchanter;
import com.minecolonies.api.util.Log;
import com.minecolonies.api.util.constant.Constants;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constants.MOD_ID)
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityInitializer
{
    @SubscribeEvent
    public static void registerTileEntity(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        MinecoloniesTwitchEntities.TWITCHBUILDING = (TileEntityType<? extends TileEntityColonyBuilding>) TileEntityType.Builder.create(TileEntityTwitch::new,
          ModBlocks.blockHutTwitch).build(null).setRegistryName(Constants.MOD_ID, "twitchbuilding");
        Log.getLogger().info("registerTileEntity");
        event.getRegistry().registerAll(
          MinecoloniesTwitchEntities.TWITCHBUILDING);
    }
}