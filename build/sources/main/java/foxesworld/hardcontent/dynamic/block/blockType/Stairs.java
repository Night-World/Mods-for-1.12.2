/*
 * Copyright (c) 2022  Stairs by FoxesWorld
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

package foxesworld.hardcontent.dynamic.block.blockType;

import foxesworld.hardcontent.cfg.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

import static foxesworld.hardcontent.cfg.CreativeTab.MOD_TAB;
import static foxesworld.hardcontent.methods.Utils.debugSend;

public class Stairs extends BlockStairs {

    public Stairs(String name, IBlockState state) {
        super(state);
        debugSend("[" + name + "|Stairs] registered");
        this.setRegistryName(name);
        this.setTranslationKey(name);
        setCreativeTab(MOD_TAB);
        Environment.BLOCKS.put(name, (Block) this);

    }
}
