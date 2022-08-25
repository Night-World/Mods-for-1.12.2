package foxesworld.aidenfox.world.structureGen;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.block.Block;

import java.io.StringReader;

import static foxesworld.aidenfox.util.Utils.debugSend;

public class StructureParser {

    private String tplFile;
    private String MODID;
    protected String structureName;
    protected Block topBlock;
    protected int biomeToGen;
    protected int rarity;
    private Gson gson;

    //BiomeIDS https://minecraft.fandom.com/wiki/Biome/ID

    public StructureParser(String filename, String MODID){
        this.tplFile = filename;
        this.MODID = MODID;
    }

    public void readTplFile(){
        FileAsStream structuresJsonStream = new FileAsStream("structures/"+this.tplFile, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }
    public StructureParser(String structureName, int biomeToGen, int rarity, Block blockToGenOn, StructureInstance structureInstance) {
        debugSend("Adding structure to generator - " + structureName);
        this.structureName = structureName;
        this.biomeToGen = biomeToGen;
        this.rarity = rarity;
        this.topBlock = blockToGenOn;
        Environment.STRUCTURES.add(this);
    }



    private void readFromJson(String JsonIn) {
        gson = new Gson();
        structureData[] thisStructureData = null;
        JsonReader reader = new JsonReader(new StringReader(JsonIn));
        thisStructureData = gson.fromJson(reader, structureData[].class);
        for (structureData construction : thisStructureData) {
            debugSend("Scanned structure - " + construction.getNbtFile());
                String structureName = construction.getNbtFile();
                int biomeToGen = construction.getBiome();
                int rarity = construction.getRarity();
                String blockToGenOn = construction.getBlockToGenOn();
                StructureInstance structureInstance = new StructureInstance(this.structureName);
            new StructureParser(structureName, biomeToGen, rarity, Block.getBlockFromName(blockToGenOn), structureInstance);

        }
    }

    public class structureData {
        @SerializedName("nbtFile")
        String nbtFile;
        @SerializedName("biome")
        Integer biome;
        @SerializedName("rarity")
        Integer rarity;
        @SerializedName("blockToGenOn")
        String blockToGenOn;

        public String getNbtFile() {
            return nbtFile;
        }

        public Integer getRarity(){
            return rarity;
        }

        public Integer getBiome(){
            return biome;
        }

        public String getBlockToGenOn(){
            return blockToGenOn;
        }
    }
}

