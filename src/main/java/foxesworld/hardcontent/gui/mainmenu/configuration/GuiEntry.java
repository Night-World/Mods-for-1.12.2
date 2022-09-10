/*
 * Copyright (c) 2022  GuiEntry by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.configuration;

import net.minecraft.client.Minecraft;
import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;

public class GuiEntry {
    GuiCustom standard;

    GuiCustom auto;
    GuiCustom small;
    GuiCustom normal;
    GuiCustom large;

    public GuiCustom getCurrentGUI() {
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;

        if (guiScale == 0 && auto != null) {
            return auto;
        }

        if (guiScale == 1 && small != null) {
            return small;
        }

        if (guiScale == 2 && normal != null) {
            return normal;
        }

        if (guiScale == 3 && large != null) {
            return large;
        }

        return standard;
    }
}