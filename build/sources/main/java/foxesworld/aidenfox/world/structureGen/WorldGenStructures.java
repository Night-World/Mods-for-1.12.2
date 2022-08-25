package foxesworld.aidenfox.world.structureGen;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static foxesworld.aidenfox.util.Utils.debugSend;


public class WorldGenStructures implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 1: //End
                break;
            case 0: //Default world
                for (StructureGenerator structure : Environment.STRUCTURES) {
                    debugSend("Generating - " + structure.structureName + " | with rarity " + structure.rarity + " | on block " + structure.topBlock + " | In biome " + structure.biomeToGen);
                    generateStructure(new StructureInstance(structure.structureName), world, random, chunkX, chunkZ, structure.rarity, structure.topBlock, BiomeMesa.getBiomeForId(structure.biomeToGen));
                }

                break;
            case -1: //nether
                break;
        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Biome biomeGen) {

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock) + 1;
        BlockPos pos = new BlockPos(x, y, z);
        Biome biome = world.provider.getBiomeForCoords(pos);
        //if (world.getWorldType() != WorldType.FLAT) {
        if (biomeGen.equals(biome)) {
            int genRarity = random.nextInt(chance);
            if (genRarity == 0) {
                generator.generate(world, random, pos);
            }
        }
        //}
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
        int y = world.getHeight(); //получаем высоту мира (256)
        boolean foundGround = false;

        while (!foundGround && y-- >= 0) //если земля не найдена и значение уменьшения высоты больше или равно 0
        {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == topBlock;
        }
        return y;
    }
}