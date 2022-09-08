/*
 * Copyright (c) 2022  Config by FoxesWorld
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

import foxesworld.hardcontent.gui.mainmenu.gui.GuiCustom;

import java.util.HashMap;

public class Config {
    HashMap<String, GuiEntry> guis;

    public Config() {
        guis = new HashMap<String, GuiEntry>();
    }

    public void addGui(String name, GuiCustom gc) {
        GuiEntry entry = guis.get(name);

        if (entry == null) {
            entry = new GuiEntry();
            guis.put(name, entry);
        }

        int scale = gc.guiConfig.guiScale;

        if (scale == -1) {
            entry.standard = gc;
        } else if (scale == 0) {
            entry.auto = gc;
        } else if (scale == 1) {
            entry.small = gc;
        } else if (scale == 2) {
            entry.normal = gc;
        } else if (scale == 3) {
            entry.large = gc;
        }
    }

    public GuiCustom getGUI(String name) {
        return guis.get(name).getCurrentGUI();
    }

    public void tick() {
        guis.values().forEach((ge) -> ge.getCurrentGUI().tick());
    }
}
