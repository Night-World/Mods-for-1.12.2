/*
 * Copyright (c) 2022  BlockAttributes by FoxesWorld
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


public class BlockAttributes {

    private String blockName;
    private String blockType;
    private String fromBlock;
    private BlockMaterial blockMaterial;
    private BlockSound blockSound;
    private String blockTool;
    private int blockHarvestLevel;
    private float blockHardness;
    private float blockResistance;
    private boolean creatureSpawn;
    private String itemDrop;
    private int dropAmmount;
    private String[] display;

    public String getBlockName() {
        return blockName;
    }

    public String getBlockType() { return  blockType; }

    public String getFromBlock() {
        return fromBlock;
    }
    public BlockMaterial getBlockMaterial() {
        return blockMaterial;
    }

    public BlockSound getBlockSound() {
        return blockSound;
    }

    public String getBlockTool() {
        return blockTool;
    }

    public int getBlockHarvestLevel() {
        return blockHarvestLevel;
    }

    public float getBlockHardness() {
        return blockHardness;
    }

    public float getBlockResistance() {
        return blockResistance;
    }

    public boolean getCreatureSpawn() { return creatureSpawn;
    }

    public String getItemDrop() {
        return itemDrop;
    }

    public int getDropAmmount() {
        return dropAmmount;
    }

    public String[] getDisplay() { return  display;}
}
