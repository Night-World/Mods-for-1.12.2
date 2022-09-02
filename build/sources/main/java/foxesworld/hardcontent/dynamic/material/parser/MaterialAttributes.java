/*
 * Copyright (c) 2022  MaterialAttributes by FoxesWorld
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

public class MaterialAttributes {

    String materialName;
    int materialHarvestLevel;
    int materialUsages;
    float materialEfficiency;
    float materialDamage;
    int materialEnchantability;
    String fixMaterial;
    int fixMaterialAmmount;
    int fixMaterialMeta;

    public String getMaterialName() {
        return materialName;
    }

    public int getMaterialHarvestLevel() {
        return materialHarvestLevel;
    }

    public int getMaterialUsages() {
        return materialUsages;
    }

    public float getMaterialEfficiency() {
        return materialEfficiency;
    }

    public float getMaterialDamage() {
        return materialDamage;
    }

    public int getMaterialEnchantability() {
        return materialEnchantability;
    }

    public String getFixMaterial() {
        return fixMaterial;
    }

    public int getFixMaterialAmmount() {
        return fixMaterialAmmount;
    }

    public int getFixMaterialMeta() {
        return fixMaterialMeta;
    }
}
