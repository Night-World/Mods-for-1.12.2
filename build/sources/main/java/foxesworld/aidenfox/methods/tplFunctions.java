/*
 * Copyright (c) 2022  tplFunctions by FoxesWorld
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

package foxesworld.aidenfox.methods;

import foxesworld.aidenfox.cfg.Environment;

import static foxesworld.aidenfox.methods.FileOptions.BufferedFileReader;
import static foxesworld.aidenfox.methods.FileOptions.createIfnotExists;
import static foxesworld.aidenfox.methods.Utils.debugSend;

public class tplFunctions {

    public static String readTplFile(String fileName, String fileDir, boolean inputStream) {
       debugSend("tplDir = "+ fileDir + " | tplFile = " + fileName);
        String jsonString = "";
        if (inputStream) {
            FileAsStream structuresJsonStream = new FileAsStream(fileName, Environment.MODID);
            jsonString = (String) structuresJsonStream.getFileContents();
        } else {
            createIfnotExists(fileDir, fileName);
            jsonString = BufferedFileReader(fileDir + fileName);
        }
        //readFromJson(jsonString);
        return jsonString;
    }
}
