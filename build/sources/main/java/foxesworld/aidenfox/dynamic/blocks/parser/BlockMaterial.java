package foxesworld.aidenfox.dynamic.blocks.parser;

import net.minecraft.block.material.Material;

public enum BlockMaterial {
    ROCK(Material.ROCK),
    CLAY(Material.CLAY),
    CIRCUITS(Material.CIRCUITS),
    WOOD(Material.WOOD),
    GROUND(Material.GROUND);

    private final Material type;
    BlockMaterial(Material type) {
       this.type = type;
    }

    public Material getType() {
        return type;
    }
}
