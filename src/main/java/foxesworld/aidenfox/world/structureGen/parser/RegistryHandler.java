package foxesworld.aidenfox.world.structureGen.parser;

import foxesworld.aidenfox.world.structureGen.StructureGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    public static void preInitRegistries()
    {
        GameRegistry.registerWorldGenerator(new StructureGen(), 0);
    }
}