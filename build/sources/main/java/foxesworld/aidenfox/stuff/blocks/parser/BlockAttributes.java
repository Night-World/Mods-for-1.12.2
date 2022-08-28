package foxesworld.aidenfox.stuff.blocks.parser;


public class BlockAttributes {

    private String blockName;
    private BlockMaterial blockMaterial;
    private BlockSound blockSound;
    private String blockTool;
    private int blockHarvestLevel;
    private float blockHardness;
    private float blockResistance;
    private boolean creatureSpawn;
    private String itemDrop;

    public String getBlockName() {
        return blockName;
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

    public boolean getCreatureSpawn(){return  creatureSpawn;}

    public String getItemDrop(){ return  itemDrop;}
}
