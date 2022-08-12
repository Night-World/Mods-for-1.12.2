package foxesworld.aidenfox;

/* IMPORTING CONTENT */
import foxesworld.aidenfox.stuff.blocks.*;
import foxesworld.aidenfox.stuff.tools.*;
import foxesworld.aidenfox.stuff.food.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.util.Objects;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;

public class ContentInit {

    /* BLOCKS */
    public static Block hardened_dirt;
    public static Block marble_raw;
    public static Block marble_bricks;

    public static Item dirtpickaxe;
    public static Item dirtaxe;
    public static Item dirtspade;
    public static Item dirtsword;
    public static Item dirthoe;

    public static Food lapis_apple;

    public static final Item.ToolMaterial DIRT_MATERIAL =
    EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability);

    public ContentInit() {

        hardened_dirt = new Blocks("hardened_dirt", Material.GROUND, 5, SoundType.GROUND, "shovel", 1, 2.0F, 5.0F){};
        marble_raw = new Blocks("marble_raw", Material.ROCK, 0, SoundType.STONE, "pickaxe", 1, 2.8F, 6.0F) {};
        marble_bricks = new Blocks("marble_bricks", Material.CLAY, 0, SoundType.STONE, "pickaxe", 1, 5.0F, 8.0F){};
        lapis_apple = new Food("lapis_apple", 4, 1, false, true,true){};
        dirtaxe  = new Axe("dirtaxe", DIRT_MATERIAL);
        dirtspade = new Spade("dirtspade", DIRT_MATERIAL);
        dirthoe = new Hoe("dirthoe", DIRT_MATERIAL);
        dirtsword = new Sword("dirtsword", DIRT_MATERIAL);
        dirtpickaxe = new Pickaxe("dirtpickaxe", DIRT_MATERIAL);
        DIRT_MATERIAL.setRepairItem(new ItemStack(Blocks.getBlockFromName(fixMaterial), fixMaterialAmmount, fixMaterialMeta));
        System.out.println("Fix material is - "+fixMaterial + ":"+fixMaterialMeta);

    }

}