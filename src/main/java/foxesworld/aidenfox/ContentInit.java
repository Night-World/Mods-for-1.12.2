package foxesworld.aidenfox;

/* IMPORTING CONTENT */
import foxesworld.aidenfox.stuff.blocks.*;
import foxesworld.aidenfox.stuff.items.Items;
import foxesworld.aidenfox.stuff.tools.*;
import foxesworld.aidenfox.stuff.food.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;

public class ContentInit {

    /* BLOCKS */
    public static Block hardened_dirt;
    public static Block hardened_gravel;
    public static Block marble_raw;
    public static Block marble_bricks;

    /*TOOLS*/
    public static Item dirtpickaxe;
    public static Item dirtaxe;
    public static Item dirtspade;
    public static Item dirtsword;
    public static Item dirthoe;

    /*FOOD*/
    public static Food lapis_apple;

    /*ITEMS*/
    public static Item debug_item;

    public static final Item.ToolMaterial DIRT_MATERIAL =
    EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability);

    public ContentInit() {

        /*ITEMS*/
        debug_item = new Items("debug_item", true){};

        /*BLOCKS*/
        hardened_dirt = new Blocks("hardened_dirt", true, Material.GROUND, 5, SoundType.GROUND, "shovel", 1, 2.0F, 5.0F){};
        hardened_gravel = new Blocks("hardened_gravel", true, Material.GROUND, 4, SoundType.GROUND, "shovel", 1, 4.0F, 6.0F){};
        marble_raw = new Blocks("marble_raw", true, Material.ROCK, 0, SoundType.STONE, "pickaxe", 1, 2.8F, 6.0F) {};
        marble_bricks = new Blocks("marble_bricks", true,  Material.CLAY, 0, SoundType.STONE, "pickaxe", 1, 9.0F, 18.0F){};

        /*FOOD*/
        lapis_apple = new Food("lapis_apple", 	 true, 4, 1, false, true,true){};

        /*TOOLS*/
        dirtaxe  	= new Axe("dirtaxe", 		 true, DIRT_MATERIAL);
        dirtspade 	= new Spade("dirtspade", 	 true, DIRT_MATERIAL);
        dirthoe 	= new Hoe("dirthoe", 		 true, DIRT_MATERIAL);
        dirtsword 	= new Sword("dirtsword", 	 true, DIRT_MATERIAL);
        dirtpickaxe = new Pickaxe("dirtpickaxe", true, DIRT_MATERIAL);
		
        DIRT_MATERIAL.setRepairItem(new ItemStack(Blocks.getBlockFromName(fixMaterial), fixMaterialAmmount, fixMaterialMeta));
        System.out.println("Fix material is - "+fixMaterial + ":"+fixMaterialMeta);

    }
    /*
    public static void onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player){
        worldIn.playSound((EntityPlayer)null,
                player.posX,
                player.posY,
                player.posZ,
                ASK,
                SoundCategory.NEUTRAL,
                0.5F,
                1.4F);
    } */
}