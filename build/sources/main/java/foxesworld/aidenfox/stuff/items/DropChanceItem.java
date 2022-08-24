package foxesworld.aidenfox.stuff.items;

import net.minecraft.item.Item;

public class DropChanceItem {
    public Item item;
    public float chance;
    public int count;

    public DropChanceItem(Item item, float chance, int count) {
        this.item = item;
        this.chance = chance;
        this.count = count;
    }
}
