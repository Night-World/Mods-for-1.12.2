/*
 * Copyright (c) 2022  ConfigStructure by FoxesWorld
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

package foxesworld.hardcontent.cfg;

import net.minecraftforge.common.config.Config;

public class ConfigStructure {

    public static class CONFIGgeneral {
        @Config.LangKey(Environment.MODID + ".cfg.general.debug")
        public Boolean debug = false;
    }

    public static class CONFIGdatagen {
        @Config.LangKey(Environment.MODID + ".cfg.datagen.materials")
        public boolean exportMaterials = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.blocks")
        public boolean exportBlocks = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.tools")
        public boolean exportTools = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.items")
        public boolean exportItems = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.ores")
        public boolean exportOreGen = true;
        @Config.LangKey(Environment.MODID + ".cfg.datagen.structures")
        public boolean exportStructures = true;
    }

    public static class CONFIGgenerate {
        @Config.Comment("Generate structures in world")
        @Config.LangKey(Environment.MODID + ".cfg.generate.structureGen")
        public boolean structureGen = true;
        @Config.Comment("Generate ores in world")
        @Config.LangKey(Environment.MODID + ".cfg.generate.oreGen")
        public boolean oreGen = true;
        @Config.Comment("Should we register materials")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regMaterials")
        public boolean regMaterials = true;
        @Config.Comment("Should we register items")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regItems")
        public boolean regItems = true;
        @Config.Comment("Should we register tools")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regTools")
        public boolean regTools = true;
        @Config.Comment("Should we register blocks")
        @Config.LangKey(Environment.MODID + ".cfg.generate.regBlocks")
        public boolean regBlocks = true;
    }
}
