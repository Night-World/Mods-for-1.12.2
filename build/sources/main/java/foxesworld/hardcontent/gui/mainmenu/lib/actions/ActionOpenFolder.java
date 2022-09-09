/*
 * Copyright (c) 2022  ActionOpenFolder by FoxesWorld
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

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ActionOpenFolder implements IAction {
    String folderName;

    public ActionOpenFolder(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public void perform(Object source, GuiCustom parent) {
        File toOpen = new File(Minecraft.getMinecraft().gameDir, folderName);

        boolean isInMinecraftFolder = false;
        try {
            File parentFile = toOpen.getCanonicalFile();
            while ((parentFile = parentFile.getParentFile()) != null) {
                if (parentFile.getCanonicalPath().equals(Minecraft.getMinecraft().gameDir.getCanonicalPath())) {
                    isInMinecraftFolder = true;
                }
            }

            if (isInMinecraftFolder) {
                if (toOpen.isDirectory() && Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(toOpen);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {

        }

    }

}
