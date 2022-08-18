package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.proxy.CommonProxy;
import foxesworld.aidenfox.stuff.sounds.Sounds;
import foxesworld.aidenfox.stuff.world.WorldGen;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import static foxesworld.aidenfox.cfg.ConfigCreator.initConfig;

@Mod(modid = Environment.MODID, name = Environment.NAME, version = Environment.VERSION)
public class main {
    @SidedProxy(clientSide = Environment.clientsideProxy, serverSide = Environment.serversideProxy)

    public static CommonProxy proxy;
    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        ConfigCreator cfg = new ConfigCreator("hardtools.cfg");
        initConfig(event);
        Sounds sounds = new Sounds("sounds.json", Environment.MODID);
            sounds.scanSounds();
        Content content = new Content();
            content.registerItems();
            content.registerBlocks();
        GameRegistry.registerWorldGenerator(new WorldGen(), 3);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
