package foxesworld.aidenfox.stuff.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Player {

    private EntityPlayer player;

    public Player() {
        this.player = Minecraft.getMinecraft().player;
    }
}
