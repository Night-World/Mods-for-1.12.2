package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static foxesworld.aidenfox.methods.Utils.debugSend;

public class RegData {

    //ResourceLocation resourcelocation = new ResourceLocation(MODCFGDIR + generatedDirName);

    public RegData() {}

   public static void regItems() {
        for (Map.Entry entry : Environment.ITEMS.entrySet()) {
            net.minecraft.item.Item thisItem = (net.minecraft.item.Item) entry.getValue();
            final ResourceLocation regName = thisItem.getRegistryName();
            final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
            ForgeRegistries.ITEMS.registerAll(thisItem);
            ModelBakery.registerItemVariants(thisItem, mrl);
            ModelLoader.setCustomModelResourceLocation(thisItem, 0,  mrl);
        }
    }

    public static void regBlocks() {
        for (Map.Entry entry : Environment.BLOCKS.entrySet()) {
            Block block = (Block) entry.getValue();
            debugSend("Registering " + block.getRegistryName());
            ForgeRegistries.BLOCKS.register(block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }

    }

    private static void resourceLocation(String pathToDir){
        FileSystem filesystem = null;

        URL url = main.class.getResource(pathToDir);

        try
        {
            if (url != null)
            {
                URI uri = url.toURI();
                Path path = null;

                if ("file".equals(uri.getScheme()))
                {
                    path = Paths.get(main.class.getResource(pathToDir).toURI());
                }
                else
                {

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath(pathToDir);

                }

                Iterator<Path> it = Files.walk(path).iterator();

                while(it.hasNext())
                    System.out.println(it.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}