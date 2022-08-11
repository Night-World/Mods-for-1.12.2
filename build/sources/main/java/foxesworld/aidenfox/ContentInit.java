package foxesworld.aidenfox;

/* IMPORTING CONTENT */
import foxesworld.aidenfox.stuff.blocks.*;
import foxesworld.aidenfox.stuff.tools.*;
import foxesworld.aidenfox.stuff.food.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;

public class ContentInit {

    public static Block hardened_dirt;
    public static Block marble_raw;

    public static Item dirtpickaxe;
    public static Item dirtaxe;
    public static Item dirtspade;
    public static Item dirtsword;
    public static Item dirthoe;

    public static Food lapis_apple;

    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final Item.ToolMaterial DIRT_MATERIAL = EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability);

    public ContentInit() {

        hardened_dirt = new Blocks("hardened_dirt", Material.ANVIL){};
        marble_raw = new Blocks("marble_raw", Material.ROCK){};
        lapis_apple = new Food("lapis_apple", 4, 2, false, true,true){};
        dirtaxe  = new Axe("dirtaxe", DIRT_MATERIAL);
        dirtspade = new Spade("dirtspade", DIRT_MATERIAL);
        dirthoe = new Hoe("dirthoe", DIRT_MATERIAL);
        dirtsword = new Sword("dirtsword", DIRT_MATERIAL);
        dirtpickaxe = new Pickaxe("dirtpickaxe", DIRT_MATERIAL);
    }

}