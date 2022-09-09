/*
 * Copyright (c) 2022  GuiCustomLabel by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.gui;

import foxesworld.hardcontent.gui.mainmenu.configuration.elements.Label;
import foxesworld.hardcontent.gui.mainmenu.lib.StringReplacer;
import foxesworld.hardcontent.gui.mainmenu.lib.texts.TextString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

import static foxesworld.hardcontent.gui.mainmenu.lib.ANCHOR.MIDDLE;
import static foxesworld.hardcontent.gui.mainmenu.lib.ANCHOR.START;

public class GuiCustomLabel extends Gui {
    Label text;
    int posX, posY;

    FontRenderer fontRendererObj;

    int width;
    int height;

    GuiCustom parent;

    static final String TIME_FORMAT = "HH:mm";
    static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
    static Field mcpversionField;
    public static String mcpversion;

    boolean hovered;

    static {
        try {
            mcpversionField = Loader.class.getDeclaredField("mcpversion");
            mcpversionField.setAccessible(true);
            mcpversion = (String) mcpversionField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuiCustomLabel(GuiCustom customGUI, Label text, int posX, int posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.parent = customGUI;
        fontRendererObj = Minecraft.getMinecraft().fontRenderer;

        hovered = false;

        width = fontRendererObj.getStringWidth(text.text.get());
        height = fontRendererObj.FONT_HEIGHT;

        if (text.name.equals("fml")) {
            String string = "";
            List<String> brandings = FMLCommonHandler.instance().getBrandings(true);
            for (int i = 0; i < brandings.size(); i++) {
                String brd = brandings.get(i);
                if (!com.google.common.base.Strings.isNullOrEmpty(brd)) {
                    string += brd + ((i < brandings.size() - 1) ? "\n" : "");
                }
            }
            this.text.text = this.text.hoverText = new TextString(string);
        }
    }

    public void drawLabel(Minecraft mc, int mouseX, int mouseY) {
        if (text.fontSize != 1F) {
            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(text.fontSize, text.fontSize, 1);
            GlStateManager.translate(-posX, -posY, 0);
        }

        String toDraw = hovered ? text.hoverText.get() : text.text.get();

        boolean newHovered = isMouseAboveLabel(mouseX, mouseY);

        if (newHovered && !hovered && text.hoverSound != null) {
            Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(text.hoverSound), SoundCategory.MASTER, 1F, 1F, false, 0, AttenuationType.NONE, 0, 0, 0));
        }

        hovered = newHovered;

        if (toDraw.contains("\n")) {
            int modY = 0;
            String[] lines = toDraw.split("\n");
            for (String line : lines) {
                String lineDraw = StringReplacer.replacePlaceholders(line);

                int textWidth = fontRendererObj.getStringWidth(lineDraw);

                int offsetX = text.anchor == START ? 0 : (text.anchor == MIDDLE ? -(textWidth / 2) : (-textWidth));

                if (hovered) {
                    this.drawString(fontRendererObj, lineDraw, posX + offsetX, posY + modY, text.hoverColor);
                } else {
                    this.drawString(fontRendererObj, lineDraw, posX + offsetX, posY + modY, text.color);
                }

                modY += fontRendererObj.FONT_HEIGHT;
            }
        } else {
            toDraw = StringReplacer.replacePlaceholders(toDraw);
            int textWidth = fontRendererObj.getStringWidth(toDraw);

            int offsetX = text.anchor == START ? 0 : (text.anchor == MIDDLE ? -(textWidth / 2) : (-textWidth));

            if (hovered) {
                this.drawString(fontRendererObj, toDraw, posX + offsetX, posY, text.hoverColor);
            } else {
                this.drawString(fontRendererObj, toDraw, posX + offsetX, posY, text.color);
            }
        }

        if (text.fontSize != 1F) {
            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(1 / text.fontSize, 1 / text.fontSize, 1);
            GlStateManager.translate(-posX, -posY, 0);
        }
    }

    private boolean isMouseAboveLabel(int mouseX, int mouseY) {
        String stringText = this.text.text.get();

        if (stringText.contains("\n")) {
            String[] lines = stringText.split("\n");

            for (int i = 0; i < lines.length; i++) {
                int width = this.fontRendererObj.getStringWidth(lines[i]);
                int height = this.fontRendererObj.FONT_HEIGHT;

                int modX = 0;

                switch (text.anchor) {
                    case END:
                        modX = -width;
                        break;
                    case MIDDLE:
                        modX = -width / 2;
                        break;
                    case START:
                        break;
                    default:
                        break;
                }

                if (mouseX >= posX + modX && mouseY >= posY + this.fontRendererObj.FONT_HEIGHT * i && mouseX < posX + width + modX && mouseY < posY + this.fontRendererObj.FONT_HEIGHT * i + height) {
                    return true;
                }
            }

            return false;
        } else {
            int width = this.fontRendererObj.getStringWidth(stringText);
            int height = this.fontRendererObj.FONT_HEIGHT;

            // Anchor Difference
            int modX = 0;

            switch (text.anchor) {
                case END:
                    modX = -width;
                    break;
                case MIDDLE:
                    modX = -width / 2;
                    break;
                case START:
                    break;
                default:
                    break;
            }

            return mouseX >= posX + modX && mouseY >= posY && mouseX < posX + width + modX && mouseY < posY + height;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean flag = isMouseAboveLabel(mouseX, mouseY);

        if (flag && text.action != null) {
            if (text.pressSound != null) {
                Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(text.pressSound), SoundCategory.MASTER, 1F, 1F, false, 0, AttenuationType.NONE, 0, 0, 0));
            } else {
                Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation("ui.button.click"), SoundCategory.MASTER, 1F, 1F, false, 0, AttenuationType.NONE, 0, 0, 0));
            }

            text.action.perform(this.text, parent);
        }
    }
}
