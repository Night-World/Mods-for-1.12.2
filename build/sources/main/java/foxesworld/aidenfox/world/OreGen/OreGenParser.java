package foxesworld.aidenfox.world.OreGen;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.aidenfox.util.FileAsStream;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class OreGenParser {

    private String oresFileName;
    private String MODID;
    private Gson gson;

    public OreGenParser(String oresFileName, String MODID) {
        this.oresFileName = oresFileName;
        this.MODID = MODID;
    }

    public void readTplFile() {
        FileAsStream structuresJsonStream = new FileAsStream(this.oresFileName, this.MODID);
        String jsonString = (String) structuresJsonStream.getFileContents();
        System.out.println(jsonString);
        readFromJson(jsonString);
    }

    private void readFromJson(String jsonIn) {
        gson = new Gson();
        TypeToken<List<OreGenAttributes>> typeToken = new TypeToken<List<OreGenAttributes>>() {
        };
        List<OreGenAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
        for (OreGenAttributes obj : object) {
            String oreName = obj.getOreName();
            int oreMinHeight = obj.getOreMinHeight();
            int oreMaxHeight = obj.getOreMaxHeight();
            int oreVeinSize = obj.getOreVeinSize();
            int oreSpawnTries = obj.getOreSpawnTries();
            String watchForBlock = obj.getWatchForBlock();
            GameRegistry.registerWorldGenerator(new OreGen(oreName, oreMinHeight, oreMaxHeight, oreVeinSize, oreSpawnTries, watchForBlock), 3);
        }
    }
}
