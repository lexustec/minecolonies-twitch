package ch.lexustec.coremod;
import ch.lexustec.api.blocks.ModBlocks;
import ch.lexustec.api.configuration.ConfigTwitch;
import ch.lexustec.api.configuration.StreamerConfig;
import ch.lexustec.api.util.constant.Constants;
import ch.lexustec.coremod.EventHandler.EventHandler;
import ch.lexustec.coremod.EventHandler.FMLEventHandler;

import com.ldtteam.structurize.util.StructureLoadingUtils;
import ch.lexustec.coremod.proxy.CommonProxy;
import com.minecolonies.api.util.Log;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.resource.IResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.List;
import java.util.function.Predicate;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class MineColoniesTwitch
{
    // Directly reference a log4j logger.
    private static final Logger       LOGGER = LogManager.getLogger();
    /**
     * The config instance.
     */
    private static       ConfigTwitch config;
    private static StreamerConfig streamers;
    /**
     * The proxy.
     */
    //public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MineColoniesTwitch() {
        //LanguageHandler.loadLangPath("assets/minecoloniestwitch/lang/%s.json"); // hotfix config comments, it's ugly bcs it's gonna be replaced
        config = new ConfigTwitch();
        streamers = new StreamerConfig();
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(EventHandler.class);
        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(FMLEventHandler.class);

        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(CommonProxy.class);
        //Mod.EventBusSubscriber.Bus.MOD.bus().get().register(this::onModInit);
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(this.getClass());

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onStitch(final TextureStitchEvent.Pre event)
    {
        LOGGER.info("In onStitch");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        ch.lexustec.coremod.Network.getNetwork().registerCommonMessages();
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        Arrays.stream(ModBlocks.getHuts());
        StructureLoadingUtils.originFolders.add(Constants.MOD_ID);

        getStreamers();
    }
    
    /**
     * Event handler for forge pre init event.
     *
     * @param event the forge pre init event.
     */
    @SubscribeEvent
    public static void preInit(@NotNull final FMLCommonSetupEvent event)
    {
        LOGGER.info("preInit minecolonies TWITCH");
        // Config



    }

    @SubscribeEvent
    public void onModInit(final FMLCommonSetupEvent event)
    {
        LOGGER.warn("FMLCommonSetupEvent");

        StructureLoadingUtils.originFolders.add(Constants.MOD_ID);


    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    //@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    //public static class RegistryEvents {
    //    @SubscribeEvent
    //    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
    //        // register a new block here
    //        LOGGER.info("HELLO from Register Block");
    //    }
    //}

    /**
     * Get the config handler.
     *
     * @return the config handler.
     */
    //public static Configuration getConfig()
    //{
    //    return config;
    //}

    /**
     * gets all StreamerPackages
     */
    public static void getStreamers()
    {
        //List<String> directories = new ArrayList<>();
        try {

            File file = new File(Minecraft.getInstance().gameDir + "/" + Constants.MOD_ID + "/streamer");
            String[] directories = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            });
            LOGGER.info("File List" + Arrays.toString(directories));
           File[] file2 = new File(Minecraft.getInstance().gameDir + "/" + Constants.MOD_ID + "/streamer").listFiles(File::isDirectory);
            LOGGER.info("File List2" + Arrays.toString(file2));
            List<String> streamersToAdd = new ArrayList<>();
            for (String check: directories)
            {
                File f = new File(Minecraft.getInstance().gameDir + "/" + Constants.MOD_ID + "/streamer/" + check + "/" + check +".xml");
                if(f.exists())
                {
                    LOGGER.info("Datapack "+ check + " seems valid");
                    streamersToAdd.add(check);
                }
                else
                {
                    LOGGER.info("Dunno wyd this aint no good -> " + check);
                }
            }
            streamers.setStreamers(streamersToAdd);


        }catch (Exception e)
        {
            LOGGER.warn(e.getMessage());
        }
    }
}
