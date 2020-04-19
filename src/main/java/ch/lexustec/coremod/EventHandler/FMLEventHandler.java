package ch.lexustec.coremod.EventHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.jetbrains.annotations.NotNull;

public class FMLEventHandler
{
    @SubscribeEvent
    public static void onServerTick(final TickEvent.ServerTickEvent event)
    {

    }

    @SubscribeEvent
    public static void onClientTick(final TickEvent.ClientTickEvent event)
    {

    }

    @SubscribeEvent
    public static void onWorldTick(final TickEvent.WorldTickEvent event)
    {

    }

    @SubscribeEvent
    public static void onPlayerLogin(@NotNull final PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getPlayer() instanceof ServerPlayerEntity)
        {

        }
    }

    /**
     * Called when registering sounds,
     * we have to register all our mod items here.
     *
     * @param event the registery event for items.
     */
    @SubscribeEvent
    public static void registerSounds(@NotNull final RegistryEvent.Register<SoundEvent> event)
    {

    }

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {

    }

    @SubscribeEvent
    public static void onServerStopped(final FMLServerStoppingEvent event)
    {

    }

}
