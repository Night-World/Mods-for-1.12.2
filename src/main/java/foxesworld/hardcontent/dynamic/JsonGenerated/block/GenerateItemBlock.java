/*
 * Copyright (c) 2022  GenerateItemBlock by FoxesWorld
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

package foxesworld.hardcontent.dynamic.JsonGenerated.block;

import com.google.gson.stream.JsonWriter;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.JsonGenerated.JsonGenerated;

import java.io.FileWriter;

public class GenerateItemBlock extends JsonGenerated {

    protected static void generateItemBlock(String name) {

        try {
            JsonWriter writer = new JsonWriter(new FileWriter(bindDir + "item/" + name + ".json"));
            writer.beginObject().setIndent("   ");
            writer.name("parent").value(Environment.MODID + ":block/" + name);
            writer.name("display");
            writer.beginObject().setIndent("   ");
            writer.name("thirdperson");
            writer.beginObject().setIndent("   ");
            writer.name("rotation");
            writer.beginArray().setIndent("");
            writer.value(10L);
            writer.value(-45L);
            writer.value(170L);
            writer.endArray().setIndent("   ");
            writer.name("translation");
            writer.beginArray().setIndent("");
            writer.value(0L);
            writer.value(1.5D);
            writer.value(-2.75D);
            writer.endArray().setIndent("   ");
            writer.name("scale");
            writer.beginArray().setIndent("");
            writer.value(0.375D);
            writer.value(0.375D);
            writer.value(0.375D);
            writer.endArray().setIndent("   ");
            writer.endObject();
            writer.endObject();
            writer.endObject();
            writer.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

}
