package foxesworld.aidenfox.stuff.tools.parser;

import foxesworld.aidenfox.Content;
import net.minecraft.item.Item;

public enum ToolMaterial {
    DIRT_MATERIAL(Content.DIRT_MATERIAL),
    WOOD(Item.ToolMaterial.WOOD),
    STONE(Item.ToolMaterial.STONE),
    IRON(Item.ToolMaterial.IRON),
    GOLD(Item.ToolMaterial.GOLD),
    DIAMOND(Item.ToolMaterial.DIAMOND);

    private final Item.ToolMaterial toolMaterial;

    ToolMaterial(net.minecraft.item.Item.ToolMaterial toolMaterial) {
        this.toolMaterial = toolMaterial;
    }

    public Item.ToolMaterial getType() {
        return toolMaterial;
    }
}
