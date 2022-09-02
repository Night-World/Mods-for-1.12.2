package foxesworld.hardcontent.proxy;

import foxesworld.hardcontent.cfg.ConfigCreator;
import foxesworld.hardcontent.data.LoadData;
import foxesworld.hardcontent.dynamic.world.StructureGen.parser.RegistryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static foxesworld.hardcontent.cfg.Environment.MODCFGDIR;

public class CommonProxy {
    public static Configuration config;
    public static EntityPlayer playerInstance;

    public void preInit(FMLPreInitializationEvent event) {
        MODCFGDIR = String.valueOf(event.getModConfigurationDirectory());
        new ConfigCreator();
        LoadData loadData = new LoadData();
        loadData.loadContent();
        RegistryHandler.preInitRegistries();

    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ConfigCreator.ConfigEvents());
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}