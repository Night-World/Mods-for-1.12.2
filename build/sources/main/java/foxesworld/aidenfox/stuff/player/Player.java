package foxesworld.aidenfox.stuff.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Player {

    private EntityPlayer player;

    public Player() {
        this.player = Minecraft.getMinecraft().player;
    }
}
