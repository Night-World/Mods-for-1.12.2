/*
 * Copyright (c) 2022  Label by FoxesWorld
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
import foxesworld.hardcontent.gui.mainmenu.lib.ANCHOR;
import foxesworld.hardcontent.gui.mainmenu.lib.actions.ActionOpenLink;
import foxesworld.hardcontent.gui.mainmenu.lib.actions.IAction;
import foxesworld.hardcontent.gui.mainmenu.lib.texts.IText;

public class Label extends Element {
    public IText text;
    public IText hoverText;
    public String name;
    public int posX;
    public int posY;
    public int color;
    public int hoverColor;
    public Alignment alignment;
    public IAction action;

    public String pressSound;
    public String hoverSound;

    public float fontSize;

    public ANCHOR anchor;

    public Label(GuiConfig parent, String name, IText text, int posX, int posY, Alignment alignment, int color) {
        super(parent);
        this.name = name;
        this.text = this.hoverText = text;

        this.posX = posX;
        this.posY = posY;
        this.color = this.hoverColor = color;

        this.alignment = alignment;
        if (this.alignment == null) {
            this.alignment = parent.getAlignment("top_left");
        }

        this.fontSize = 1;

        this.anchor = ANCHOR.START;
    }

    public Label(GuiConfig parent, String name, IText text, int posX, int posY) {
        this(parent, name, text, posX, posY, parent.getAlignment("top_left"), -1);
    }

    public void setHoverColor(int hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAlignment(String alignment) {
        this.alignment = parent.getAlignment(alignment);
    }

    public void setLink(String link) {
        this.action = new ActionOpenLink(link);
    }
}
