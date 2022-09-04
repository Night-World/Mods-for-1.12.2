package foxesworld.hardcontent.dynamic.block.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.cfg.ConfigCreator;
import foxesworld.hardcontent.dynamic.block.addingBlock.AddBlock;
import foxesworld.hardcontent.dynamic.block.addingBlock.BlockAttributes;

import java.util.List;

public class BlocksParser {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public BlocksParser(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }

    public void readFromJson(String jsonIn) {
        if (ConfigCreator.CONFIGgenerate.regBlocks) {
            gson = new Gson();
            TypeToken<List<BlockAttributes>> typeToken = new TypeToken<List<BlockAttributes>>() {
            };
            List<BlockAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
            for (BlockAttributes obj : object) {
                AddBlock AddBlock = new AddBlock(obj);
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
