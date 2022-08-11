package foxesworld.aidenfox.cfg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    public static final String MODID = "foxesmod";
    public static final String NAME = "First modiffication";
    public static final String VERSION = "1.2";
    public static final String clientsideProxy = "foxesworld.aidenfox.proxy.ClientProxy";
    public static final String serversideProxy = "foxesworld.aidenfox.proxy.CommonProxy";

    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
}
