/*
 * Copyright (c) 2022  ActionOpenModConfig by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.lib.actions;

import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class ActionOpenModConfig implements IAction {
    String modid;

    public ActionOpenModConfig(String modid) {
        this.modid = modid;
    }

    @Override
    public void perform(Object source, GuiCustom parent) {
        for (ModContainer mod : Loader.instance().getModList()) {
            if (mod.getModId().equals(modid)) {
                IModGuiFactory guiFactory = FMLClientHandler.instance().getGuiFactoryFor(mod);

                if (guiFactory != null) {
                    GuiScreen newScreen = guiFactory.createConfigGui(parent);
                    Minecraft.getMinecraft().displayGuiScreen(newScreen);
                }
            }
        }
    }

}
