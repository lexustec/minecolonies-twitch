package ch.lexustec.coremod;
import ch.lexustec.api.blocks.ModBlocks;
import ch.lexustec.api.util.constant.Constants;
import ch.lexustec.coremod.EventHandler.EventHandler;
import ch.lexustec.coremod.EventHandler.FMLEventHandler;

import com.ldtteam.structurize.Network;
import com.ldtteam.structurize.util.LanguageHandler;
import com.ldtteam.structurize.util.StructureLoadingUtils;
import com.minecolonies.api.configuration.Configuration;
import ch.lexustec.coremod.proxy.CommonProxy;
import com.minecolonies.api.util.Log;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class MineColoniesTwitch
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The config instance.
     */
    private static Configuration config;

    /**
     * The proxy.
     */
    //public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MineColoniesTwitch() {
        //LanguageHandler.loadLangPath("assets/minecoloniestwitch/lang/%s.json"); // hotfix config comments, it's ugly bcs it's gonna be replaced
        config = new Configuration();
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(EventHandler.class);
        Mod.EventBusSubscriber.Bus.FORGE.bus().get().register(FMLEventHandler.class);
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(this.getClass());
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(CommonProxy.class);
        //Mod.EventBusSubscriber.Bus.MOD.bus().get().register(this::onModInit);
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
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        Arrays.stream(ModBlocks.getHuts());
        StructureLoadingUtils.originFolders.add(Constants.MOD_ID);

    }

    @SubscribeEvent
    public void onModInit(final FMLCommonSetupEvent event)
    {
        Log.getLogger().warn("FMLCommonSetupEvent");
        Network.getNetwork().registerCommonMessages();
        StructureLoadingUtils.originFolders.add(Constants.MOD_ID);
    }
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
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

}
