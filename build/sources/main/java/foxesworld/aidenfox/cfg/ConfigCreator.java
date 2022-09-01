package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigCreator {

    public static Boolean debug = false;
    private static String cfgName = "hardtools.cfg";
    private static final String CATEGORY_GENERAL = "General";
    private static final String ContentOptions = "ContentOptions";

    /*ContentOptions*/
    public static boolean structureGen = true;
    public static boolean oreGen = true;
    public static boolean regMaterials = true;
    public static boolean regItems = true;
    public static boolean regBlocks = true;


    public ConfigCreator(String cfgName, FMLPreInitializationEvent e) {
        this.cfgName = cfgName;
        CommonProxy.config = new Configuration(new File(e.getModConfigurationDirectory().getPath(), ConfigCreator.cfgName));
        //JsonGenerator JsonGenerator = new JsonGenerator(e.getModConfigurationDirectory());
    }

    public static void initCfg() {
        readConfig();
    }

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {

        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General HardTools Configuration Version." + Environment.VERSION);
        debug = cfg.getBoolean("debug", CATEGORY_GENERAL, false, "Debug logging");

        cfg.addCustomCategoryComment(ContentOptions, "What content to enable");
        structureGen = cfg.getBoolean("structureGem", ContentOptions, true, "Generate structures in world");
        oreGen = cfg.getBoolean("oreGen", ContentOptions, true, "Generate ores in world");
        regMaterials = cfg.getBoolean("regMaterial", ContentOptions, true, "Should we register materials");
        regItems = cfg.getBoolean("regItems", ContentOptions, true, "Should we register Ites (Tools and Food also)");
        regBlocks = cfg.getBoolean("regBlocks", ContentOptions, true, "Should we register Blocks");
    }

}