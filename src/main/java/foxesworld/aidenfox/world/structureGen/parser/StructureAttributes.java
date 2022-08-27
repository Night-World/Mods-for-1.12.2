package foxesworld.aidenfox.world.structureGen.parser;

import com.google.gson.annotations.SerializedName;

public class StructureAttributes {
    @SerializedName("nbtFile")
    String nbtFile;
    @SerializedName("biome")
    Integer[] biome;
    @SerializedName("rarity")
    Integer rarity;
    @SerializedName("blockToGenOn")
    String blockToGenOn;

    public String getNbtFile() {
        return nbtFile;
    }

    public Integer[] getBiome() {
        return biome;
    }

    public Integer getRarity() {
        return rarity;
    }

    public String getBlockToGenOn() {
        return blockToGenOn;
    }
}
