/*
 * Copyright (c) 2022  StructureGen by FoxesWorld
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foxesworld.hardcontent.dynamic.world.StructureGen;

import foxesworld.hardcontent.cfg.ConfigCreator;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.world.StructureGen.parser.StructureInstance;
import foxesworld.hardcontent.dynamic.world.StructureGen.parser.StructureParser;
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

import static foxesworld.hardcontent.methods.Utils.debugSend;


public class StructureGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(ConfigCreator.CONFIGgenerate.structureGen) {
            switch (world.provider.getDimension()) {
                case 1: //End
                    break;
                case 0: //Overworld
                    for (StructureParser structure : Environment.STRUCTURES) {
                        for (int i = 0; i < structure.biomeToGen.length; i++) {
                            Biome biome = BiomeMesa.getBiomeForId(structure.biomeToGen[i]);
                            debugSend("Generating - " + structure.structureName + " | with rarity " + structure.rarity + " | on block " + structure.topBlock + " | In biome " + biome);
                            generateStructure(new StructureInstance(structure.structureName), world, random, chunkX, chunkZ, structure.rarity, structure.topBlock, biome);
                        }
                    }

                    break;
                case -1: //nether
                    break;
            }
        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Biome biomeGen) {

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock) + 1;
        BlockPos pos = new BlockPos(x, y, z);
        Biome biome = world.provider.getBiomeForCoords(pos);
        //if (world.getWorldType() != WorldType.FLAT) {
        if (biomeGen.equals(biome) && !biomeGen.equals(0)) {
            int genRarity = random.nextInt(chance);
            if (genRarity == 0) {
                generator.generate(world, random, pos);
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
        int y = world.getHeight();
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == topBlock;
        }
        return y;
    }
}