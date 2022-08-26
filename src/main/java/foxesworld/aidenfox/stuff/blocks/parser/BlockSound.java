package foxesworld.aidenfox.stuff.blocks.parser;

import net.minecraft.block.SoundType;

public enum BlockSound {
    GROUND(SoundType.GROUND),
    WOOD(SoundType.WOOD),
    CLOTH(SoundType.CLOTH),
    ANVIL(SoundType.ANVIL),
    GLASS(SoundType.GLASS),
    STONE(SoundType.STONE);

    private final SoundType snd;

    BlockSound(SoundType snd) {
        this.snd = snd;
    }

    public SoundType getSnd() {
        return snd;
    }
}
