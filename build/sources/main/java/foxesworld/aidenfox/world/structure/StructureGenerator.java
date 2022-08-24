package foxesworld.aidenfox.world.structure;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.io.StringReader;

import static foxesworld.aidenfox.main.logger;

public class StructureGenerator {

    FileAsStream structuresJsonStream;
    protected String structureName;
    protected WorldGenStructure structureInstance;
    protected Block topBlock = Blocks.GRASS;
    protected int biomeToGen;
    protected int rarity;
    private Gson gson;

    //BiomeIDS https://minecraft.fandom.com/wiki/Biome/ID

    public StructureGenerator(){
        structuresJsonStream = new FileAsStream("structures.json", Environment.MODID);
        readFromJson((String) structuresJsonStream.getFileContents());
    }
    public StructureGenerator(String structureName, int biomeToGen, int rarity, Block blockToGenOn, WorldGenStructure structureInstance) {
        logger.info("Adding to generator structure - " + structureName);
        this.structureName = structureName;
        this.biomeToGen = biomeToGen;
        this.rarity = rarity;
        this.topBlock = blockToGenOn;
        this.structureInstance = new WorldGenStructure(this.structureName);
        Environment.STRUCTURES.add(this);
    }

    private void readFromJson(String JsonIn) {
        gson = new Gson();
        structureData[] thisStructureData = null;
        JsonReader reader = new JsonReader(new StringReader(JsonIn));
        thisStructureData = gson.fromJson(reader, structureData[].class);
        for (structureData construction : thisStructureData) {
            logger.info("Scanned structure - " + construction.getNbtFile());
                String structureName = construction.getNbtFile();
                int biomeToGen = construction.getBiome();
                int rarity = construction.getRarity();
                String blockToGenOn = construction.getBlockToGenOn();
                WorldGenStructure structureInstance = new WorldGenStructure(this.structureName);
            new StructureGenerator(structureName, biomeToGen, rarity, Block.getBlockFromName(blockToGenOn), structureInstance);

        }
    }

    public class structureData {
        String nbtFile;
        Integer biome;
        Integer rarity;
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

