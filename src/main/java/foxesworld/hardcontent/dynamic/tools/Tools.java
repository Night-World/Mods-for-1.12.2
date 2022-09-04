package foxesworld.hardcontent.dynamic.tools;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import foxesworld.hardcontent.cfg.ConfigCreator;
import foxesworld.hardcontent.dynamic.tools.parser.ToolAttributes;
import foxesworld.hardcontent.dynamic.tools.toolsType.*;
import net.minecraft.item.Item;

import java.io.StringReader;

public class Tools {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public Tools(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }


    public void readFromJson(String JsonIn) {
        if(ConfigCreator.CONFIGgenerate.regTools) {
            gson = new Gson();
            ToolAttributes[] toolsData;
            JsonReader reader = new JsonReader(new StringReader(JsonIn));
            toolsData = gson.fromJson(reader, ToolAttributes[].class);
            for (ToolAttributes tool : toolsData) {
                String toolName = tool.getToolName();
                String toolType = tool.getToolType();
                Item.ToolMaterial toolMaterial = tool.getToolMaterial().getType();

                toolCreator(toolName, toolType, toolMaterial);
            }
        }
    }

    private void toolCreator(String toolName, String toolType, Item.ToolMaterial toolMaterial) {
        switch (toolType) {
            case "Axe":
                new Axe(toolName, toolMaterial);
                break;

            case "Pickaxe":
                new Pickaxe(toolName, toolMaterial);
                break;

            case "Hoe":
                new Hoe(toolName, toolMaterial);
                break;

            case "Spade":
                new Spade(toolName, toolMaterial);
                break;

            case "Sword":
                new Sword(toolName, toolMaterial);
                break;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDir() {
        return fileDir;
    }
}
