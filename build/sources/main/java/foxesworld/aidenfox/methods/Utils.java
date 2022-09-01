package foxesworld.aidenfox.methods;

import foxesworld.aidenfox.cfg.ConfigCreator;
import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static foxesworld.aidenfox.cfg.Environment.SOUNDS;
import static foxesworld.aidenfox.main.logger;

public class Utils {

    public static void debugSend(String msg) {
        if (ConfigCreator.debug) {
            logger.info(msg);
        }
    }

    public static void addLore(String objectName, String itemtype, List<String> tooltip) {
        TextComponentTranslation lore = new TextComponentTranslation(itemtype + "." + objectName + ".lore");
        if (!lore.getUnformattedText().contains(itemtype)) {
            tooltip.add(lore.getUnformattedText());
        } else {
            tooltip.add("");
        }
    }

    public static void spawnParticles(IBlockState stateIn, World worldIn, BlockPos pos, Random rand, Integer particleId) {
        for (int i = 0; i < 1; i++) {
            double motionY = Math.abs(rand.nextGaussian() * 0.02D);
            float randX = rand.nextFloat();
            float randY = rand.nextFloat();
            float randZ = rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.getParticleFromId(particleId),
                    pos.getX() + randX,
                    pos.getY() + randY,
                    pos.getZ() + randZ,
                    0,
                    motionY,
                    0,
                    new int[0]);
        }
    }

    public static void playFoxesSound(World worldIn, EntityPlayer playerIn, String snd){
        Thread sndPlay = new Thread(new Runnable() {
            public void run() {
                worldIn.playSound((EntityPlayer) playerIn,
                        playerIn.posX,
                        playerIn.posY,
                        playerIn.posZ,
                        SOUNDS.get(snd),
                        SoundCategory.NEUTRAL,
                        1.5F, 1F);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sndPlay.start();
    }

    public static void createIfnotExists(String fileDir, String filename){
        String dataTemplateDir = "dataTemplates/";
        File cfgFile = new File(fileDir + filename);
        FileAsStream templateFile = new FileAsStream(dataTemplateDir + filename, Environment.MODID);
        Object templateFileContents = templateFile.getFileContents();
        if(!cfgFile.exists()){
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WriteFile.appendToFile(String.valueOf(templateFileContents), String.valueOf(cfgFile));
        }
    }

}
