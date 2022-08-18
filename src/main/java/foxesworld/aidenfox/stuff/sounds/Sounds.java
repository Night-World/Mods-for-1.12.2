package foxesworld.aidenfox.stuff.sounds;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static foxesworld.aidenfox.cfg.Environment.SOUNDS;
import static foxesworld.aidenfox.util.Utils.debugSend;

public class Sounds {

    /*INPUT*/
    private static String SoundsFile = "sounds.json";
    private static String modDir;

    /*INTERNAL*/
    private FileAsStream soundScan;
    private Object JsonString;
    private Gson gson = new Gson();

    public Sounds(String soundsFile, String modDir) {
        this.SoundsFile = soundsFile;
        this.modDir = modDir;
    }

    public void scanSounds(){
        //ResourceLocation JsonFile = new ResourceLocation(modDir, this.SoundsFile);
        soundScan = new FileAsStream(this.SoundsFile, modDir);
        JsonString = soundScan.getFileContents();
        //Type FullSoundsMap = new TypeToken<HashMap<String, Object>>() {}.getType();
        HashMap<String, Object> SoundsMap = gson.fromJson((String) JsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
        for (Map.Entry entry : SoundsMap.entrySet()) {
            SOUNDS.put((String) entry.getKey(), registerSound((String) entry.getKey()));
        }
    }

    private static SoundEvent registerSound(String id) {
        debugSend("Registering sound " + id);
        ResourceLocation soundID = new ResourceLocation(Environment.MODID, id);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    @Mod.EventBusSubscriber(modid = Environment.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
            for (Map.Entry entry : SOUNDS.entrySet()) {
                debugSend("GameEvent register - " + entry.getValue());
                event.getRegistry().register((SoundEvent) entry.getValue());
            }
        }
    }
}