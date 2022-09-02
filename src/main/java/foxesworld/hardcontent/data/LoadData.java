/*
 * Copyright (c) 2022  LoadData by FoxesWorld
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

package foxesworld.hardcontent.data;

import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.block.parser.BlocksParser;
import foxesworld.hardcontent.dynamic.item.parser.ItemParser;
import foxesworld.hardcontent.dynamic.material.parser.MaterialParser;
import foxesworld.hardcontent.dynamic.sounds.Sounds;
import foxesworld.hardcontent.dynamic.tools.Tools;
import foxesworld.hardcontent.dynamic.world.OreGen.parser.OreGenParser;
import foxesworld.hardcontent.dynamic.world.StructureGen.parser.StructureParser;

import java.io.File;

import static foxesworld.hardcontent.cfg.ConfigCreator.*;
import static foxesworld.hardcontent.cfg.Environment.MODCFGDIR;
import static foxesworld.hardcontent.cfg.Environment.dataExportDir;
import static foxesworld.hardcontent.methods.Utils.debugSend;
import static foxesworld.hardcontent.data.DataParse.readTplFile;

public class LoadData {

    private String paramPath = MODCFGDIR + File.separator + Environment.MODID + dataExportDir;
    private Thread loadData;


    public LoadData() {
        loadData = new Thread(new Runnable() {
            public void run() {
                debugSend("=== STARTING LOADING DATA ===");
                long lStartTime = System.nanoTime();

                Sounds sounds = new Sounds("sounds.json");
                sounds.registerSounds();

                MaterialParser materials = new MaterialParser(paramPath, "material.json");
                materials.readFromJson(readTplFile(materials.getFileName(), materials.getFileDir(), exportMaterials));

                BlocksParser blockParser = new BlocksParser(paramPath, "blocks.json");
                blockParser.readFromJson(readTplFile(blockParser.getFileName(), blockParser.getFileDir(), exportBlocks));

                Tools toolsParser = new Tools(paramPath, "tools.json");
                toolsParser.readFromJson(readTplFile(toolsParser.getFileName(), toolsParser.getFileDir(), exportTools));

                ItemParser ItemParser = new ItemParser(paramPath, "items.json");
                ItemParser.readFromJson(readTplFile(ItemParser.getFileName(), ItemParser.getFileDir(), exportItems));


                OreGenParser oreGen = new OreGenParser(paramPath, "oreGen.json");
                oreGen.readFromJson(readTplFile(oreGen.getFileName(), oreGen.getFileDir(), exportOreGen));

                StructureParser structures = new StructureParser(paramPath, "structures.json");
                structures.readFromJson(readTplFile(structures.getFileName(), structures.getFileDir(), exportStructures));
                long endTime = System.currentTimeMillis();

                RegData data = new RegData();
                data.regItems();
                data.regBlocks();

                debugSend("=== DATA LOADED IN " + (int) ((endTime - lStartTime) / 1000) % 60 + " SECONDS + ===");
            }
        });
    }

    public void loadContent(){
        this.loadData.start();
    }
}
