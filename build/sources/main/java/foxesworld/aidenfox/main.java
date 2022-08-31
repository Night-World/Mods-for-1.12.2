package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.dynamic.blocks.parser.BlocksParser;
import foxesworld.aidenfox.dynamic.items.parser.ItemParser;
import foxesworld.aidenfox.dynamic.material.parser.MaterialParser;
import foxesworld.aidenfox.dynamic.sounds.Sounds;
import foxesworld.aidenfox.dynamic.tools.Tools;
import foxesworld.aidenfox.dynamic.world.OreGen.parser.OreGenParser;
import foxesworld.aidenfox.dynamic.world.StructureGen.parser.StructureParser;
import foxesworld.aidenfox.proxy.CommonProxy;
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

        Sounds sounds = new Sounds("sounds.json");
            sounds.registerSounds();

        MaterialParser materials= new MaterialParser("options/material.json");
            materials.readTplFile();

        BlocksParser blockParser = new BlocksParser("options/blocks.json");
            blockParser.readTplFile();

        Tools toolsParser = new Tools("options/tools.json");
            toolsParser.readTplFile();

        ItemParser ItemParser = new ItemParser("options/items.json", Environment.MODID);
            ItemParser.readTplFile();

        RegData data = new RegData();
            data.regItems();
            data.regBlocks();

        OreGenParser oreGen = new OreGenParser("options/oreGen.json");
            oreGen.readTplFile();

        StructureParser structures = new StructureParser("options/structures.json");
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
