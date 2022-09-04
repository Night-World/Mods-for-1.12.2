package foxesworld.hardcontent.cfg;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

import static foxesworld.hardcontent.cfg.Environment.*;

@Config(modid = MODID, name = CfgName)
public class ConfigCreator {

    @Config.LangKey(Environment.MODID + ".cfg.sub.general")
    @Config.Comment("The mod's heneral comfiguration")
    public static CONFIGgeneral CONFIGgeneral = new CONFIGgeneral();

    @Config.LangKey(Environment.MODID + ".cfg.sub.generate")
    @Config.Comment("Mod registry list")
    public static CONFIGgenerate CONFIGgenerate = new CONFIGgenerate();

    @Config.LangKey(Environment.MODID + ".cfg.sub.exportReading")
    @Config.Comment("Data to parse from config")
    public static  CONFIGdatagen CONFIGdatagen = new CONFIGdatagen();

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

    public static class CONFIGgeneral {
        @Config.LangKey(Environment.MODID + ".cfg.general.debug")
        public Boolean debug = false;
    }

    public static class CONFIGdatagen {
        @Config.LangKey(Environment.MODID + ".cfg.datagen.materials")
        public boolean exportMaterials = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.blocks")
        public boolean exportBlocks = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.tools")
        public boolean exportTools = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.items")
        public boolean exportItems = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.ores")
        public boolean exportOreGen = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.structures")
        public boolean exportStructures = true;
    }

    public static class CONFIGgenerate {
        @Config.Comment("Generate structures in world")
        @Config.LangKey(Environment.MODID + ".cfg.generate.structureGen")
        public boolean structureGen = true;
        @Config.Comment("Generate ores in world")
        @Config.LangKey(Environment.MODID + ".cfg.generate.oreGen")
        public boolean oreGen = true;
        @Config.Comment("Should we register materials")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regMaterials")
        public boolean regMaterials = true;
        @Config.Comment("Should we register items")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regItems")
        public boolean regItems = true;
        @Config.Comment("Should we register tools")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regTools")
        public boolean regTools = true;
        @Config.Comment("Should we register blocks")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regBlocks")
        public boolean regBlocks = true;
    }

    @Mod.EventBusSubscriber(modid = Environment.MODID)
    public static class ConfigEvents {

        @SubscribeEvent
        public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
            if (event.getModID().equals(Environment.MODID)){
                ConfigManager.sync(Environment.MODID, Config.Type.INSTANCE);
            }
        }
    }
}

