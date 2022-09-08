package foxesworld.hardcontent.proxy;

import foxesworld.hardcontent.cfg.ConfigInit;
import foxesworld.hardcontent.data.LoadData;
import foxesworld.hardcontent.dynamic.world.StructureGen.parser.RegistryHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static foxesworld.hardcontent.cfg.Environment.MODCFGDIR;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MODCFGDIR = String.valueOf(event.getModConfigurationDirectory());
        new ConfigInit();
        LoadData loadData = new LoadData();
        loadData.loadContent();
        RegistryHandler.preInitRegistries();
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}