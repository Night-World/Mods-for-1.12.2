/*
 * Copyright (c) 2022  Block by FoxesWorld
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

package foxesworld.aidenfox.dynamic.blocks.blockType;

import foxesworld.aidenfox.cfg.Environment;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static foxesworld.aidenfox.cfg.CreativeTab.MOD_TAB;
import static foxesworld.aidenfox.methods.Utils.addLore;
import static foxesworld.aidenfox.methods.Utils.debugSend;
import static net.minecraft.item.Item.getByNameOrId;

public class Block extends net.minecraft.block.Block {
    protected String name;
    protected boolean creatureSpawn;
    protected String itemDrop;
    protected int dropAmmount;

    public Block(String name, Material material, SoundType snd, String harvestTool, Integer harvestLevel, float hardness, float resistance, boolean creatureSpawn, String itemDrop, int dropAmmount) {
        super(material);
        debugSend("[" + name + "|Block] " + material + " | " + snd + " | " + harvestTool + " | " + harvestLevel + " | " + hardness + " | " + resistance);
        this.name = name;
        this.creatureSpawn = creatureSpawn;
        this.itemDrop = itemDrop;
        this.dropAmmount = dropAmmount;
        this.setTranslationKey(name);
        this.setSoundType(snd);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(Environment.MODID, name);
        setCreativeTab(MOD_TAB);
        //ResourceLocation loc = new ResourceLocation(JsonGenerated.bindDir);

        Environment.BLOCKS.put(name, this);
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.isNormalCube(state, world, pos);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        if (this.creatureSpawn) {
            return super.canCreatureSpawn(state, world, pos, type);
        } else {
            return false;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (!this.itemDrop.equals("")) {
            return getByNameOrId(this.itemDrop);
        } else {
            return super.getItemDropped(state, rand, fortune);
        }
    }

    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return this.dropAmmount;
        //quantityDroppedWithBonus(fortune, random);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.name, "tile", tooltip);
    }
}
