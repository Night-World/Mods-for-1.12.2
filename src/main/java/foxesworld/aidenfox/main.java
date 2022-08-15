package foxesworld.aidenfox;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.proxy.CommonProxy;
import foxesworld.aidenfox.stuff.sounds.Sounds;
import foxesworld.aidenfox.stuff.world.WorldGen;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static foxesworld.aidenfox.cfg.ConfigCreator.initConfig;

@Mod(modid = Environment.MODID, name = Environment.NAME, version = Environment.VERSION)
public class main {
    @SidedProxy(clientSide = Environment.clientsideProxy, serverSide = Environment.serversideProxy)

    public static CommonProxy proxy;
    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        initConfig(event);
        Sounds Sounds = new Sounds("sounds.json", Environment.MODID);
        ContentInit contentInit = new ContentInit();
        GameRegistry.registerWorldGenerator(new WorldGen(), 3);
        registerItems();
        registerBlocks();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static void registerItems() {
        for (Item item : Environment.ITEMS) {
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
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
}
