package foxesworld.aidenfox.world.structureGen.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.block.Block;

import java.io.StringReader;

import static foxesworld.aidenfox.util.Utils.debugSend;

public class StructureParser {

    private String tplFile;
    private String MODID;
    public String structureName;
    public Block topBlock;
    public Integer[] biomeToGen;
    public int rarity;
    private Gson gson;

    //BiomeIDS https://minecraft.fandom.com/wiki/Biome/ID

    public StructureParser(String filename, String MODID){
        this.tplFile = filename;
        this.MODID = MODID;
    }

    public void readTplFile(){
        FileAsStream structuresJsonStream = new FileAsStream(this.tplFile, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }
    public StructureParser(String structureName, Integer[] biomeToGen, int rarity, Block blockToGenOn, StructureInstance structureInstance) {
        debugSend("Adding structure to generator - " + structureName);
        this.structureName = structureName;
        this.biomeToGen = biomeToGen;
        this.rarity = rarity;
        this.topBlock = blockToGenOn;
        Environment.STRUCTURES.add(this);
    }



    private void readFromJson(String JsonIn) {
        gson = new Gson();
        StructureAttributes[] thisStructureData;
        JsonReader reader = new JsonReader(new StringReader(JsonIn));
        thisStructureData = gson.fromJson(reader, StructureAttributes[].class);
        for (StructureAttributes construction : thisStructureData) {
            debugSend("Scanned structure - " + construction.getNbtFile());
                String structureName = construction.getNbtFile();
                Integer[] biomeToGen = construction.getBiome();
                int rarity = construction.getRarity();
                String blockToGenOn = construction.getBlockToGenOn();
                StructureInstance structureInstance = new StructureInstance(this.structureName);
            new StructureParser(structureName, biomeToGen, rarity, Block.getBlockFromName(blockToGenOn), structureInstance);

        }
    }

}

