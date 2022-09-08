/*
 * Copyright (c) 2022  Button by FoxesWorld
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
import foxesworld.hardcontent.gui.mainmenu.lib.actions.IAction;
import foxesworld.hardcontent.gui.mainmenu.lib.texts.IText;
import foxesworld.hardcontent.gui.mainmenu.lib.textures.ITexture;

public class Button extends Element {
    public String name;
    public IText text;
    public IText hoverText;
    public IAction action;
    public IText tooltip;

    public Alignment alignment;
    public int posX;
    public int posY;
    public int width;
    public int height;
    public int imageWidth;
    public int imageHeight;

    public ITexture texture;

    public int normalTextColor;
    public int hoverTextColor;
    public boolean shadow;

    public String pressSound;
    public String hoverSound;

    public int textOffsetX;
    public int textOffsetY;

    public int wrappedButtonID;

    public Button(GuiConfig parent, IText text, int posX, int posY, int width, int height, Alignment alignment) {
        super(parent);
        this.text = text;

        this.posX = posX;
        this.posY = posY;
        this.width = this.imageWidth = width;
        this.height = this.imageHeight = height;
        this.alignment = alignment;
        this.texture = null;
        this.normalTextColor = 14737632;
        this.hoverTextColor = 16777120;
        this.shadow = true;
        this.wrappedButtonID = -1;
        this.action = null;
        this.tooltip = null;
        this.hoverText = text;

        this.textOffsetX = 0;
        this.textOffsetY = 0;

        if (this.alignment == null) {
            this.alignment = parent.getAlignment("button");
        }
    }

    public Button(GuiConfig parent, IText text, int posX, int posY, int width, int height) {
        this(parent, text, posX, posY, width, height, parent.getAlignment("button"));
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public void setPressSound(String pressSound) {
        this.pressSound = pressSound;
    }

    public void setHoverSound(String hoverSound) {
        this.hoverSound = hoverSound;
    }

    public void setStringAlignment(String stringAlignment) {
        this.alignment = parent.getAlignment(stringAlignment);
    }

    public Button setTexture(ITexture texture) {
        this.texture = texture;
        return this;
    }

    public void setWrappedButton(int wrappedButtonID) {
        this.wrappedButtonID = wrappedButtonID;
    }
}
