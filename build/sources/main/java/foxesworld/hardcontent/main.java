package foxesworld.hardcontent;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.proxy.CommonProxy;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import static foxesworld.hardcontent.cfg.Environment.MODID;
import static foxesworld.hardcontent.cfg.Environment.acceptedVersions;

@Mod(modid = MODID, name = Environment.NAME, version = Environment.VERSION, acceptedMinecraftVersions = acceptedVersions)

public class main extends Environment {
    @SidedProxy(clientSide = Environment.clientsideProxy, serverSide = Environment.serversideProxy)
    public static CommonProxy proxy;
    public static Logger logger;

    @Mod.Instance(MODID)
    public static main instance;

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ConfigManager.sync(MODID, Type.INSTANCE);
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static main getMod(){
        return instance;
    }
}
