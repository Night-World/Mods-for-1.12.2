/*
 * Copyright (c) 2022  BlockMaterial by FoxesWorld
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

import net.minecraft.block.material.Material;

enum BlockMaterial {
    ROCK(Material.ROCK),
    CLAY(Material.CLAY),
    CIRCUITS(Material.CIRCUITS),
    WOOD(Material.WOOD),
    GROUND(Material.GROUND);

    private final Material type;
    BlockMaterial(Material type) {
       this.type = type;
    }

    public Material getType() {
        return type;
    }
}
