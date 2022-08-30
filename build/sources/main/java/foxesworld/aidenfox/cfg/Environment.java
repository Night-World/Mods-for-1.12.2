package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.dynamic.world.StructureGen.parser.StructureParser;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environment {

    public static final String MODID = "foxesmod";
    public static final String NAME = "Hard Tactical tools";
    public static final String VERSION = "1.13.6-SNAPSHOT";
    public static final String CFGNAME = "hardtools.cfg";
    public static final String clientsideProxy = "foxesworld.aidenfox.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.aidenfox.proxy.CommonProxy";
    public static Map<String, Block> BLOCKS = new HashMap<>();
    public static Map<String, Item> ITEMS = new HashMap<>();
    public static Map<String, SoundEvent> SOUNDS = new HashMap();
    public static Map<String, Item.ToolMaterial> MATERIALS = new HashMap();
    public static final ArrayList<StructureParser> STRUCTURES = new ArrayList<StructureParser>();
}
