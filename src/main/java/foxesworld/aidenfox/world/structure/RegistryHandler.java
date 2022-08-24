package foxesworld.aidenfox.world.structure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    public static void preInitRegistries()
    {
        GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
    }
}