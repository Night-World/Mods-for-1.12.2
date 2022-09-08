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
import foxesworld.hardcontent.methods.FileAsStream;

import java.io.File;

import static foxesworld.hardcontent.cfg.ConfigInit.CONFIGdatagen;
import static foxesworld.hardcontent.cfg.Environment.*;
import static foxesworld.hardcontent.methods.FileOptions.BufferedFileReader;
import static foxesworld.hardcontent.methods.FileOptions.createIfnotExists;
import static foxesworld.hardcontent.methods.Utils.debugSend;

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
                materials.readFromJson(readTplFile(materials.getFileName(), materials.getFileDir(), CONFIGdatagen.exportMaterials));

                BlocksParser blockParser = new BlocksParser(paramPath, "blocks.json");
                blockParser.readFromJson(readTplFile(blockParser.getFileName(), blockParser.getFileDir(), CONFIGdatagen.exportBlocks));

                Tools toolsParser = new Tools(paramPath, "tools.json");
                toolsParser.readFromJson(readTplFile(toolsParser.getFileName(), toolsParser.getFileDir(), CONFIGdatagen.exportTools));

                ItemParser ItemParser = new ItemParser(paramPath, "items.json");
                ItemParser.readFromJson(readTplFile(ItemParser.getFileName(), ItemParser.getFileDir(), CONFIGdatagen.exportItems));

                OreGenParser oreGen = new OreGenParser(paramPath, "oreGen.json");
                oreGen.readFromJson(readTplFile(oreGen.getFileName(), oreGen.getFileDir(), CONFIGdatagen.exportOreGen));

                StructureParser structures = new StructureParser(paramPath, "structures.json");
                structures.readFromJson(readTplFile(structures.getFileName(), structures.getFileDir(), CONFIGdatagen.exportStructures));

                RegistryData data = new RegistryData();
                data.regItems();
                data.regBlocks();
                long endTime = System.nanoTime();

                debugSend("=== DATA LOADED IN " + (int) ((endTime - lStartTime) / 1000) % 60 +  " SECONDS  ===");
            }
        });
    }

    private static String readTplFile(String fileName, String fileDir, boolean exportData) {
        String jsonString = "";
        if (!exportData) {
            debugSend("Reading " + fileName + " from internal resource");
            FileAsStream templateFile = new FileAsStream(dataTemplateDir + fileName, Environment.MODID);
            jsonString = (String) templateFile.getFileContents();
        } else {
            debugSend("Reading " + fileName + " from external folder");
            createIfnotExists(fileDir, fileName);
            jsonString = BufferedFileReader(fileDir + fileName);
        }
        return jsonString;
    }

    public void loadContent() {
        this.loadData.start();
    }
}
