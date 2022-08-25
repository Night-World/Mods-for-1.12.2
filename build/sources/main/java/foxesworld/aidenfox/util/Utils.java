package foxesworld.aidenfox.util;

import foxesworld.aidenfox.cfg.ConfigCreator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

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
}
