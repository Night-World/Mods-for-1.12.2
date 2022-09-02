package foxesworld.hardcontent.dynamic.tools.parser;

import net.minecraft.item.Item;

import static foxesworld.hardcontent.cfg.Environment.MATERIALS;

public enum ToolMaterial {
    DIRT_MATERIAL(MATERIALS.get("foxesmod:dirt")),
    GRAVEL_MATERIAL(MATERIALS.get("foxesmod:gravel")),
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
