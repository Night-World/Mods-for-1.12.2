package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.material.parser.MaterialParser;
import foxesworld.aidenfox.proxy.CommonProxy;
import foxesworld.aidenfox.sounds.Sounds;
import foxesworld.aidenfox.stuff.blocks.parser.BlocksParser;
import foxesworld.aidenfox.stuff.tools.Tools;
import foxesworld.aidenfox.world.OreGen.parser.OreGenParser;
import foxesworld.aidenfox.world.StructureGen.parser.StructureParser;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

        MaterialParser materials= new MaterialParser("options/material.json", Environment.MODID);
            materials.readTplFile();

        BlocksParser blockParser = new BlocksParser("options/blocks.json", Environment.MODID);
            blockParser.readTplFile();

        Tools toolsParser = new Tools("options/tools.json", Environment.MODID);
            toolsParser.readTplFile();

        Content content = new Content();
            content.registerItems();
            content.registerBlocks();

        OreGenParser oreGen = new OreGenParser("options/oreGen.json", Environment.MODID);
            oreGen.readTplFile();

        StructureParser structures = new StructureParser("options/structures.json", Environment.MODID);
            structures.readTplFile();
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
