package foxesworld.hardcontent.cfg.contentCreation;

import foxesworld.hardcontent.cfg.Environment;

import java.io.*;
import java.util.ArrayList;

import static foxesworld.hardcontent.cfg.Environment.generatedDirName;
import static foxesworld.hardcontent.cfg.Environment.paramsDirName;

public class JsonGenerator {
    public static ArrayList<String> toGenerate = new ArrayList();
    public static File bindDir;
    public static int blockstates = 0;
    public static int blocks = 0;
    public static int items = 0;
    public static int recipes = 0;
    public static Logger logger;

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
        PrintWriter log = new PrintWriter(bindDir + "/generateLog.txt", "UTF-8");
        log.print("");
        log.close();

        File generated = new File(bindDir + "/" + generatedDirName);
        generated.mkdir();
        File item = new File(bindDir + "/"+generatedDirName +"/item/");
        item.mkdir();
        File block = new File(bindDir + "/"+generatedDirName+"/block/");
        block.mkdir();
        File blockstates = new File(bindDir + "/"+generatedDirName+"/blockstates/");
        blockstates.mkdir();
        File recipes = new File(bindDir + "/"+generatedDirName+"/recipes/");
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
}
