package foxesworld.aidenfox.cfg;

import foxesworld.aidenfox.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static foxesworld.aidenfox.cfg.Environment.MODID;

public class ConfigCreator {

    private static final String CATEGORY_GENERAL = "aGeneral";

    public static boolean mobsDisable = false;
    public static boolean disableContent = false;
    public static float materialEfficiency = 1.0F;
    public static float materialDamage = 1.0F;
    public static Integer materialHarvestLevel = 2;
    public static Integer materialEnchantability = 12;

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
        CommonProxy.config = new Configuration(new File(directory.getPath(), MODID+".cfg"));
        readConfig();
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General HardTools Configuration");
        materialEfficiency = cfg.getFloat("materialEfficiency", CATEGORY_GENERAL, 1.5F, 0.01F, 30.0F, "Material efficiency");
        materialDamage = cfg.getFloat("materialDamage", CATEGORY_GENERAL, 1.0F, 0.0F, 20.0F, "Base material damage");
        materialEnchantability = cfg.getInt("materialEnchantability", CATEGORY_GENERAL, 12, 1, 12, "Max enchantment level for tools");
        materialHarvestLevel = cfg.getInt("materialHarvestLevel", CATEGORY_GENERAL, 2, 1, 5, "Max enchantment level for tools");
    }

}