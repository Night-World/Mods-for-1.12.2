package foxesworld.aidenfox.stuff.blocks;

import net.minecraft.block.material.Material;

public enum MaterialType {
    ROCK(Material.ROCK),
    CLAY(Material.CLAY),
    CIRCUITS(Material.CIRCUITS),
    WOOD(Material.WOOD),
    GROUND(Material.GROUND);

    private final Material type;
    MaterialType(Material type) {
       this.type = type;
    }

    public Material getType() {
        return type;
    }
}
