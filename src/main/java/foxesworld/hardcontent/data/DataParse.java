/*
 * Copyright (c) 2022  DataParse by FoxesWorld
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
import foxesworld.hardcontent.methods.FileAsStream;

import static foxesworld.hardcontent.cfg.Environment.dataTemplateDir;
import static foxesworld.hardcontent.methods.FileOptions.BufferedFileReader;
import static foxesworld.hardcontent.methods.FileOptions.createIfnotExists;
import static foxesworld.hardcontent.methods.Utils.debugSend;

public class DataParse {

    public static String readTplFile(String fileName, String fileDir, boolean exportData) {
        String jsonString = "";
        if (!exportData) {
            FileAsStream structuresJsonStream = new FileAsStream(dataTemplateDir + fileName, Environment.MODID);
            jsonString = (String) structuresJsonStream.getFileContents();
        } else {
            debugSend("tplDir = "+ fileDir + " | tplFile = " + fileName);
            createIfnotExists(fileDir, fileName);
            jsonString = BufferedFileReader(fileDir + fileName);
        }
        return jsonString;
    }
}
