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

import java.io.File;

import static foxesworld.aidenfox.cfg.Environment.*;
import static foxesworld.aidenfox.methods.tplFunctions.readTplFile;

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
        MODCFGDIR = event.getModConfigurationDirectory() + "/" + Environment.MODID;

        ConfigCreator cfg = new ConfigCreator(Environment.CFGNAME, event);
        cfg.initCfg();
        genFolders();
        String paramPath = MODCFGDIR + paramsDirName;

        Sounds sounds = new Sounds("sounds.json");
        sounds.registerSounds();

        MaterialParser materials = new MaterialParser(paramPath, "material.json");
        materials.readFromJson(readTplFile(materials.getFileName(), materials.getFileDir(), false));


        BlocksParser blockParser = new BlocksParser(paramPath, "blocks.json");
        blockParser.readFromJson(readTplFile(blockParser.getFileName(), blockParser.getFileDir(), false));

        Tools toolsParser = new Tools(paramPath, "tools.json");
        toolsParser.readFromJson(readTplFile(toolsParser.getFileName(), toolsParser.getFileDir(), false));


        ItemParser ItemParser = new ItemParser(paramPath, "items.json");
        ItemParser.readFromJson(readTplFile(ItemParser.getFileName(), ItemParser.getFileDir(), false));

        RegData data = new RegData();
        data.regItems();
        data.regBlocks();

        OreGenParser oreGen = new OreGenParser(paramPath, "oreGen.json");
        oreGen.readFromJson(readTplFile(oreGen.getFileName(), oreGen.getFileDir(), false));

        StructureParser structures = new StructureParser(paramPath, "structures.json");
        structures.readFromJson(readTplFile(structures.getFileName(), structures.getFileDir(), false));

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    private static void genFolders() {
    String[] folders = {paramsDirName, generatedDirName};
    for(String dir: folders){
        File workFolder = new File(MODCFGDIR + "/" + dir);
        if (!workFolder.exists()) {
            workFolder.mkdir();
        }
    }
    }
}
