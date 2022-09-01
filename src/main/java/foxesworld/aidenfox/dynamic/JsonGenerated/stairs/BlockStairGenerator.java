/*
 * Copyright (c) 2022  BlockStairGenerator by FoxesWorld
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

package foxesworld.aidenfox.dynamic.JsonGenerated.stairs;

import com.google.gson.stream.JsonWriter;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.dynamic.JsonGenerated.JsonGenerated;

import java.io.FileWriter;

import static foxesworld.aidenfox.dynamic.JsonGenerated.stairs.BlockStateStairs.generateBlockstateStair;

public class BlockStairGenerator extends JsonGenerated {

    public static void generateBlockStair(String name) {

            JsonWriter writer;
            try {
                writer = new JsonWriter(new FileWriter(bindDir + "block/" + name + "_stair.json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/stairs");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("bottom").value(Environment.MODID + ":blocks/" + name);
                writer.name("top").value(Environment.MODID + ":blocks/" + name);
                writer.name("side").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            try {
                writer = new JsonWriter(new FileWriter(bindDir + "block/" + name + "_stair_inner.json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/inner_stairs");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("bottom").value(Environment.MODID + ":blocks/" + name);
                writer.name("top").value(Environment.MODID + ":blocks/" + name);
                writer.name("side").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            try {
                writer = new JsonWriter(new FileWriter(bindDir + "block/" + name + "_stair_outer.json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/outer_stairs");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("bottom").value(Environment.MODID + ":blocks/" + name);
                writer.name("top").value(Environment.MODID + ":blocks/" + name);
                writer.name("side").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
            } catch (Exception var3) {
                var3.printStackTrace();
            }
            generateBlockstateStair(name);
        }
}
