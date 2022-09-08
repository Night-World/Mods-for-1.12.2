/*
 * Copyright (c) 2022  ActionOpenGUI by FoxesWorld
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

import com.google.common.util.concurrent.Runnables;
import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;
import foxesworld.hardcontent.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.client.GuiModList;

public class ActionOpenGUI implements IAction {
    String guiName;

    public ActionOpenGUI(String guiName) {
        this.guiName = guiName;
    }

    @Override
    public void perform(Object source, GuiCustom menu) {
        GuiScreen gui = null;

        if (guiName.startsWith("custom.")) {
            String customName = guiName.substring(7);

            gui = main.INSTANCE.config.getGUI(customName);
        } else {
            if (guiName.equalsIgnoreCase("mods")) {
                gui = new GuiModList(menu);
            } else if (guiName.equalsIgnoreCase("singleplayer")) {
                gui = new GuiWorldSelection(menu);
            } else if (guiName.equalsIgnoreCase("singleplayer.createworld")) {
                gui = new GuiCreateWorld(menu);
            } else if (guiName.equalsIgnoreCase("multiplayer")) {
                gui = new GuiMultiplayer(menu);
            } else if (guiName.equalsIgnoreCase("options")) {
                gui = new GuiOptions(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("languages")) {
                gui = new GuiLanguage(menu, menu.mc.gameSettings, menu.mc.getLanguageManager());
            } else if (guiName.equalsIgnoreCase("options.ressourcepacks")) {
                gui = new GuiScreenResourcePacks(menu);
            } else if (guiName.equalsIgnoreCase("options.snooper")) {
                gui = new GuiSnooper(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("options.sounds")) {
                gui = new GuiScreenOptionsSounds(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("options.skinsettings")) {
                gui = new GuiCustomizeSkin(menu);
            } else if (guiName.equalsIgnoreCase("options.video")) {
                gui = new GuiVideoSettings(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("options.controls")) {
                gui = new GuiControls(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("options.multiplayer")) {
                gui = new ScreenChatOptions(menu, menu.mc.gameSettings);
            } else if (guiName.equalsIgnoreCase("mainmenu")) {
                gui = new GuiMainMenu();
            } else if (guiName.equalsIgnoreCase("realms")) {
                RealmsBridge realmsbridge = new RealmsBridge();
                realmsbridge.switchToRealms(Minecraft.getMinecraft().currentScreen);
            } else if (guiName.equalsIgnoreCase("credits")) {
                gui = new GuiWinGame(false, Runnables.doNothing());
            }
        }

        if (gui != null) {
            Minecraft.getMinecraft().displayGuiScreen(gui);
        }

    }

}
