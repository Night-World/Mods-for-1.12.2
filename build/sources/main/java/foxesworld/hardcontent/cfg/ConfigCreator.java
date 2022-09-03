package foxesworld.hardcontent.cfg;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

import static foxesworld.hardcontent.cfg.Environment.*;

public class ConfigCreator {

    protected static final String CfgName = MODID+'/'+CFGNAME;

    public ConfigCreator(){
        genFolders();
    }


    private static void genFolders() {
        String[] folders = {dataExportDir, generatedDirName};
        for (String dir : folders) {
            File workFolder = new File(MODCFGDIR + File.separator + MODID + File.separator + dir);
            if (!workFolder.exists()) {
                workFolder.mkdir();
            }
        }
    }

    @Config(modid = MODID, name = CfgName, category = "general")
    public static class CONFIG {


        public static Boolean debug = false;

        /*ContentOptions*/
        public static boolean structureGen = true;
        public static boolean oreGen = true;
        public static boolean regMaterials = true;
        public static boolean regItems = true;
        public static boolean regTools = true;
        public static boolean regBlocks = true;
        /*DataGen*/
        public static boolean exportMaterials = true;
        public static boolean exportBlocks = true;
        public static boolean exportTools = true;
        public static boolean exportItems = true;
        public static boolean exportOreGen = true;
        public static boolean exportStructures = true;

    }

    public static class ConfigEvents {
        @SubscribeEvent
        public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
            if (event.getModID().equals(Environment.MODID)){
                ConfigManager.sync(Environment.MODID, Config.Type.INSTANCE);
            }
        }
    }
}

