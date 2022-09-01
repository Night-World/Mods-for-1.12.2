/*
 * Copyright (c) 2022  BlockStateStairs by FoxesWorld
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

import java.io.FileWriter;

import static foxesworld.aidenfox.dynamic.JsonGenerated.JsonGenerated.bindDir;

public class BlockStateStairs {

    public static void generateBlockstateStair(String name) {
        String[] facing = new String[]{"east", "south", "west", "north"};
        String[] half = new String[]{"bottom", "top"};
        String[] shape = new String[]{"straight", "outer_right", "outer_left", "inner_right", "inner_left"};


            try {
                JsonWriter writer = new JsonWriter(new FileWriter(bindDir + "blockstates/" + name + "_stair.json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");

                for(int i = 0; i < facing.length; ++i) {
                    for(int k = 0; k < half.length; ++k) {
                        for(int j = 0; j < shape.length; ++j) {
                            writer.name("facing=" + facing[i] + ",half=" + half[k] + ",shape=" + shape[j]);
                            writer.beginObject().setIndent("");
                            if (shape[j].equals("straight")) {
                                writer.name("model").value(Environment.MODID + ":" + name + "_stair");
                                writer.name("x").value((long)(k * 180));
                                writer.name("y").value((long)(i * 90));
                                writer.name("uvlock").value(true);
                            } else {
                                byte factor;
                                if (!shape[j].equals("inner_right") && !shape[j].equals("inner_left")) {
                                    if (shape[j].equals("outer_right") || shape[j].equals("outer_left")) {
                                        writer.name("model").value(Environment.MODID + ":" + name + "_stair_outer");
                                        writer.name("x").value((long)(k * 180));
                                        if (shape[j].equals("outer_right")) {
                                            writer.name("y").value((long)((i + k) % 4 * 90));
                                        }

                                        if (shape[j].equals("outer_left")) {
                                            factor = 0;
                                            if (k == 0) {
                                                factor = 1;
                                            }

                                            if (k == 1) {
                                                factor = 0;
                                            }

                                            writer.name("y").value((long)((i + 3 * factor) % 4 * 90));
                                        }

                                        writer.name("uvlock").value(true);
                                    }
                                } else {
                                    writer.name("model").value(Environment.MODID + ":" + name + "_stair_inner");
                                    writer.name("x").value((long)(k * 180));
                                    if (shape[j].equals("inner_right")) {
                                        writer.name("y").value((long)((i + k) % 4 * 90));
                                    }

                                    if (shape[j].equals("inner_left")) {
                                        factor = 0;
                                        if (k == 0) {
                                            factor = 1;
                                        }

                                        if (k == 1) {
                                            factor = 0;
                                        }

                                        writer.name("y").value((long)((i + 3 * factor) % 4 * 90));
                                    }

                                    writer.name("uvlock").value(true);
                                }
                            }

                            writer.endObject().setIndent("   ");
                        }
                    }
                }

                writer.endObject();
                writer.endObject();
                writer.close();
            } catch (Exception var10) {
                var10.printStackTrace();
            }
        }
}
