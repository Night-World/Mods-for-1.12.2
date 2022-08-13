package foxesworld.aidenfox.cfg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    public static final String MODID = "foxesmod";
    public static final String NAME = "Hard Tactical tools";
    public static final String VERSION = "1.5";
    public static final String clientsideProxy = "foxesworld.aidenfox.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.aidenfox.proxy.CommonProxy";

    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
    public static Map<String, SoundEvent> foxesSounds = new HashMap();
}
