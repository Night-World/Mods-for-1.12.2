/*
 * Copyright (c) 2022  OreGenParser by FoxesWorld
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

package foxesworld.hardcontent.dynamic.world.OreGen.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.dynamic.world.OreGen.OreGen;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class OreGenParser {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public OreGenParser(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }

    public void readFromJson(String jsonIn) {
        gson = new Gson();
        TypeToken<List<OreGenAttributes>> typeToken = new TypeToken<List<OreGenAttributes>>() {
        };
        List<OreGenAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
        for (OreGenAttributes obj : object) {
            String oreName = obj.getOreName();
            int oreMinHeight = obj.getOreMinHeight();
            int oreMaxHeight = obj.getOreMaxHeight();
            int oreVeinSize = obj.getOreVeinSize();
            int oreSpawnTries = obj.getOreSpawnTries();
            int generationWeight = obj.getGenerationWeight();
            String watchForBlock = obj.getWatchForBlock();
            OreGen genOres = new OreGen(oreName, oreMinHeight, oreMaxHeight, oreVeinSize, oreSpawnTries, watchForBlock);
            GameRegistry.registerWorldGenerator(genOres, generationWeight);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDir() {
        return fileDir;
    }
}
