package foxesworld.hardcontent.cfg;

import foxesworld.hardcontent.dynamic.world.StructureGen.parser.StructureParser;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environment {

    //@Mod
    public static final String MODID = "foxesmod";
    public static final String NAME = "HardContent creator";
    public static final String VERSION = "1.16.5-ALPHA";
    public static final String acceptedVersions = "[1.12.2]";

    //Proxy
    public static final String clientsideProxy = "foxesworld.hardcontent.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.hardcontent.proxy.CommonProxy";

    //CFG
    public static String MODCFGDIR = "";
    public static final String cfgExtension = ".cfg";
    public static final String CFGNAME = "hardcontent";
    public static final String CFGDIR = MODID + File.separator + CFGNAME + cfgExtension;

    //Content Directories
    public static final String dataTemplateDir = "dataTemplates/";
    public static final String dataExportDir = File.separator + "data" + File.separator;
    public static final String generatedDirName = File.separator + "generated" + File.separator;

    //Content MAPS
    public static Map<String, net.minecraft.block.Block> BLOCKS = new HashMap<>();
    public static Map<String, Item> ITEMS = new HashMap<>();
    public static Map<String, SoundEvent> SOUNDS = new HashMap();
    public static Map<String, Item.ToolMaterial> MATERIALS = new HashMap();
    public static final ArrayList<StructureParser> STRUCTURES = new ArrayList<StructureParser>();
}
