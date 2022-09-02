package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

import static foxesworld.aidenfox.methods.Utils.debugSend;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

public class RegData {

    private ResourceLocation assetsLocation;

    public static void regItems() {
        for (Map.Entry entry : Environment.ITEMS.entrySet()) {
            Item thisItem = (Item) entry.getValue();
            ForgeRegistries.ITEMS.registerAll(thisItem);
            itemRenderer(thisItem);
        }
    }

    public static void regBlocks() {
        for (Map.Entry entry : Environment.BLOCKS.entrySet()) {
            Block block = (Block) entry.getValue();
            ForgeRegistries.BLOCKS.register(block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            blockRenderer(block);
        }
    }

    @SideOnly(CLIENT)
    private static void itemRenderer(Item thisItem) {
        final ResourceLocation regName = thisItem.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        debugSend("Registering render of  " + regName);
        ModelBakery.registerItemVariants(thisItem, mrl);
        ModelLoader.setCustomModelResourceLocation(thisItem, 0, mrl);
    }
    @SideOnly(CLIENT)
    private static void blockRenderer(Block thisBlock) {
        final ResourceLocation regName = thisBlock.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        debugSend("Registering render of  " + regName);
        ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(thisBlock), 0, mrl);
    }

    private static String getItemLocation(ResourceLocation regName){
        ResourceLocation location = new ResourceLocation(Environment.MODCFGDIR + Environment.generatedDirName + regName);
        String path = location.getPath();
        return path;
    }


}