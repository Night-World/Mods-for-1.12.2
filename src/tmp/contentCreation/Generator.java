package foxesworld.aidenfox.cfg.contentCreation;

import com.google.gson.stream.JsonWriter;
import foxesworld.aidenfox.cfg.Environment;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Generator {
    public Generator() {
    }

    public static void generateItem(String parent) {
        Iterator var2 = JsonGenerator.toGenerate.iterator();

        while(var2.hasNext()) {
            String name = (String)var2.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/item/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value(parent);
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("layer0").value(Environment.MODID + ":items/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.items;
                JsonGenerator.logger.logItem(name);
            } catch (Exception var4) {
                var4.printStackTrace();
                JsonGenerator.logger.log(var4.toString());
                JsonGenerator.logger.end();
            }
        }

        JsonGenerator.toGenerate.clear();
    }

    public static void generateItemBlock() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/item/" + name + ".json"));
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
                ++JsonGenerator.items;
                JsonGenerator.logger.logItem(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateItemBlock(String name) {
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/item/" + name + ".json"));
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
            ++JsonGenerator.items;
            JsonGenerator.logger.logItem(name);
        } catch (Exception var2) {
            var2.printStackTrace();
            JsonGenerator.logger.log(var2.toString());
            JsonGenerator.logger.end();
        }

    }

    public static void generateBlock(String name) {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            //String name = (String)var1.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/cube_all");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("all").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

        generateItemBlock();
        generateBlockstate();
        JsonGenerator.toGenerate.clear();
    }

    public static void generateBlockOrientable() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/orientable");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("top").value(Environment.MODID + ":blocks/" + name + "_top");
                writer.name("front").value(Environment.MODID + ":blocks/" + name + "_front");
                writer.name("side").value(Environment.MODID + ":blocks/" + name + "_side");
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

        generateItemBlock();
        generateBlockstateOrientable();
        JsonGenerator.toGenerate.clear();
    }

    public static void generateBlockVariants() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();
            int colon = name.indexOf(":");
            String property = name.substring(colon);
            name = name.replace(property, "");
            property = property.replace(":", "");
            int semicolon = name.indexOf(";");
            String variants = name.substring(semicolon);
            name = name.replace(variants, "");
            variants = variants.replace(";", "");
            String[] variantList = variants.split(",");

            for(int i = 0; i < variantList.length; ++i) {
                try {
                    JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + variantList[i] + ".json"));
                    writer.beginObject().setIndent("   ");
                    writer.name("parent").value("block/all");
                    writer.name("textures");
                    writer.beginObject().setIndent("   ");
                    writer.name("all").value(Environment.MODID + ":blocks/" + variantList[i]);
                    writer.endObject();
                    writer.endObject();
                    writer.close();
                    ++JsonGenerator.blocks;
                    JsonGenerator.logger.logBlock(name);
                } catch (Exception var9) {
                    var9.printStackTrace();
                    JsonGenerator.logger.log(var9.toString());
                    JsonGenerator.logger.end();
                }

                generateItemBlock(variantList[i]);
            }
        }

        generateBlockstateVariants();
        JsonGenerator.toGenerate.clear();
    }

    public static void generateBlockSlab() {
        String name;
        for(Iterator var1 = JsonGenerator.toGenerate.iterator(); var1.hasNext(); generateItemBlock(name + "_slab")) {
            name = (String)var1.next();

            JsonWriter writer;
            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + "_slab.json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/half_slab");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("bottom").value(Environment.MODID + ":blocks/" + name);
                writer.name("top").value(Environment.MODID + ":blocks/" + name);
                writer.name("side").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name + "_slab");
            } catch (Exception var4) {
                var4.printStackTrace();
                JsonGenerator.logger.log(var4.toString());
                JsonGenerator.logger.end();
            }

            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + "_slab_upper.json"));
                writer.beginObject().setIndent("   ");
                writer.name("parent").value("block/upper_slab");
                writer.name("textures");
                writer.beginObject().setIndent("   ");
                writer.name("bottom").value(Environment.MODID + ":blocks/" + name);
                writer.name("top").value(Environment.MODID + ":blocks/" + name);
                writer.name("side").value(Environment.MODID + ":blocks/" + name);
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name + "_slab_upper");
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

        generateBlockstateSlab();
        JsonGenerator.toGenerate.clear();
    }

    public static void generateBlockStair() {
        String name;
        for(Iterator var1 = JsonGenerator.toGenerate.iterator(); var1.hasNext(); generateItemBlock(name + "_stair")) {
            name = (String)var1.next();

            JsonWriter writer;
            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + "_stair.json"));
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
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name + "_stair");
            } catch (Exception var5) {
                var5.printStackTrace();
                JsonGenerator.logger.log(var5.toString());
                JsonGenerator.logger.end();
            }

            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + "_stair_inner.json"));
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
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name + "_stair_inner");
            } catch (Exception var4) {
                var4.printStackTrace();
                JsonGenerator.logger.log(var4.toString());
                JsonGenerator.logger.end();
            }

            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/block/" + name + "_stair_outer.json"));
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
                ++JsonGenerator.blocks;
                JsonGenerator.logger.logBlock(name + "_outer");
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

        generateBlockstateStair();
        JsonGenerator.toGenerate.clear();
    }

    public static void generateBlockstateVariants() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();
            int colon = name.indexOf(":");
            String property = name.substring(colon);
            name = name.replaceFirst(property, "");
            property = property.replace(":", "");
            int semicolon = name.indexOf(";");
            String variants = name.substring(semicolon);
            name = name.replaceFirst(variants, "");
            variants = variants.replace(";", "");
            String[] variantList = variants.split(",");

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");

                for(int i = 0; i < variantList.length; ++i) {
                    writer.name(property + "=" + variantList[i]);
                    writer.beginObject().setIndent("");
                    writer.name("model").value(Environment.MODID + ":" + variantList[i]);
                    writer.endObject().setIndent("   ");
                }

                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var9) {
                var9.printStackTrace();
                JsonGenerator.logger.log(var9.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateBlockstate() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");
                writer.name("normal");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.endObject().setIndent("   ");
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateBlockstateSlab() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();

            JsonWriter writer;
            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + "_slab.json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");
                writer.name("half=bottom");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name + "_slab");
                writer.endObject().setIndent("   ");
                writer.name("half=top");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name + "_slab_upper");
                writer.endObject().setIndent("   ");
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var4) {
                var4.printStackTrace();
                JsonGenerator.logger.log(var4.toString());
                JsonGenerator.logger.end();
            }

            try {
                writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + "_slab_double.json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");
                writer.name("normal");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.endObject().setIndent("   ");
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateBlockstateOrientable() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("variants");
                writer.beginObject().setIndent("   ");
                writer.name("facing=north");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.endObject().setIndent("   ");
                writer.name("facing=south");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.name("y").value(180L);
                writer.endObject().setIndent("   ");
                writer.name("facing=west");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.name("y").value(270L);
                writer.endObject().setIndent("   ");
                writer.name("facing=east");
                writer.beginObject().setIndent("");
                writer.name("model").value(Environment.MODID + ":" + name);
                writer.name("y").value(90L);
                writer.endObject().setIndent("   ");
                writer.endObject();
                writer.endObject();
                writer.close();
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var3) {
                var3.printStackTrace();
                JsonGenerator.logger.log(var3.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateBlockstateStair() {
        String[] facing = new String[]{"east", "south", "west", "north"};
        String[] half = new String[]{"bottom", "top"};
        String[] shape = new String[]{"straight", "outer_right", "outer_left", "inner_right", "inner_left"};
        Iterator var4 = JsonGenerator.toGenerate.iterator();

        while(var4.hasNext()) {
            String name = (String)var4.next();

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/blockstates/" + name + "_stair.json"));
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
                ++JsonGenerator.blockstates;
                JsonGenerator.logger.logBlockstate(name);
            } catch (Exception var10) {
                var10.printStackTrace();
                JsonGenerator.logger.log(var10.toString());
                JsonGenerator.logger.end();
            }
        }

    }

    public static void generateRecipeShaped() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String line = (String)var1.next();
            int comma = line.indexOf(",");
            String output = line.substring(0, comma);
            line = line.replace(output + ",", "");
            int outputCount = 1;
            int outputMeta = 0;
            if (output.contains("*")) {
                outputCount = Integer.parseInt(output.substring(0, output.indexOf("*")));
                output = output.replace(outputCount + "*", "");
            }

            if (output.contains(";")) {
                outputMeta = Integer.parseInt(output.substring(output.indexOf(";") + 1));
                output = output.replace(";" + outputMeta, "");
            }

            String outputName;
            if (output.contains(":")) {
                outputName = output.substring(output.indexOf(":") + 1);
            } else {
                outputName = output;
            }

            String allPatterns = line.substring(0, line.indexOf("),"));
            ArrayList<String> patterns = new ArrayList();
            line = line.replace(allPatterns + "),", "");

            String group;
            for(allPatterns = allPatterns.replace("(", ""); allPatterns.contains(","); allPatterns = allPatterns.replace(group + ",", "")) {
                comma = allPatterns.indexOf(",");
                group = allPatterns.substring(0, comma);
                patterns.add(group);
            }

            patterns.add(allPatterns);
            group = "";
            if (line.contains("?")) {
                group = line.substring(line.indexOf("?") + 1);
                line = line.replace(",?" + group, "");
            }

            ArrayList keys = new ArrayList();

            while(line.contains(",")) {
                comma = line.indexOf(",");
                String identifier = line.substring(0, comma);
                line = line.replace(identifier + ",", "");
                if (line.contains(",")) {
                    comma = line.indexOf(",");
                } else {
                    comma = line.length();
                }

                String item = line.substring(0, comma);
                line = line.replaceFirst(item + ",", "");
                int meta = 0;
                if (item.contains(";")) {
                    meta = Integer.parseInt(item.substring(item.indexOf(";") + 1));
                    item = item.replace(";" + meta, "");
                }

                Identifier id = new Identifier(identifier, item, meta);
                keys.add(id);
            }

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/recipes/" + outputName + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("type").value("minecraft:crafting_shaped");
                writer.name("pattern");
                writer.beginArray().setIndent("   ");

                int i;
                for(i = 0; i < patterns.size(); ++i) {
                    writer.value((String)patterns.get(i));
                }

                writer.endArray();
                writer.name("key");
                writer.beginObject().setIndent("   ");

                for(i = 0; i < keys.size(); ++i) {
                    Identifier id = (Identifier)keys.get(i);
                    writer.name(id.getKey());
                    writer.beginObject().setIndent("   ");
                    writer.name("item").value(id.getItem());
                    writer.name("data").value((long)id.getMeta());
                    writer.endObject();
                }

                writer.endObject();
                writer.name("result");
                writer.beginObject().setIndent("   ");
                writer.name("item").value(output);
                writer.name("data").value((long)outputMeta);
                writer.name("count").value((long)outputCount);
                writer.endObject();
                if (!group.isEmpty()) {
                    writer.name("group").value(group);
                }

                writer.endObject();
                writer.close();
                ++JsonGenerator.recipes;
                JsonGenerator.logger.logRecipe(outputName);
            } catch (Exception var15) {
                var15.printStackTrace();
                JsonGenerator.logger.log(var15.toString());
                JsonGenerator.logger.end();
            }
        }

        JsonGenerator.toGenerate.clear();
    }

    public static void generateRecipeShapeless() {
        Iterator var1 = JsonGenerator.toGenerate.iterator();

        while(var1.hasNext()) {
            String line = (String)var1.next();
            int comma = line.indexOf(",");
            String output = line.substring(0, comma);
            line = line.replace(output + ",", "");
            int outputCount = 1;
            int outputMeta = 0;
            if (output.contains("*")) {
                outputCount = Integer.parseInt(output.substring(0, output.indexOf("*")));
                output = output.replace(outputCount + "*", "");
            }

            if (output.contains(";")) {
                outputMeta = Integer.parseInt(output.substring(output.indexOf(";") + 1));
                output = output.replace(";" + outputMeta, "");
            }

            String outputName;
            if (output.contains(":")) {
                outputName = output.substring(output.indexOf(":") + 1);
            } else {
                outputName = output;
            }

            String group = "";
            if (line.contains("?")) {
                group = line.substring(line.indexOf("?") + 1);
                line = line.replace(",?" + group, "");
            }

            ArrayList ingredients = new ArrayList();

            while(line.contains(",")) {
                comma = line.indexOf(",");
                String item = line.substring(0, comma);
                line = line.replaceFirst(item + ",", "");
                int meta = 0;
                if (item.contains(";")) {
                    meta = Integer.parseInt(item.substring(item.indexOf(";") + 1));
                    item = item.replace(";" + meta, "");
                }

                Identifier id = new Identifier((String)null, item, meta);
                ingredients.add(id);
            }

            int meta = 0;
            if (line.contains(";")) {
                meta = Integer.parseInt(line.substring(line.indexOf(";") + 1));
                line = line.replace(";" + meta, "");
            }

            Identifier id = new Identifier((String)null, line, meta);
            ingredients.add(id);

            try {
                JsonWriter writer = new JsonWriter(new FileWriter(JsonGenerator.bindDir + "/output/recipes/" + outputName + ".json"));
                writer.beginObject().setIndent("   ");
                writer.name("type").value("minecraft:crafting_shapeless");
                writer.name("ingredients");
                writer.beginArray().setIndent("   ");

                for(int i = 0; i < ingredients.size(); ++i) {
                    writer.beginObject().setIndent("   ");
                    writer.name("item").value(((Identifier)ingredients.get(i)).getItem());
                    writer.name("data").value((long)((Identifier)ingredients.get(i)).getMeta());
                    writer.endObject();
                }

                writer.endArray();
                writer.name("result");
                writer.beginObject().setIndent("   ");
                writer.name("item").value(output);
                writer.name("data").value((long)outputMeta);
                writer.name("count").value((long)outputCount);
                writer.endObject();
                if (!group.isEmpty()) {
                    writer.name("group").value(group);
                }

                writer.endObject();
                writer.close();
                ++JsonGenerator.recipes;
                JsonGenerator.logger.logRecipe(outputName);
            } catch (Exception var13) {
                var13.printStackTrace();
                JsonGenerator.logger.log(var13.toString());
                JsonGenerator.logger.end();
            }
        }

        JsonGenerator.toGenerate.clear();
    }
}
