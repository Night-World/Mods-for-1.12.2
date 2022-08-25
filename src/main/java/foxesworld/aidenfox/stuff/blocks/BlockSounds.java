package foxesworld.aidenfox.stuff.blocks;

import net.minecraft.block.SoundType;

public enum BlockSounds {
    GROUND(SoundType.GROUND),
    WOOD(SoundType.WOOD),
    CLOTH(SoundType.CLOTH),
    ANVIL(SoundType.ANVIL),
    GLASS(SoundType.GLASS),
    STONE(SoundType.STONE);

    private final SoundType snd;

    BlockSounds(SoundType snd) {
        this.snd = snd;
    }

    public SoundType getSnd() {
        return snd;
    }
}
