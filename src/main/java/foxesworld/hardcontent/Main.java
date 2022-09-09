package foxesworld.hardcontent;

import foxesworld.hardcontent.cfg.ConfigInit;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.DynamicEventHandler;
import foxesworld.hardcontent.gui.mainmenu.configuration.ConfigurationLoader;
import foxesworld.hardcontent.gui.mainmenu.handler.CMMEventHandler;
import foxesworld.hardcontent.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static foxesworld.hardcontent.cfg.Environment.MODID;
import static foxesworld.hardcontent.cfg.Environment.acceptedVersions;

@Mod(modid = MODID, name = Environment.NAME, version = Environment.VERSION, acceptedMinecraftVersions = acceptedVersions)

public class Main extends Environment {
    @SidedProxy(clientSide = Environment.clientsideProxy, serverSide = Environment.serversideProxy)
    public static CommonProxy commonProxy;
    public static Logger logger;

    @Instance(MODID)
    public static Main INSTANCE;
    public static CMMEventHandler EVENT_HANDLER;
    private ConfigurationLoader configLoader;
    public foxesworld.hardcontent.gui.mainmenu.configuration.Config config;
    public File configFolder;

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        commonProxy.preInit(event);
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        if (ConfigInit.CONFIGgeneral.mainmenu) {
            configFolder = event.getModConfigurationDirectory();
            config = new foxesworld.hardcontent.gui.mainmenu.configuration.Config();

            EVENT_HANDLER = new CMMEventHandler();
            MinecraftForge.EVENT_BUS.register(EVENT_HANDLER);
            FMLCommonHandler.instance().bus().register(EVENT_HANDLER);

            logger = event.getModLog();
            configLoader = new ConfigurationLoader(config);
            try {
                configLoader.load();
            } catch (Exception e) {
                logger.log(Level.ERROR, "Error loading config file.");
                throw new RuntimeException(e);
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ConfigManager.sync(MODID, Config.Type.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new DynamicEventHandler());
        commonProxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        commonProxy.postInit(event);
    }

    @SideOnly(Side.CLIENT)
    public void reload() {
        foxesworld.hardcontent.gui.mainmenu.configuration.Config backup = config;
        config = new foxesworld.hardcontent.gui.mainmenu.configuration.Config();
        configLoader = new ConfigurationLoader(config);
        try {
            configLoader.load();
            EVENT_HANDLER.displayMs = -1;
        } catch (Exception e) {
            e.printStackTrace();

            EVENT_HANDLER.displayMs = System.currentTimeMillis();
            logger.log(Level.ERROR, "Error while loading new config file, trying to keep the old one loaded.");
            config = backup;
        }
    }
}
