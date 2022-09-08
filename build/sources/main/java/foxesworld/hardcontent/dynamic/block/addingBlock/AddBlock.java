/*
 * Copyright (c) 2022  AddBlock by FoxesWorld
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

package foxesworld.hardcontent.dynamic.block.addingBlock;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.block.blockType.Block;
import foxesworld.hardcontent.dynamic.block.blockType.Slab;
import foxesworld.hardcontent.dynamic.block.blockType.Stairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static foxesworld.hardcontent.data.JsonGenerated.JsonGenerated.genBlock;
import static foxesworld.hardcontent.data.JsonGenerated.stairs.BlockStairGenerator.generateBlockStair;

public class AddBlock {
    private BlockAttributes blockAttributes;

    private String blockName;
    private String blockType;
    private String fromBlock;
    private Material blockMaterial = Material.CIRCUITS;
    private SoundType blockSound = SoundType.METAL;
    private String blockHarvestTool = "pickaxe";
    private Integer blockHarvestLevel = 2;
    private float blockHardness = 0;
    private float blockResistance = 0;
    private boolean creatureSpawn = false;
    private String itemDrop = "";
    private int dropAmmount;
    private String[] display;

    public AddBlock(BlockAttributes blockAttributes) {
        this.blockAttributes = blockAttributes;
        blockName = blockAttributes.getBlockName();
        blockType = blockAttributes.getBlockType();
        dropAmmount = 1;
        switch (blockType) {

            case "Block":
                blockMaterial = blockAttributes.getBlockMaterial().getType();
                blockSound = blockAttributes.getBlockSound().getSnd();
                blockHarvestTool = blockAttributes.getBlockTool();
                blockHarvestLevel = blockAttributes.getBlockHarvestLevel();
                blockHardness = blockAttributes.getBlockHardness();
                blockResistance = blockAttributes.getBlockResistance();
                creatureSpawn = blockAttributes.getCreatureSpawn();
                itemDrop = blockAttributes.getItemDrop();
                display = blockAttributes.getDisplay();

                if (!itemDrop.equals("")) {
                    dropAmmount = blockAttributes.getDropAmmount();
                }
                genBlock(blockName);
                new Block(blockName, blockMaterial, blockSound, blockHarvestTool, blockHarvestLevel, blockHardness, blockResistance, creatureSpawn, itemDrop, dropAmmount);
                break;

            case "Stairs":
                fromBlock = blockAttributes.getFromBlock();
                generateBlockStair(blockName);
                new Stairs(blockName, Environment.BLOCKS.get(fromBlock).getDefaultState());
                break;

            case "Slab":
                fromBlock = blockAttributes.getFromBlock();
                new Slab(blockName, blockMaterial, Environment.BLOCKS.get(fromBlock).getDefaultState());
                break;
        }
    }



}
