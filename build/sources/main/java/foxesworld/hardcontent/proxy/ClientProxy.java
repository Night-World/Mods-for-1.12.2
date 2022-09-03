package foxesworld.hardcontent.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        //NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        //ConfigManager.sync(Environment.MODID, Type.INSTANCE);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

    }
}