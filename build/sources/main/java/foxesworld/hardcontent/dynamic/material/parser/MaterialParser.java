/*
 * Copyright (c) 2022  MaterialParser by FoxesWorld
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foxesworld.hardcontent.dynamic.material.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foxesworld.hardcontent.dynamic.material.Material;

import java.util.List;

public class MaterialParser {

    private String fileName;
    private String fileDir;
    private Gson gson;

    public MaterialParser(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }

    public void readFromJson(String jsonIn) {
        gson = new Gson();
        TypeToken<List<MaterialAttributes>> typeToken = new TypeToken<List<MaterialAttributes>>() {
        };
        List<MaterialAttributes> object = gson.fromJson(jsonIn, typeToken.getType());
        for (MaterialAttributes obj : object) {
            String materialName = obj.getMaterialName();
            int materialHarvestLevel = obj.getMaterialHarvestLevel();
            int materialUsages = obj.getMaterialUsages();
            float materialEfficiency = obj.getMaterialEfficiency();
            float materialDamage = obj.getMaterialDamage();
            int materialEnchantability = obj.getMaterialEnchantability();
            String fixMaterial = obj.getFixMaterial();
            int fixMaterialAmmount = obj.getFixMaterialAmmount();
            int fixMaterialMeta = obj.getFixMaterialMeta();
            new Material(materialName, materialHarvestLevel, materialUsages, materialEfficiency, materialDamage, materialEnchantability, fixMaterial, fixMaterialAmmount, fixMaterialMeta);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDir() {
        return fileDir;
    }
}
