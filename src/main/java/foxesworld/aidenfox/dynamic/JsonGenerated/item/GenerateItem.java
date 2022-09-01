/*
 * Copyright (c) 2022  GenerateItem by FoxesWorld
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

package foxesworld.aidenfox.dynamic.JsonGenerated.item;

import com.google.gson.stream.JsonWriter;
import foxesworld.aidenfox.cfg.Environment;

import java.io.FileWriter;

import static foxesworld.aidenfox.dynamic.JsonGenerated.JsonGenerated.bindDir;

public class GenerateItem {

        public static void generateItem(String parent, String name) {

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(bindDir + "item/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value(parent);
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("layer0").value(Environment.MODID + ":items/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

}
