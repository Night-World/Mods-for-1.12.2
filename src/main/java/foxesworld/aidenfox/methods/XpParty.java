package foxesworld.aidenfox.methods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import static foxesworld.aidenfox.methods.Utils.playFoxesSound;

public class XpParty {

    private static String soundPlay = "event.action.warn";
    //private SpawnEntity eSpawner;
    private static Thread XpParty;


    public static void XpParty(double size, Integer points, EntityPlayer player, World worldIn, SpawnEntity eSpawner) {
        XpParty = new Thread(new Runnable()  {
            public void run() {
                int playerHunger = player.getFoodStats().getFoodLevel();
                playFoxesSound(worldIn, player, soundPlay);

                for (int i = 0; i < 360; i += 360 / points) {
                    double angle = (i * Math.PI / 180);
                    double x = size * Math.cos(angle);
                    double z = size * Math.sin(angle);

                    //worldIn.createExplosion(player, player.posX + x, player.posY, player.posZ + z, 4.0f, true);
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + x, player.posY, player.posZ + z, 0, 0.2D, 0, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, player.posX + x, player.posY, player.posZ + z, 2000D, 800D, 200D);
                    eSpawner.spawnExpBottle(player.posX + x, player.posY, player.posZ + z);
                }
                player.addExperience((int) (playerHunger * 0.5));
                return;
            }
        });
    }

    public static void start() {
        XpParty.start();
    }

    public static void setSoundPlay(String snd){
        soundPlay = snd;
    }
}
