package foxesworld.hardcontent.dynamic.block.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.cfg.Environment;
import foxesworld.hardcontent.dynamic.block.blockType.Block;
import foxesworld.hardcontent.dynamic.block.blockType.Slab;
import foxesworld.hardcontent.dynamic.block.blockType.Stairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.List;

import static foxesworld.hardcontent.cfg.ConfigCreator.CONFIG.regBlocks;
import static foxesworld.hardcontent.dynamic.JsonGenerated.JsonGenerated.genBlock;
import static foxesworld.hardcontent.dynamic.JsonGenerated.stairs.BlockStairGenerator.generateBlockStair;

public class BlocksParser {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public BlocksParser(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }

    public void readFromJson(String jsonIn) {
        if (regBlocks) {
            gson = new Gson();
            String blockName;
            String blockType;
            String fromBlock;
            Material blockMaterial = Material.CIRCUITS;
            SoundType blockSound = SoundType.METAL;
            String blockHarvestTool = "pickaxe";
            Integer blockHarvestLevel = 2;
            float blockHardness = 10;
            float blockResistance = 50;
            boolean creatureSpawn = false;
            String itemDrop = "";
            int dropAmmount;
            String[] display;
            TypeToken<List<BlockAttributes>> typeToken = new TypeToken<List<BlockAttributes>>() {
            };
            List<BlockAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
            for (BlockAttributes obj : object) {
                blockName = obj.getBlockName();
                blockType = obj.getBlockType();
                dropAmmount = 1;
                switch (blockType) {

                    case "Block":
                        blockMaterial = obj.getBlockMaterial().getType();
                        blockSound = obj.getBlockSound().getSnd();
                        blockHarvestTool = obj.getBlockTool();
                        blockHarvestLevel = obj.getBlockHarvestLevel();
                        blockHardness = obj.getBlockHardness();
                        blockResistance = obj.getBlockResistance();
                        creatureSpawn = obj.getCreatureSpawn();
                        itemDrop = obj.getItemDrop();
                        display = obj.getDisplay();

                        if (!itemDrop.equals("")) {
                            dropAmmount = obj.getDropAmmount();
                        }
                        genBlock(blockName);
                        new Block(blockName, blockMaterial, blockSound, blockHarvestTool, blockHarvestLevel, blockHardness, blockResistance, creatureSpawn, itemDrop, dropAmmount);
                        break;

                    case "Stairs":
                        fromBlock = obj.getFromBlock();
                        generateBlockStair(blockName);
                        new Stairs(blockName, Environment.BLOCKS.get(fromBlock).getDefaultState());
                        break;

                    case "Slab":
                        fromBlock = obj.getFromBlock();
                        new Slab(blockName, blockMaterial, Environment.BLOCKS.get(fromBlock).getDefaultState());
                        break;
                }

            }
        }
    }
    public String getFileName() {
        return fileName;
    }

    public String getFileDir() {
        return fileDir;
    }
}
