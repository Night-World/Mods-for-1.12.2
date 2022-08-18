package foxesworld.aidenfox;

/* IMPORTING CONTENT */
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.stuff.blocks.*;
import foxesworld.aidenfox.stuff.items.Item;
import foxesworld.aidenfox.stuff.tools.*;
import foxesworld.aidenfox.stuff.food.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;

public class Content {

    /* BLOCKS */
    public static Block hardened_dirt;
    public static Block hardened_gravel;
    public static Block marble_raw;
    public static Block marble_bricks;
    public static Block stone_bricks;

    /*TOOLS*/
    public static net.minecraft.item.Item dirtpickaxe;
    public static net.minecraft.item.Item dirtaxe;
    public static net.minecraft.item.Item dirtspade;
    public static net.minecraft.item.Item dirtsword;
    public static net.minecraft.item.Item dirthoe;

    /*FOOD*/
    public static Food lapis_apple;

    /*ITEMS*/
    public static net.minecraft.item.Item debug_item;

    /*MATERIAL*/
    public static final net.minecraft.item.Item.ToolMaterial DIRT_MATERIAL =
    EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability);

    public Content() {

        /*ITEMS*/
        debug_item = new Item("debug_item", true){};

        /*BLOCKS*/
        stone_bricks = new Blocks("stone_bricks", true, Material.ROCK, 0, SoundType.STONE, "pickaxe", 1, 7.0F, 6.0F){};
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

    public static void registerItems() {
        for (net.minecraft.item.Item item : Environment.ITEMS) {
            final ResourceLocation regName = item.getRegistryName();
            final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
            ForgeRegistries.ITEMS.registerAll(item);
            ModelBakery.registerItemVariants(item, mrl);
            ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
        }
    }

    public static void registerBlocks() {
        for (Block block : Environment.BLOCKS) {
            ForgeRegistries.BLOCKS.register(block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
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