/*
 * Copyright (c) 2022  GuiFakeMain by FoxesWorld
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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

import java.util.List;

public class GuiFakeMain extends GuiMainMenu
{
	public GuiFakeMain()
	{
		this.mc = Minecraft.getMinecraft();
	}
	
	public void initGui()
    {
		
    }
	
	public List<GuiButton> getButtonList()
	{
		return this.buttonList;
	}
}
