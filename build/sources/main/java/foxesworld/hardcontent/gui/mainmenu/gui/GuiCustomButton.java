/*
 * Copyright (c) 2022  GuiCustomButton by FoxesWorld
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

import foxesworld.hardcontent.gui.mainmenu.configuration.elements.Button;
import foxesworld.hardcontent.gui.mainmenu.lib.StringReplacer;
import foxesworld.hardcontent.gui.mainmenu.lib.textures.ITexture;
import foxesworld.hardcontent.gui.mainmenu.util.LogicUtil;
import foxesworld.hardcontent.gui.mainmenu.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

import java.util.Iterator;
import java.util.List;

public class GuiCustomButton extends GuiButton {
    public Button b;
    ITexture texture;
    int normalText;
    int hoverText;

    public GuiCustomButton(int buttonId, Button b) {
        super(buttonId, b.posX, b.posY, b.width, b.height, I18n.format(b.text.get(), new Object[0]));

        this.texture = b.texture;
        this.enabled = b.status;
        this.normalText = b.normalTextColor;
        this.hoverText = b.hoverTextColor;
        this.b = b;
    }

    public void drawTooltip(Minecraft mc, int mouseX, int mouseY) {
        FontRenderer fontrenderer = mc.fontRenderer;
        if (hovered && this.b.tooltip != null) {
            this.drawHoveringText(mc, LogicUtil.getTooltip(this.b.tooltip.get()), mouseX, mouseY, fontrenderer);
        }
    }

    public void setStatus(boolean enable){
        this.enabled = enable;
    }

    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            if (this.b.name.equals("language") && this.texture == null) {
                mc.getTextureManager().bindTexture(GuiButton.BUTTON_TEXTURES);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                boolean hovering = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                int k = 106;

                if (hovering) {
                    k += this.height;
                }

                this.drawTexturedModalRect(this.x, this.y, 0, k, this.width, this.height);
                return;
            }
            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            boolean newHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            if (newHovered && !this.hovered && b.hoverSound != null) {
                Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(b.hoverSound), SoundCategory.MASTER, 1F, 1F, false, 0, AttenuationType.NONE, 0, 0, 0));
            }

            this.hovered = newHovered;
            int k = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            if (this.texture != null) {
                texture.bind();

                RenderUtil.drawPartialImage(this.x, this.y, 0, (k - 1) * b.imageHeight, b.width, b.height, b.imageWidth, b.imageHeight);
            } else {
                mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
                this.drawTexturedModalRect(this.x, this.y, 0, 46 + k * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            }

            this.mouseDragged(mc, mouseX, mouseY);
            int l = normalText;

            if (packedFGColour != 0) {
                l = packedFGColour;
            } else if (!this.enabled) {
                l = 10526880;
            } else if (this.hovered) {
                l = hoverText;
            }

            this.drawCenteredString(fontrenderer, hovered ? I18n.format(StringReplacer.replacePlaceholders(b.hoverText.get()), new Object[0]) : I18n.format(StringReplacer.replacePlaceholders(b.text.get()), new Object[0]), this.x + this.width / 2 + b.textOffsetX, this.y + (this.height - 8) / 2 + b.textOffsetY, l, b.shadow);
        }
    }

    protected void drawHoveringText(Minecraft mc, List textLines, int x, int y, FontRenderer font) {
        if (!textLines.isEmpty()) {
            int width = mc.currentScreen.width;
            int height = mc.currentScreen.height;
            GlStateManager.disableDepth();
            int k = 0;
            Iterator iterator = textLines.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                int l = font.getStringWidth(s);

                if (l > k) {
                    k = l;
                }
            }

            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;

            if (textLines.size() > 1) {
                i1 += 2 + (textLines.size() - 1) * 10;
            }

            if (j2 + k > width) {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > height) {
                k2 = this.height - i1 - 6;
            }

            this.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < textLines.size(); ++i2) {
                String s1 = (String) textLines.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0) {
                    k2 += 2;
                }

                k2 += 10;
            }

            this.zLevel = 0.0F;
            GlStateManager.enableDepth();
        }
    }

    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
        if (shadow) {
            fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color);
        } else {
            fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, false);
        }
    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        if (b.pressSound != null) {
            this.setStatus(false);
            soundHandlerIn.playSound(new PositionedSoundRecord(new ResourceLocation(b.pressSound), SoundCategory.MASTER, 1F, 1F, false, 0, AttenuationType.NONE, 0, 0, 0));
        } else {
            super.playPressSound(soundHandlerIn);
        }
    }

}
