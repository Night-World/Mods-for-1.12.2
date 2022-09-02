package foxesworld.hardcontent.dynamic.sounds;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.methods.FileAsStream;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static foxesworld.hardcontent.methods.Utils.debugSend;

public class Sounds {

    /*INPUT*/
    private static String SoundsFile;
    private static String modDir;

    /*INTERNAL*/
    private FileAsStream soundScan;
    private Object JsonString;
    private Gson gson = new Gson();

    public Sounds(String soundsFile) {
        this.SoundsFile = soundsFile;
        this.modDir = Environment.MODID;
    }
    public void registerSounds(){
        soundScan = new FileAsStream(this.SoundsFile, modDir);
        JsonString = soundScan.getFileContents();
        HashMap<String, Object> SoundsMap = gson.fromJson((String) JsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
        for (Map.Entry entry : SoundsMap.entrySet()) {
            Environment.SOUNDS.put((String) entry.getKey(), regSnd((String) entry.getKey()));
        }
    }
    private static SoundEvent regSnd(String id) {
        debugSend("Registering soundgroup " + id);
        ResourceLocation soundID = new ResourceLocation(Environment.MODID, id);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
    @Mod.EventBusSubscriber(modid = Environment.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
            for (Map.Entry entry : Environment.SOUNDS.entrySet()) {
                debugSend("GameEvent register - " + entry.getValue());
                event.getRegistry().register((SoundEvent) entry.getValue());
            }
        }
    }
}