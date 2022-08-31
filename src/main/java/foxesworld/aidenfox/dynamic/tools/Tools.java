package foxesworld.aidenfox.dynamic.tools;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.dynamic.tools.parser.ToolAttributes;
import foxesworld.aidenfox.dynamic.tools.toolsType.*;
import foxesworld.aidenfox.methods.FileAsStream;
import net.minecraft.item.Item;

import java.io.StringReader;

public class Tools {

    private String toolsFileName;
    private String MODID;
    private Gson gson;

    public Tools(String toolsFileName) {
        this.toolsFileName = toolsFileName;
        this.MODID = Environment.MODID;
    }

    public void readTplFile() {
        FileAsStream structuresJsonStream = new FileAsStream(this.toolsFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        readFromJson(jsonString);
    }

    private void readFromJson(String JsonIn) {
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
}
