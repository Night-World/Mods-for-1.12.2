package foxesworld.aidenfox.stuff.effects;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Effects {

    private static Boolean goodEffect = false;

    static Potion[] ourEffects = new Potion[]{
            MobEffects.SPEED,
            MobEffects.STRENGTH,
            MobEffects.NIGHT_VISION,
            MobEffects.HASTE,
            MobEffects.JUMP_BOOST,
            MobEffects.LUCK,
            MobEffects.INSTANT_HEALTH,
            MobEffects.REGENERATION,
            MobEffects.GLOWING,
            MobEffects.WITHER,
            MobEffects.POISON,
            MobEffects.HUNGER,
            MobEffects.UNLUCK,
            MobEffects.WEAKNESS,
            MobEffects.NAUSEA,
            MobEffects.BLINDNESS,
            MobEffects.INSTANT_DAMAGE,
            MobEffects.SLOWNESS};
    static Integer effectCount = ourEffects.length;

  public static PotionEffect randomEffect(Integer foodLevel) {
        PotionEffect effect = null;
        Integer randomInt = new Random().ints(0, foodLevel - 1).iterator().nextInt();
        effect = new PotionEffect(ourEffects[randomInt], effectCount * foodLevel / randomInt);
        /* for(int k = 0; foodLevel >= effectCount; foodLevel--) {
            if(effectCount + 1 >= foodLevel){
                effect = new PotionEffect(ourEffects[foodLevel], effectCount * foodLevel / 100);
                break;
            } else {
                continue;
            }
        }
/*	 PotionEffect effect = null;

        for (Integer i = 0; i < foodLevel; i++) {
            ourEffects = shuffleArray(ourEffects);
        }

        for (Integer k; foodLevel >= effectCount; foodLevel--) {
            if (foodLevel <= effectCount) {
                Integer effectLength = foodLevel * 100 / effectCount;
                effect = new PotionEffect(ourEffects[foodLevel], effectLength);
                break;
            }
        } */

        return effect;
    }

    static Potion[] shuffleArray(Potion[] ar) {
        Random rnd = ThreadLocalRandom.current();
        Potion a = null;
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return new Potion[]{a};
    }

}
