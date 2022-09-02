/*
 * Copyright (c) 2022  StructureInstance by FoxesWorld
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

package foxesworld.hardcontent.dynamic.world.StructureGen.parser;

import foxesworld.hardcontent.cfg.Environment;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureInstance extends WorldGenerator implements IStructure {
    public static String structureName;

    public StructureInstance(String name) {
        this.structureName = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position, rand);
        return true;
    }

    public static void generateStructure(World world, BlockPos pos, Random rand) {
        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Environment.MODID, structureName);
        Template template = manager.get(mcServer, location);

        if (template != null) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorldChunk(world, pos, settings);
            fillChests(template, world, pos, rand);
        }
        return;
    }

    private static void fillChests(Template template, World world, BlockPos pos, Random rand){
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorldChunk(world, pos, settings);

            for(int x = 0; x <= template.getSize().getX(); x++) {

                for(int y = 0; y <= template.getSize().getY(); y++) {

                    for(int z = 0; z <= template.getSize().getZ(); z++){

                        BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);

                        if(world.getTileEntity(tmp) != null){

                            if(world.getTileEntity(tmp) instanceof TileEntityChest){

                                TileEntityChest chest = (TileEntityChest) world.getTileEntity(tmp);
                                ((TileEntityChest)chest).setLootTable(new ResourceLocation(Environment.MODID + ":chests/" + structureName), rand.nextLong());
                            }
                        }
                    }
                }
            }
    }
}