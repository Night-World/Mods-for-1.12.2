package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.proxy.CommonProxy;
import foxesworld.aidenfox.sounds.Sounds;
import foxesworld.aidenfox.world.WorldGen;
import foxesworld.aidenfox.world.structureGen.StructureGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Environment.MODID, name = Environment.NAME, version = Environment.VERSION)
public class main {

    @Mod.Instance
    public static main instance;
    @SidedProxy(clientSide = Environment.clientsideProxy, serverSide = Environment.serversideProxy)

    public static CommonProxy proxy;
    public static Logger logger;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        ConfigCreator cfg = new ConfigCreator(Environment.CFGNAME, event);
            cfg.initCfg();
        Sounds sounds = new Sounds("sounds.json", Environment.MODID);
            sounds.registerSounds();
        Content content = new Content();
            content.registerItems();
            content.registerBlocks();
        GameRegistry.registerWorldGenerator(new WorldGen(), 3);
        StructureGenerator StructureGenerator = new StructureGenerator("structures.json", Environment.MODID);
            StructureGenerator.readTplFile();
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
