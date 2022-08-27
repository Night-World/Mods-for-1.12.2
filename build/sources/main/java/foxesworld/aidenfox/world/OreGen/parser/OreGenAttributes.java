/*
 * Copyright (c) 2022  OreGenAttributes by FoxesWorld
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

package foxesworld.aidenfox.world.OreGen.parser;

public class OreGenAttributes {

    private String oreName;
    private int oreMinHeight;
    private int oreMaxHeight;
    private int oreVeinSize;
    private int oreSpawnTries;
    private int generationWeight;
    private String watchForBlock;

    public String getOreName() {
        return oreName;
    }

    public int getOreMinHeight() {
        return oreMinHeight;
    }

    public int getOreMaxHeight() {
        return oreMaxHeight;
    }

    public int getOreVeinSize() {
        return oreVeinSize;
    }

    public int getOreSpawnTries() {
        return oreSpawnTries;
    }

    public int getGenerationWeight(){ return  generationWeight;}

    public String getWatchForBlock() {
        return watchForBlock;
    }
}
