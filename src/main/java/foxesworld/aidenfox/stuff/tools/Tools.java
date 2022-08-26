package foxesworld.aidenfox.stuff.tools;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import foxesworld.aidenfox.stuff.tools.parser.ToolAttributes;
import foxesworld.aidenfox.stuff.tools.toolsTypes.*;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraft.item.Item;

import java.io.StringReader;

public class Tools {

    private String toolsFileName;
    private String MODID;
    private Gson gson;

    public Tools(String toolsFileName, String MODID) {
        this.toolsFileName = toolsFileName;
        this.MODID = MODID;
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
