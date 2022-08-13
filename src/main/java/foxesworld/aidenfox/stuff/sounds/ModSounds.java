package foxesworld.aidenfox.stuff.sounds;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class ModSounds {

    public static SoundEvent SUCCESS;
    public static SoundEvent FAIL;
    public static SoundEvent ASK;

    public static void init() {
        SUCCESS = registerSound("item.action.success");
        FAIL = registerSound("event.action.fail");
        ASK  = registerSound("event.action.ask");
    }

    private static SoundEvent registerSound(String id) {
        ResourceLocation soundID = new ResourceLocation(Environment.MODID, id);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    @Mod.EventBusSubscriber(modid = Environment.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(
                    SUCCESS,
                    FAIL,
                    ASK
            );
        }
    }
}