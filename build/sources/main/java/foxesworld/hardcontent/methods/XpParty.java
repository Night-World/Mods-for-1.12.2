package foxesworld.hardcontent.methods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import static foxesworld.hardcontent.methods.Utils.playFoxesSound;

public class XpParty {

    private static String soundPlay = "event.action.warn";
    private static EnumParticleTypes particleTypes = EnumParticleTypes.EXPLOSION_HUGE;
    private static Thread XpParty;


    public static void XpParty(double size, Integer points, EntityPlayer player, World worldIn, SpawnEntity eSpawner) {
                int playerHunger = player.getFoodStats().getFoodLevel();
                playFoxesSound(worldIn, player, soundPlay);

                for (int i = 0; i < 360; i += 360 / points) {
                    double angle = (i * Math.PI / 180);
                    double x = size * Math.cos(angle);
                    double z = size * Math.sin(angle);

                    //worldIn.createExplosion(player, player.posX + x, player.posY, player.posZ + z, 4.0f, true);
                    worldIn.spawnParticle(particleTypes, player.posX + x, player.posY, player.posZ + z, 0, 0.2D, 0, 90);
                    eSpawner.spawnExpBottle(player.posX + x, player.posY, player.posZ + z);
                }
                player.addExperience((int) (playerHunger * 0.5));
                return;
    }

    public static void setSoundPlay(String snd){
        soundPlay = snd;
    }

    public static void setParticle(EnumParticleTypes particle) {
        particleTypes = particle;
    }
}
