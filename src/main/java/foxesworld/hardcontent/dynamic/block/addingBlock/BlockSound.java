/*
 * Copyright (c) 2022  BlockSound by FoxesWorld
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

import net.minecraft.block.SoundType;

enum BlockSound {
    GROUND(SoundType.GROUND),
    WOOD(SoundType.WOOD),
    CLOTH(SoundType.CLOTH),
    ANVIL(SoundType.ANVIL),
    GLASS(SoundType.GLASS),
    STONE(SoundType.STONE);

    private final SoundType snd;

    BlockSound(SoundType snd) {
        this.snd = snd;
    }

    public SoundType getSnd() {
        return snd;
    }
}
