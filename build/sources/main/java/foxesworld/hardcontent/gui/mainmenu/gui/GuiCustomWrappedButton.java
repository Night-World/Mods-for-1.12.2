/*
 * Copyright (c) 2022  GuiCustomWrappedButton by FoxesWorld
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiCustomWrappedButton extends GuiCustomButton
{
	GuiButton wrappedButton;
	public int wrappedButtonID;

	public GuiCustomWrappedButton(int buttonId, int wrappedButtonID, Button b)
	{
		super(buttonId, b);

		this.wrappedButtonID = wrappedButtonID;
	}

	@Override
	public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (wrappedButton != null)
		{
			this.visible = this.enabled = wrappedButton.visible && wrappedButton.enabled;
		}
		else
		{
			this.visible = this.enabled = false;
		}
		super.func_191745_a(mc, mouseX, mouseY, partialTicks);
	}

	public void init(GuiButton wrappedButton)
	{
		this.wrappedButton = wrappedButton;
		if (wrappedButton == null)
		{
			this.visible = this.enabled = false;
		}
	}

	public GuiButton getWrappedButton()
	{
		return wrappedButton;
	}
}
