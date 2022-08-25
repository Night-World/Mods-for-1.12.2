package foxesworld.aidenfox;

/* IMPORTING CONTENT */

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.stuff.blocks.Blocks;
import foxesworld.aidenfox.stuff.food.Food;
import foxesworld.aidenfox.stuff.items.Item;
import foxesworld.aidenfox.stuff.tools.*;
import foxesworld.aidenfox.util.AppleEaten;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;
import static foxesworld.aidenfox.cfg.Environment.SOUNDS;
import static foxesworld.aidenfox.util.Utils.spawnParticles;

public class Content {

    /* BLOCKS */
    public static Block hardened_dirt;
    public static Block hardened_gravel;
    public static Block marble_raw;
    public static Block marble_bricks;
    public static Block black_marble;

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
    public static final net.minecraft.item.Item.ToolMaterial DIRT_MATERIAL
            = EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability)
            .setRepairItem(new ItemStack(Blocks.getBlockFromName(fixMaterial), fixMaterialAmmount, fixMaterialMeta));

    public Content() {

        /*ITEMS*/
        debug_item = new Item("debug_item", true) {
            @Override
            public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
                worldIn.playSound((EntityPlayer) playerIn,
                        playerIn.posX,
                        playerIn.posY,
                        playerIn.posZ,
                        SOUNDS.get("entity.topielec.hurt"),
                        SoundCategory.NEUTRAL,
                        1.5F, 1F);
                return super.onItemRightClick(worldIn, playerIn, hand);
            }
        };

        /*BLOCKS*/
        black_marble = new Blocks("black_marble", Material.ROCK, SoundType.STONE, "pickaxe", 2, 9.5F, 19.5F) {
            @Override
            public void onBlockClicked(World p_onBlockClicked_1_, BlockPos p_onBlockClicked_2_, EntityPlayer p_onBlockClicked_3_) {
                super.onBlockClicked(p_onBlockClicked_1_, p_onBlockClicked_2_, p_onBlockClicked_3_);
                p_onBlockClicked_3_.spawnSweepParticles();
            }
        };
        hardened_dirt = new Blocks("hardened_dirt", Material.GROUND, SoundType.GROUND, "shovel", 1, 2.0F, 5.0F) {
            @SideOnly(Side.CLIENT)
            public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
                spawnParticles(stateIn, worldIn, pos, rand, 3);
            }
        };
        hardened_gravel = new Blocks("hardened_gravel", Material.GROUND, SoundType.GROUND, "shovel", 1, 4.0F, 6.0F) {
        };
        marble_raw = new Blocks("marble_raw", Material.ROCK, SoundType.STONE, "pickaxe", 1, 2.8F, 6.0F) {
            @SideOnly(Side.CLIENT)
            public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
                spawnParticles(stateIn, worldIn, pos, rand, 0);
            }
        };
        marble_bricks = new Blocks("marble_bricks", Material.STRUCTURE_VOID, SoundType.STONE, "pickaxe", 1, 9.0F, 18.0F) {
        };

        /*FOOD*/
        lapis_apple = new Food("lapis_apple", 4, 1, false, true) {
            @Override
            protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
                super.onFoodEaten(stack, worldIn, player);
                AppleEaten appleEaten = new AppleEaten(16, 8, player, worldIn) {
                };
                appleEaten.start();
                player.getCooldownTracker().setCooldown(this, 50);
            }
        };

        /*TOOLS*/
        dirtaxe = new Axe("dirtaxe",  DIRT_MATERIAL);
        dirtspade = new Spade("dirtspade", DIRT_MATERIAL);
        dirthoe = new Hoe("dirthoe", DIRT_MATERIAL);
        dirtsword = new Sword("dirtsword",DIRT_MATERIAL);
        dirtpickaxe = new Pickaxe("dirtpickaxe",DIRT_MATERIAL);
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
}