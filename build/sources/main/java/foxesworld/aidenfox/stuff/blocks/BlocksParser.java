package foxesworld.aidenfox.stuff.blocks;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.io.StringReader;

public class BlocksParser {

    /*INPUT*/
    private String blocksFileName;
    private String MODID;


    private Gson gson;
    /*BLOCK*/
    private String blockName;
    private Material blockMaterial;
    private SoundType blockSound;
    private String blockTool;
    private int blockHarvestLevel;
    private float blockHardness;
    private float blockResistance;

    public BlocksParser(String blocksFileName, String MODID){
        this.blocksFileName = blocksFileName;
        this.MODID = MODID;
    }
/*
    public BlocksParser(String blockName, Material blockMaterial, SoundType blockSound, String blockTool, int blockHarvestLevel, float blockHardness, float blockResistance) {
        this.blockName = blockName;
        System.out.println("Adding block to BLIST - " + blockName);
        this.blockMaterial = blockMaterial;
        this.blockSound = blockSound;
        this.blockTool = blockTool;
        this.blockHarvestLevel = blockHarvestLevel;
        this.blockHardness = blockHardness;
        this.blockResistance = blockResistance;
        Environment.BLIST.add(this);
    } */

    public void readTplFile(){
        FileAsStream structuresJsonStream = new FileAsStream(this.blocksFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }

    private void readFromJson(String JsonIn) {
        gson = new Gson();
        blockList[] listOfBlocks = null;
        JsonReader reader = new JsonReader(new StringReader(JsonIn));
        listOfBlocks = gson.fromJson(reader, blockList[].class);
        for (blockList block : listOfBlocks) {
            String name = block.getBlockName();
            Material material = Material.ROCK;// block.getBlockMaterial();
            SoundType sound = SoundType.METAL;//block.getBlockSound();
            String harvestTool = block.getBlockTool();
            int harvestLevel = block.getBlockHarvestLevel();
            float hardness = block.getBlockHardness();
            float resistance = block.getBlockResistance();

            new Blocks(name, material, sound, harvestTool, harvestLevel, hardness, resistance){};
        }
    }

    public class blockList {
        @SerializedName("blockName")
        public String blockName;

        @SerializedName("blockMaterial")
        public String blockMaterial;

        @SerializedName("blockSound")
        public String blockSound;

        @SerializedName("blockTool")
        public String blockTool;
        @SerializedName("blockHarvestLevel")
        public int blockHarvestLevel;

        @SerializedName("blockHardness")
        public float blockHardness;

        @SerializedName("blockResistance")
        public float blockResistance;

        public String getBlockName(){
            return blockName;
        }

        public String getBlockMaterial(){
            return blockMaterial;
        }

        public String getBlockSound(){
            return blockSound;
        }

        public  String getBlockTool(){
            return  blockTool;
        }

        public int getBlockHarvestLevel(){
            return blockHarvestLevel;
        }

        public float getBlockHardness(){
            return  blockHardness;
        }

        public float getBlockResistance(){
            return  blockResistance;
        }
    }

}
