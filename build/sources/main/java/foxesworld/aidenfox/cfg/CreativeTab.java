/*
 * Copyright (c) 2022  CreativeTab by FoxesWorld
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

package foxesworld.aidenfox.cfg;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static foxesworld.aidenfox.cfg.Environment.BLOCKS;

public class CreativeTab extends CreativeTabs {

    public static final CreativeTabs MOD_TAB = new CreativeTab();

    public CreativeTab() {

        super(Environment.MODID);

    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BLOCKS.get("hardened_dirt"));
    }
}