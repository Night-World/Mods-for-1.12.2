package foxesworld.hardenedcore;/*
 * Copyright (c) 2022  Mantle by FoxesWorld
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

import foxesworld.hardenedcore.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static foxesworld.hardenedcore.cfg.Environment.*;

@Mod(modid = MODID, name = MODNAME, version = MODVER,dependencies = "required-after:forge@[14.21.1.2387,)", acceptedMinecraftVersions = "[1.12,1.13)")

public class HardenedCore {

    /* Instance of this mod, used for grabbing prototype fields */
    @Instance(MODID)
    public static HardenedCore instance;

    /* Proxies for sides, used for graphics processing */
    @SidedProxy(clientSide = clientSide, serverSide = serverSide)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}