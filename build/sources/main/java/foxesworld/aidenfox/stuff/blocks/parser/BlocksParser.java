package foxesworld.aidenfox.stuff.blocks.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.aidenfox.stuff.blocks.Blocks;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.List;

public class BlocksParser {

    private String blocksFileName;
    private String MODID;
    private Gson gson;

    public BlocksParser(String blocksFileName, String MODID) {
        this.blocksFileName = blocksFileName;
        this.MODID = MODID;
    }

    public void readTplFile() {
        FileAsStream structuresJsonStream = new FileAsStream(this.blocksFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }

    private void readFromJson(String jsonIn) {
        gson = new Gson();
        TypeToken<List<BlockAttributes>> typeToken = new TypeToken<List<BlockAttributes>>() {
        };
        List<BlockAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
        for (BlockAttributes obj : object) {
            String blockName = obj.getBlockName();
            Material blockMaterial = obj.getBlockMaterial().getType();
            SoundType blockSound = obj.getBlockSound().getSnd();
            String blockHarvestTool = obj.getBlockTool();
            Integer blockHarvestLevel = obj.getBlockHarvestLevel();
            float blockHardness = obj.getBlockHardness();
            float blockResistance = obj.getBlockResistance();
            boolean creatureSpawn = obj.getCreatureSpawn();
            String itemDrop = obj.getItemDrop();
            new Blocks(blockName, blockMaterial, blockSound, blockHarvestTool, blockHarvestLevel, blockHardness, blockResistance, creatureSpawn, itemDrop) {};
        }
    }
}
