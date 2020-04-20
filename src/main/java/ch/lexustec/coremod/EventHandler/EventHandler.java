package ch.lexustec.coremod.EventHandler;

import com.minecolonies.api.util.Log;
import com.minecolonies.coremod.colony.ColonyManager;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import static net.minecraftforge.eventbus.api.EventPriority.LOWEST;

public class EventHandler
{
    /**
     * Adds our custom loot tables to vanilla tables.
     *
     * @param event
     */
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event){}
    /**
     * Event when the debug screen is opened. Event gets called by displayed text on the screen, we only need it when f3 is clicked.
     *
     * @param event {@link net.minecraftforge.client.event.RenderGameOverlayEvent.Text}
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onDebugOverlay(final RenderGameOverlayEvent.Text event)
    {}

    /**
     * Event called to attach capabilities on a chunk.
     *
     * @param event the event.
     */
    @SubscribeEvent
    public static void onAttachingCapabilitiesChunk(@NotNull final AttachCapabilitiesEvent<Chunk> event)
    {
       // event.addCapability(new ResourceLocation(Constants.MOD_ID, "closecolony"), new MinecoloniesChunkCapabilityProvider());
    }
    /**
     * Called when a chunk gets loaded for some reason.
     */
    @SubscribeEvent
    public static void onChunkLoad(@NotNull final ChunkEvent.Load event)
    {

    }
    /**
     * Called when a chunk gets unloaded
     */
    @SubscribeEvent
    public static void onChunkUnLoad(final ChunkEvent.Unload event)
    {

    }
    /**
     * Event called when a player enters the world.
     *
     * @param event player enter world event
     */
    @SubscribeEvent
    public static void onPlayerEnterWorld(final PlayerEvent.PlayerLoggedInEvent event)
    {

    }
    /**
     * Event called when a player leaves the world.
     *
     * @param event player leaves world event
     */
    @SubscribeEvent
    public static void onPlayerLeaveWorld(final PlayerEvent.PlayerLoggedOutEvent event)
    {

    }
    /**
     * Gets called when world loads. Calls
     *
     * @param event {@link net.minecraftforge.event.world.WorldEvent.Load}
     */
    @SubscribeEvent(priority = LOWEST)
    public static void onWorldLoad(@NotNull final WorldEvent.Load event)
    {
        Log.getLogger().warn("World load");
    }
    /**
     * Gets called when world unloads.
     *
     * @param event {@link net.minecraftforge.event.world.WorldEvent.Unload}
     */
    @SubscribeEvent
    public static void onWorldUnload(@NotNull final WorldEvent.Unload event)
    {
        Log.getLogger().warn("World unload");
    }

}
