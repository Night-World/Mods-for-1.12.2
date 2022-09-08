/*
 * Copyright (c) 2022  Image by FoxesWorld
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

import foxesworld.hardcontent.gui.mainmenu.configuration.Alignment;
import foxesworld.hardcontent.gui.mainmenu.configuration.GuiConfig;
import foxesworld.hardcontent.gui.mainmenu.lib.textures.ITexture;

public class Image extends Element
{
	public int posX;
	public int posY;

	public int width;
	public int height;

	public ITexture image;
	public ITexture hoverImage;
	public Alignment alignment;
	
	public boolean ichBinEineSlideshow;
	public Slideshow slideShow;

	public Image(GuiConfig parent, int posX, int posY, int width, int height, Alignment alignment)
	{
		super(parent);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;

		this.alignment = alignment;
	}
}
