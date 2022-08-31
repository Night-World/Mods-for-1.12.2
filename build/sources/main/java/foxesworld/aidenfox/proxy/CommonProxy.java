package foxesworld.aidenfox.proxy;

import foxesworld.aidenfox.dynamic.world.StructureGen.parser.RegistryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public static Configuration config;
    public static EntityPlayer playerInstance;

    public void preInit(FMLPreInitializationEvent event) {

        RegistryHandler.preInitRegistries();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }

}