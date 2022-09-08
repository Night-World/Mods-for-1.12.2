/*
 * Copyright (c) 2022  GenerateBlock by FoxesWorld
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

package foxesworld.hardcontent.data.JsonGenerated.block;

import com.google.gson.stream.JsonWriter;
import foxesworld.hardcontent.cfg.Environment;

import java.io.FileWriter;

import static foxesworld.hardcontent.data.JsonGenerated.JsonGenerated.bindDir;

public class GenerateBlock {

    public static void generateBlock(String name) {

        try {
            JsonWriter writer = new JsonWriter(new FileWriter(bindDir + "block/" + name + ".json"));
            writer.beginObject().setIndent("   ");
            writer.name("parent").value("block/cube_all");
            writer.name("textures");
            writer.beginObject().setIndent("   ");
            writer.name("all").value(Environment.MODID + ":blocks/" + name);
            writer.endObject();
            writer.endObject();
            writer.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        GenerateItemBlock.generateItemBlock(name);
        GenerateBlockstate.generateBlockstate(name);
    }

}
