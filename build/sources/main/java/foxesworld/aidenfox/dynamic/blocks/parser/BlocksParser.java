package foxesworld.aidenfox.dynamic.blocks.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.dynamic.blocks.blockType.Block;
import foxesworld.aidenfox.dynamic.blocks.blockType.Slab;
import foxesworld.aidenfox.dynamic.blocks.blockType.Stairs;
import foxesworld.aidenfox.methods.FileAsStream;
import foxesworld.aidenfox.methods.Utils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.List;

import static foxesworld.aidenfox.cfg.ConfigCreator.regBlocks;
import static foxesworld.aidenfox.methods.BufferedFileReader.BufferedFileReader;

public class BlocksParser {

    private String fileName;
    private String fileDir;
    private String MODID;
    private Gson gson;

    public BlocksParser(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.MODID = Environment.MODID;
    }

    public void readTplFile(boolean inputStream) {
        String jsonString = "";
        if(inputStream) {
            FileAsStream structuresJsonStream = new FileAsStream(this.fileName, this.MODID);
            jsonString = (String) structuresJsonStream.getFileContents();
        } else {
            Utils.createIfnotExists(this.fileDir,this.fileName);
            jsonString = BufferedFileReader(fileDir+this.fileName);
        }

        readFromJson(jsonString);
    }

    private void readFromJson(String jsonIn) {
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
                        if (!itemDrop.equals("")) {
                            dropAmmount = obj.getDropAmmount();
                        }
                        new Block(blockName, blockMaterial, blockSound, blockHarvestTool, blockHarvestLevel, blockHardness, blockResistance, creatureSpawn, itemDrop, dropAmmount);
                        break;

                    case "Stairs":
                        fromBlock = obj.getFromBlock();
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
}
