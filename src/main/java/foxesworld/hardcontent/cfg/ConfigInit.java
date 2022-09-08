package foxesworld.hardcontent.cfg;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import foxesworld.hardcontent.cfg.ConfigStructure.CONFIGdatagen;
import foxesworld.hardcontent.cfg.ConfigStructure.CONFIGgeneral;
import foxesworld.hardcontent.cfg.ConfigStructure.CONFIGgenerate;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static foxesworld.hardcontent.cfg.Environment.*;
import static net.minecraftforge.common.config.Config.Type.INSTANCE;

@Config(modid = MODID, name = CfgName)
public class ConfigInit {

    LinkedList<String> langList;

    @Config.LangKey(Environment.MODID + ".cfg.sub.general")
    @Config.Comment("The mod's general comfiguration")
    public static CONFIGgeneral CONFIGgeneral = new CONFIGgeneral();

    @Config.LangKey(Environment.MODID + ".cfg.sub.generate")
    @Config.Comment("Mod registry list")
    public static CONFIGgenerate CONFIGgenerate = new CONFIGgenerate();

    @Config.LangKey(Environment.MODID + ".cfg.sub.exportReading")
    @Config.Comment("Data to parse from config")
    public static CONFIGdatagen CONFIGdatagen = new CONFIGdatagen();

    public ConfigInit() {
        genFolders();
 /*       String langDir = "/assets/" + MODID + "/lang/";
        try {
            langList = loadLanguageList(this.getClass()
                    .getClassLoader().getResourceAsStream((langDir)));
        } catch (Throwable e) {
            e.printStackTrace();
        } */
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

    private LinkedList<String> loadLanguageList(InputStream stream) throws Throwable {

        return loadLanguageList(stream, new LinkedList<String>());
    }

    private LinkedList<String> loadLanguageList(InputStream stream, LinkedList<String> list) throws Throwable {

        JsonReader r = new JsonReader(new InputStreamReader(stream, "UTF-8"));
        // doesn't matter if it's strictly JSON, so long as it's a list
        r.setLenient(true);
        if (r.peek() == JsonToken.BEGIN_ARRAY) {
            r.beginArray();
        }
        builder: while (true) {
            switch (r.peek()) {
                case END_ARRAY:
                case END_DOCUMENT: // doesn't matter if the array is valid
                    break builder;
                default: // require only strings
                case STRING:
                    list.add(r.nextString());
            }
        }
        r.close();

        return list;
    }

    @Mod.EventBusSubscriber(modid = Environment.MODID)
    public static class ConfigEvents {

        @SubscribeEvent
        public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Environment.MODID)) {
                ConfigManager.sync(Environment.MODID, INSTANCE);
            }
        }
    }

}

