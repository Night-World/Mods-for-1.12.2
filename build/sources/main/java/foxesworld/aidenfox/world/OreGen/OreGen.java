package foxesworld.aidenfox.world.OreGen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static foxesworld.aidenfox.util.Utils.debugSend;

public class OreGen implements IWorldGenerator {

    private String oreName;
    private int oreMinHeight;
    private int oreMaxHeight;
    private int oreVeinSize;
    private int oreSpawnTRies;
    private String watchForBlock;

    public OreGen(String oreName, int oreMinHeight, int oreMaxHeight, int oreVeinSize, int oreSpawnTRies, String watchForBlock) {
        this.oreName = oreName;
        this.oreMinHeight = oreMinHeight;
        this.oreMaxHeight = oreMaxHeight;
        this.oreVeinSize = oreVeinSize;
        this.oreSpawnTRies = oreSpawnTRies;
        this.watchForBlock = watchForBlock;
    }

    private WorldGenMinable worldGenMinable;

    /**
     * function to generate Ores in the specified Dimension
     * @param random                Random
     * @param chunkX                Chunk X Coordinate
     * @param chunkZ                Chunk Z Coordinate
     * @param world                 The World we are generating for
     * @param chunkGenerator        IChunkGenerator
     * @param chunkProvider         IChunkProvider
     * Dimension IDs:               0 = overworld; 1 = The End; -1 = The Nether
     */
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider ) {
        if (world.provider.getDimension() == 0) {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
            debugSend("Generating " + this.oreName + " in overWorld");
        }
        if (world.provider.getDimension() == -1) {
            generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    /**
     * function to create the generation of the ores
     * @param ore           The Ore to be generated
     * @param world         World
     * @param rand          Random
     * @param chunkX        Chunk X Coordinate
     * @param chunkZ        Chunk Z Coordinate
     * @param minY          Min height for Generation
     * @param maxY          Max height for Generation
     * @param size          Size of the Generation
     * @param spawnTries    How often to try a Generation in a Chunk
     * @param block         The Block to generate Ores in
     */
    private void generateOre(IBlockState ore, World world, Random rand, int chunkX, int chunkZ, int minY, int maxY, int size, int spawnTries, BlockMatcher block) {
        int deltaY = maxY - minY + 1;
        if (minY < 0) minY = 1;
        if (maxY > 255) maxY = 255;

        for (int i = 0; i < spawnTries; i++) {
            BlockPos pos = new BlockPos(chunkX + rand.nextInt(16), minY + rand.nextInt(deltaY), chunkZ + rand.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size, block);
            generator.generate(world, rand, pos);
        }
    }

    /**
     * function to declare what ores will be generated
     * @param random                Random
     * @param chunkX                Chunk X
     * @param chunkZ                Chunk Z
     * @param world                 World
     * @param chunkGenerator        IChunkGenerator
     * @param chunkProvider         IChunkProvider
     */
    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
            generateOre(Block.getBlockFromName(this.oreName).getDefaultState(),
                    world, random, chunkX * 16, chunkZ * 16, this.oreMinHeight, this.oreMaxHeight, random.nextInt(this.oreVeinSize) + 1,
                    this.oreSpawnTRies, BlockMatcher.forBlock(Block.getBlockFromName(this.watchForBlock)));
    }

    private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOre(Blocks.REDSTONE_ORE.getDefaultState(),
                world, random, chunkX * 16, chunkZ * 16, 1, 255, random.nextInt(30) + 10, 24,BlockMatcher.forBlock(Blocks.NETHERRACK));
    }
}
