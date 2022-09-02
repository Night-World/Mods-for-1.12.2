package foxesworld.hardcontent.dynamic.block;

import foxesworld.hardcontent.dynamic.block.blockType.Block;
import foxesworld.hardcontent.dynamic.block.blockType.Stairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static foxesworld.hardcontent.methods.Utils.debugSend;

public class Blocks {

    protected String name;
    protected boolean creatureSpawn;
    protected String itemDrop;
    protected int dropAmmount;
    protected int fortune;

    public Blocks(String name, String blockType, Material material, SoundType snd, String harvestTool, Integer harvestLevel, float hardness, float resistance, boolean creatureSpawn, String itemDrop, int dropAmmount) {
        switch (blockType) {
            case "Block":
                new Block(name, material, snd, harvestTool, harvestLevel, hardness, resistance, creatureSpawn, itemDrop, dropAmmount);
                break;

            case "Stairs":
                new Stairs(name, Block.getBlockFromName("STONE").getDefaultState());
                break;

            case "Slab":
                break;

            default:
                debugSend("Unexpected value: " + blockType);
        }
    }
}