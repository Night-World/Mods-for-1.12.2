package foxesworld.aidenfox.stuff.blocks;

import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.stuff.world.WorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static foxesworld.aidenfox.cfg.CreativeTab.MOD_TAB;

public abstract class Blocks extends Block {

    protected String name;
    public Integer blockParticle = 1;

    public Blocks(String name,
                  Material material,
                  Integer particle,
                  SoundType snd,
                  String harvestTool,
                  Integer harvestLevel,
                  float hardness,
                  float resistance) {
        super(material);

                this.name = name;
                this.blockParticle = particle;
                this.setTranslationKey(name);
                this.setSoundType(snd);
                this.setHarvestLevel(harvestTool, harvestLevel);
                this.setHardness(hardness);
                this.setResistance(resistance);
                this.setRegistryName(Environment.MODID, name);

                setCreativeTab(MOD_TAB);

        Environment.BLOCKS.add(this);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public String getBlockName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 1; i++) {
            double motionY = Math.abs(rand.nextGaussian() * 0.02D);
            float randX = rand.nextFloat();
            float randY = rand.nextFloat();
            float randZ = rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.getParticleFromId(blockParticle),
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