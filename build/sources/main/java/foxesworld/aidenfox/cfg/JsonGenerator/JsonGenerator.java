package foxesworld.aidenfox.cfg.JsonGenerator;

import foxesworld.aidenfox.cfg.Environment;

import java.io.*;
import java.util.ArrayList;

import static foxesworld.aidenfox.cfg.Environment.paramsDirName;

public class JsonGenerator {
    public static ArrayList<String> toGenerate = new ArrayList();
    public static File bindDir;
    public static int blockstates = 0;
    public static int blocks = 0;
    public static int items = 0;
    public static int recipes = 0;
    public static Logger logger;

    static String genDirName = "output";

    public JsonGenerator(File cfgDir)  {
        this.bindDir = new File(cfgDir + File.separator + Environment.MODID);
        try {
            logger = new Logger();
            firstRun();
            if (Environment.MODID == null) {
                UnsupportedOperationException ex = new UnsupportedOperationException("modid.txt cannot be empty!");
                logger.log(ex.toString());
                logger.end();
                throw ex;
            } else {
                //readToGenerateFile("items");
                //Generator.generateItem("item/generated");
                /*
                readToGenerateFile("handheld");
                Generator.generateItem("item/handheld");
                readToGenerateFile("block");
                Generator.generateBlock();
                readToGenerateFile("orientable");
                Generator.generateBlockOrientable();
                readToGenerateFile("variant");
                Generator.generateBlockVariants();
                readToGenerateFile("slab");
                Generator.generateBlockSlab();
                readToGenerateFile("stair");
                Generator.generateBlockStair();
                readToGenerateFile("shaped");
                Generator.generateRecipeShaped();
                readToGenerateFile("shapeless");
                Generator.generateRecipeShapeless(); */
                end();
            }
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public static void firstRun() throws IOException {
        PrintWriter log = new PrintWriter(bindDir + "/log.txt", "UTF-8");
        log.print("");
        log.close();

        File generated = new File(bindDir + "/" + genDirName);
        generated.mkdir();
        File item = new File(bindDir + "/"+genDirName +"/item/");
        item.mkdir();
        File block = new File(bindDir + "/"+genDirName+"/block/");
        block.mkdir();
        File blockstates = new File(bindDir + "/"+genDirName+"/blockstates/");
        blockstates.mkdir();
        File recipes = new File(bindDir + "/"+genDirName+"/recipes/");
        recipes.mkdir();
        File params = new File(bindDir + "/"+paramsDirName);
        params.mkdir();
        /*
        File items = new File(bindDir + "/"+paramsDirName+"/item.txt");
        items.createNewFile();
        File blocks = new File(bindDir + "/"+paramsDirName+"/block.txt");
        blocks.createNewFile();
        File handheld = new File(bindDir + "/"+paramsDirName+"/handheld.txt");
        handheld.createNewFile();
        File orientable = new File(bindDir + "/"+paramsDirName+"/orientable.txt");
        orientable.createNewFile();
        File variant = new File(bindDir + "/"+paramsDirName+"/variant.txt");
        variant.createNewFile();
        File slab = new File(bindDir + "/"+paramsDirName+"/slab.txt");
        slab.createNewFile();
        File stair = new File(bindDir + "/"+paramsDirName+"/stair.txt");
        stair.createNewFile();
        File shaped = new File(bindDir + "/"+paramsDirName+"/shaped.txt");
        shaped.createNewFile();
        File shapeless = new File(bindDir + "/"+paramsDirName+"/shapeless.txt");
        shapeless.createNewFile(); */
        try {
            PrintWriter writer = new PrintWriter(bindDir + "/README.txt", "UTF-8");
            writer.println("Minecraft JSON Generator");
            //writer.println("by Tschipp");
            writer.println("Version: "+Environment.VERSION);
            writer.println();
            writer.println("USAGE:");
            writer.println("First, enter your mod's modid in the modid.txt file. It can at most contain one modid, on one line");
            writer.println("Then you are ready to generate jsons!");
            writer.println("For examples, view Example.txt");
            writer.println("For new blocks/items/recipes, just create a new line in the corresponding file.");
            writer.close();
        } catch (IOException var18) {
        }

        printExample();
    }
/*
    public static void readModid() {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(bindDir + "/modid.txt");
            new BufferedReader(fr);

            String sCurrentLine;
            for(br = new BufferedReader(new FileReader(bindDir + "/modid.txt")); (sCurrentLine = br.readLine()) != null; MODID = sCurrentLine) {
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    } */

    public static void readToGenerateFile(String type) {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(bindDir + "/"+paramsDirName+"/" + type + ".txt");
            new BufferedReader(fr);
            br = new BufferedReader(new FileReader(bindDir +  "/"+paramsDirName+"/"  + type + ".txt"));

            String sCurrentLine;
            while((sCurrentLine = br.readLine()) != null) {
                toGenerate.add(sCurrentLine);
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public static void end() {
        logger.log("Created " + blocks + " Block Model Files");
        logger.log("Created " + items + " Item Model Files");
        logger.log("Created " + blockstates + " Blockstate Files");
        logger.log("Created " + recipes + " Recipe Files");
        logger.end();
    }

    public static void printExample() {
        try {
            PrintWriter writer = new PrintWriter(bindDir + "/Examples.txt", "UTF-8");
            writer.println("Normal Block:");
            writer.println("test_block");
            writer.println("This creates a blockstate file called 'test_block.json', a block model file called 'test_block.json' and an item model file called 'test_block.json'");
            writer.println("The block will try to use a texture called 'test_block', which is located in /textures/blocks");
            writer.println();
            writer.println("Handheld Item:");
            writer.println("test_sword");
            writer.println("This creates an item model file called 'test_sword.json'. The item model will look like a sword when held");
            writer.println("The item will try to use a texture called 'test_sword', which is located in /textures/items");
            writer.println();
            writer.println("Normal Item:");
            writer.println("test_apple");
            writer.println("This creates an item model file called 'test_apple.json'");
            writer.println("The item will try to use a texture called 'test_apple', which is located in /textures/items");
            writer.println();
            writer.println("Orientable Block:");
            writer.println("test_furnace");
            writer.println("This creates a blockstate file called 'test_furnace.json', a block model file called 'test_furnace.json' and an item model file called 'test_furnace.json' The block will behave like an orientable block (like a furnace)");
            writer.println("The block will try to use textures called 'test_furnace_top', 'test_furnace_front' and 'test_furnace_side', all of them are located in /textures/blocks");
            writer.println();
            writer.println("Slab Block:");
            writer.println("test");
            writer.println("This creates two blockstate files, one named 'test_slab.json', the other named 'test_slab_double'. Note how the _slab suffix is added automatically");
            writer.println("It also creates two block model files, one called 'test_slab.json' and one called 'test_slab_double.json'. It creates one item model file called 'test_slab.json'");
            writer.println("The block will try to use a texture called 'test', which is located in /textures/blocks");
            writer.println();
            writer.println("Stair Block:");
            writer.println("test");
            writer.println("This creates a blockstate file called 'test_stair.json'. Note how the _stair suffix is added automatically");
            writer.println("It also creates three block model files, one called 'test_stair.json', one called 'test_stair_inner.json' and one called 'test_stair_outer.json'. It creates one item model file called 'test_stair.json'");
            writer.println("The block will try to use a texture called 'test', which is located in /textures/blocks");
            writer.println();
            writer.println("Variant Block / Block with Properties:");
            writer.println("test_clay;blue,red,green:color");
            writer.println("This creates a blockstate file called 'test_clay.json'. It creates block model files for each variant, in this case 'blue.json', 'red.json' and 'green.json'. It also creates Item model files for all variants with the same names.");
            writer.println("The block will try to use textures called 'blue', 'red' and 'green', all of them are located in /textures/blocks");
            writer.println();
            writer.println("Shaped Recipes:");
            writer.println("3*minecraft:stone;3,(###,#P#,###),#,minecraft:dirt;2,P,minecraft:dirt:3,?group");
            writer.println("This creates a recipe file called 'stone.json'");
            writer.println("The first argument is the recipe output. Using NUMBER*, you can specify how many items are created. The ;3 after the minecraft:stone refers to the damage value of the output");
            writer.println("The second argument in brackets is the recipe. The lines are separated with commas (first is top line in crafting, second is middle line and third is last line). You can also not write lines (for 4x4 crafting). (##,##). You can use any characters as identifiers apart from *,;:?");
            writer.println("The next arguments specify what the characters in the second argument stand for. The format for items is the same (modid:name;meta). You cannot specify a count.");
            writer.println("The last argument is indicated by the '?'. It is the group name, if you want to group your recipes. If you don't write a ?, it will not assign the recipe to a group.");
            writer.println();
            writer.println("Shapeless Recipes:");
            writer.println("3*minecraft:stone;3,minecraft:dirt;2,minecraft:dirt:3,?group");
            writer.println("This creates a recipe file called 'stone.json'");
            writer.println("The first argument is the recipe output. Using NUMBER*, you can specify how many items are created. The ;3 after the minecraft:stone refers to the damage value of the output");
            writer.println("The next arguments specify the ingredients used in this recipe. The format for items is the same (modid:name;meta). You cannot specify a count.");
            writer.println("The last argument is indicated by the '?'. It is the group name, if you want to group your recipes. If you don't write a ?, it will not assign the recipe to a group.");
            writer.println();
            writer.close();
        } catch (IOException var1) {
        }

    }
}
