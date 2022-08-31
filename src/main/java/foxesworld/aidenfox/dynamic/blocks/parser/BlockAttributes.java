package foxesworld.aidenfox.dynamic.blocks.parser;


public class BlockAttributes {

    private String blockName;
    private String blockType;
    private String fromBlock;
    private BlockMaterial blockMaterial;
    private BlockSound blockSound;
    private String blockTool;
    private int blockHarvestLevel;
    private float blockHardness;
    private float blockResistance;
    private boolean creatureSpawn;
    private String itemDrop;
    private int dropAmmount;

    public String getBlockName() {
        return blockName;
    }

    public String getBlockType() { return  blockType; }

    public String getFromBlock() {
        return fromBlock;
    }
    public BlockMaterial getBlockMaterial() {
        return blockMaterial;
    }

    public BlockSound getBlockSound() {
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

    public boolean getCreatureSpawn() { return creatureSpawn;
    }

    public String getItemDrop() {
        return itemDrop;
    }

    public int getDropAmmount() {
        return dropAmmount;
    }
}
