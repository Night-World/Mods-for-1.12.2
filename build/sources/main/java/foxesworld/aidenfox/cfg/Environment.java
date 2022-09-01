package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.dynamic.world.StructureGen.parser.StructureParser;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environment {

    public static final String MODID = "foxesmod";
    public static final String NAME = "HardContent creator";
    public static final String VERSION = "1.14.9-SNAPSHOT";
    public static String MODCFGDIR = "";
    public static final String CFGNAME = MODID+"/hardtools.cfg";
    public static String paramsDirName = "/data/";
    public static final String clientsideProxy = "foxesworld.aidenfox.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.aidenfox.proxy.CommonProxy";
    public static Map<String, net.minecraft.block.Block> BLOCKS = new HashMap<>();
    public static Map<String, Item> ITEMS = new HashMap<>();
    public static Map<String, SoundEvent> SOUNDS = new HashMap();
    public static Map<String, Item.ToolMaterial> MATERIALS = new HashMap();
    public static final ArrayList<StructureParser> STRUCTURES = new ArrayList<StructureParser>();
}
