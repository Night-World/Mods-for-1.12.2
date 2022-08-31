/*
 * Copyright (c) 2022  Slab by FoxesWorld
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import static foxesworld.aidenfox.cfg.CreativeTab.MOD_TAB;

public class Slab extends BlockSlab {
    public Slab(String name, Material material, IBlockState state) {
        super(material);
        //IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, BlockPurpurSlab.Variant.DEFAULT);

        this.setRegistryName(name);
        this.setTranslationKey(name);

        if(!this.isDouble()) {
            //state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        //this.setDefaultState(state);
        //this.useNeighborBrightness = !this.isDouble();
        setCreativeTab(MOD_TAB);
        Environment.BLOCKS.put(name, (Block) this);
    }

    @Override
    public String getTranslationKey(int i) {
        return null;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return null;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack itemStack) {
        return null;
    }
}
