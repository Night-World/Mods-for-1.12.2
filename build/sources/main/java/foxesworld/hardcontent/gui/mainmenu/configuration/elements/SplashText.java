/*
 * Copyright (c) 2022  SplashText by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.configuration.elements;

import foxesworld.hardcontent.gui.mainmenu.configuration.GuiConfig;
import foxesworld.hardcontent.gui.mainmenu.configuration.Alignment;
import foxesworld.hardcontent.gui.mainmenu.lib.texts.IText;
import foxesworld.hardcontent.gui.mainmenu.lib.texts.TextResourceLocation;

public class SplashText extends Element {
    public IText texts;
    public int posX;
    public int posY;
    public int color;
    public Alignment alignment;
    public boolean synced;

    public SplashText(GuiConfig parent, int posX, int posY, int color, Alignment alignment) {
        super(parent);
        this.posX = posX;
        this.posY = posY;
        this.alignment = alignment;
        this.color = color;
        this.texts = new TextResourceLocation("texts/splashes.txt");
        this.synced = false;

        if (this.alignment == null) {
            this.alignment = parent.getAlignment("button");
        }
    }

    public SplashText(GuiConfig parent, int posX, int posY, String alignment) {
        this(parent, posX, posY, -256, parent.getAlignment(alignment));
    }

    public SplashText(GuiConfig parent, int posX, int posY, int color, String alignment) {
        this(parent, posX, posY, color, parent.getAlignment(alignment));
    }

    public void setSplashTexts(IText texts) {
        this.texts = texts;
    }
}
