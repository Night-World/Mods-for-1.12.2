package foxesworld.aidenfox.methods;

import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import static foxesworld.aidenfox.cfg.Environment.SOUNDS;

public abstract class AppleEaten {

    private Thread appleEaten;

    public AppleEaten(double size, Integer points, EntityPlayer player, World worldIn) {
        appleEaten = new Thread(new Runnable()  {
            public void run() {
                int playerHunger = player.getFoodStats().getFoodLevel();
                worldIn.playSound((EntityPlayer) player,
                        player.posX,
                        player.posY,
                        player.posZ,
                        SOUNDS.get("event.action.warn"),
                        SoundCategory.NEUTRAL,
                        1.5F, 1F);

                //double circleSize = 8;
                //int points = 16;

                for (int i = 0; i < 360; i += 360 / points) {
                    double angle = (i * Math.PI / 180);
                    double x = size * Math.cos(angle);
                    double z = size * Math.sin(angle);

                    //worldIn.createExplosion(player, player.posX + x, player.posY, player.posZ + z, 4.0f, true);
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + x, player.posY, player.posZ + z, 0, 0.2D, 0, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, player.posX + x, player.posY, player.posZ + z, 2000D, 800D, 200D);

                    EntityExpBottle EntityExpBottle = new EntityExpBottle(worldIn);
                    //EntitySilverfish.canPickUpLoot();
                    EntityExpBottle.setLocationAndAngles(player.posX + x, player.posY, player.posZ + z, (float) (0.0F + x), (float) (0.0F + z));
                    worldIn.spawnEntity(EntityExpBottle);
                }
                player.addExperience(playerHunger * 20);
                return;
            }
        });
    }

    public void start() {
        appleEaten.start();
    }
}
