package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigCreator {

    private static final String CATEGORY_GENERAL = "General";
    private static final String CATEGORY_WORLDGEN= "WorldGen";
    public static Boolean debug = false;

    /*TOOLS*/
    public static float materialEfficiency = 2.1F;
    public static float materialDamage = 1.1F;
    public static Integer materialHarvestLevel = 1;
    public static Integer materialEnchantability = 8;
    public static  String fixMaterial = "DIRT";
    public static Integer fixMaterialMeta = 1;
    public static Integer fixMaterialAmmount = 8;

    /*GENERATIONS*/
    public static boolean genMarble = true;
    public static  Integer marbleMinHeight = 19;
    public static  Integer marbleMaxHeight = 80;
    public static Integer marbleVeinSize = 28;
    public static Integer marbleSpawnTries = 4;

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

    public static void initConfig(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.config = new Configuration(new File(directory.getPath(), "hardtools.cfg"));
        readConfig();
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General HardTools Configuration");
        debug = cfg.getBoolean("debug", CATEGORY_GENERAL, false, "Debug information" );
        materialEfficiency = cfg.getFloat("materialEfficiency", CATEGORY_GENERAL, 1.5F, 0.01F, 30.0F, "Material efficiency");
        materialDamage = cfg.getFloat("materialDamage", CATEGORY_GENERAL, 1.0F, 0.0F, 20.0F, "Base material damage");
        materialEnchantability = cfg.getInt("materialEnchantability", CATEGORY_GENERAL, 12, 1, 12, "Max enchantment level for tools");
        materialHarvestLevel = cfg.getInt("materialHarvestLevel", CATEGORY_GENERAL, 2, 1, 5, "Max enchantment level for tools");
        fixMaterial = cfg.getString("fixMaterial", CATEGORY_GENERAL, "DIRT", "Material to fix your tool");
        fixMaterialMeta = cfg.getInt("fixMaterialMeta", CATEGORY_GENERAL, 1, 0, 255, "Meta of your fix material");
        fixMaterialAmmount = cfg.getInt("fixMaterialAmmount", CATEGORY_GENERAL, 8, 1, 255, "Ammount of material to fix");

        cfg.addCustomCategoryComment(CATEGORY_WORLDGEN, "WorldGeneration configuration");
        genMarble = cfg.getBoolean("genMarble", CATEGORY_WORLDGEN, true, "Generate marble in world");

    }

}