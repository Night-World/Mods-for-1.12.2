package foxesworld.aidenfox.stuff.blocks;


public class BlockAttributes {

    private String blockName;
    private MaterialType blockMaterial;
    private BlockSounds blockSound;
    private String blockTool;
    private int blockHarvestLevel;
    private float blockHardness;
    private float blockResistance;

    public String getBlockName() {
        return blockName;
    }

    public MaterialType getBlockMaterial() {
        return blockMaterial;
    }

    public BlockSounds getBlockSound() {
        return blockSound;
    }

    public String getBlockTool() {
        return blockTool;
    }

    public int getBlockHarvestLevel() {
        return blockHarvestLevel;
    }

    public float getBlockHardness() {
        return blockHardness;
    }

    public float getBlockResistance() {
        return blockResistance;
    }
}
