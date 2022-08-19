package foxesworld.aidenfox.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Utils {

    public static void debugSend(String msg){
      //  if(ConfigCreator.debug){
            System.out.println(msg);
      //  }
    }

    public static void spawnParticles(IBlockState stateIn, World worldIn, BlockPos pos, Random rand, Integer particleId){
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
