package foxesworld.aidenfox.stuff.effects;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class Effects {

    public static PotionEffect randomEffect(Integer foodLevel){
        PotionEffect effect = null;
        switch(foodLevel){
            case 1:
                effect = new PotionEffect(MobEffects.WITHER, 300);
                break;

            case 2:
                effect = new PotionEffect(MobEffects.SPEED, 250);
                break;

            case 3:
                effect = new PotionEffect(MobEffects.POISON, 120);
                break;

            case 4:
                effect = new PotionEffect(MobEffects.REGENERATION, 320);
                break;

            case 5:
                effect = new PotionEffect(MobEffects.STRENGTH, 920);
                break;

            default:
                effect = new PotionEffect(MobEffects.NIGHT_VISION, 1500);
                break;
        }

        return effect;
    }

}
