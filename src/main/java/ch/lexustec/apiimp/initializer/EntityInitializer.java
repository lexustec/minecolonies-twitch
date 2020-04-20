package ch.lexustec.apiimp.initializer;

import ch.lexustec.api.util.constant.Constants;
import com.minecolonies.api.util.Log;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


@ObjectHolder(Constants.MOD_ID)
//@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityInitializer
{
    public static void setupEntities()
    {
        Log.getLogger().info("setupEntities in EntityInitializer.java");
    }

    //@SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        Log.getLogger().info("registerEntities in EntityInitializer.java");
    }
}