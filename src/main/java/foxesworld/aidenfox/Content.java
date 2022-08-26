package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.stuff.blocks.Blocks;
import foxesworld.aidenfox.stuff.food.Food;
import foxesworld.aidenfox.stuff.items.Item;
import foxesworld.aidenfox.util.AppleEaten;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static foxesworld.aidenfox.cfg.ConfigCreator.*;
import static foxesworld.aidenfox.cfg.Environment.SOUNDS;
import static foxesworld.aidenfox.util.Utils.debugSend;

public class Content {


    /*FOOD*/
    public static Food lapis_apple;

    /*ITEMS*/
    public static net.minecraft.item.Item debug_item;
    public static net.minecraft.item.Item staff;

    /*MATERIAL*/
    public static final net.minecraft.item.Item.ToolMaterial DIRT_MATERIAL
            = EnumHelper.addToolMaterial("foxesmod:dirt", materialHarvestLevel, 56, materialEfficiency, materialDamage, materialEnchantability)
            .setRepairItem(new ItemStack(Blocks.getBlockFromName(fixMaterial), fixMaterialAmmount, fixMaterialMeta));

    public Content() {

        /*ITEMS*/
        debug_item = new Item("debug_item") {
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
        staff = new Item("staff") {
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
            debugSend("Registering " + block.getRegistryName());
            ForgeRegistries.BLOCKS.register(block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
}