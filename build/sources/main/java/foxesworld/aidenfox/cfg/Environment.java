package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.world.structureGen.StructureParser;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    public static final String MODID = "foxesmod";
    public static final String NAME = "Hard Tactical tools";
    public static final String VERSION = "1.9.0-SNAPSHOT";
    public static final String CFGNAME = "hardtools.cfg";
    public static final String clientsideProxy = "foxesworld.aidenfox.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.aidenfox.proxy.CommonProxy";
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
    public static final ArrayList<StructureParser> STRUCTURES = new ArrayList<StructureParser>();

    public static Map<String, SoundEvent> SOUNDS = new HashMap();
    public static Map<String, Object> cfgMap = new HashMap();
    public static Minecraft mc = Minecraft.getMinecraft();
}
