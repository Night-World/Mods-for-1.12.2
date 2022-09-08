/*
 * Copyright (c) 2022  TextureApng by FoxesWorld
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


package foxesworld.hardcontent.gui.mainmenu.lib.textures;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.ellerton.japng.Png;
import net.ellerton.japng.argb8888.Argb8888Bitmap;
import net.ellerton.japng.argb8888.Argb8888BitmapSequence;
import net.ellerton.japng.argb8888.Argb8888BitmapSequence.Frame;
import net.ellerton.japng.chunks.PngAnimationControl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;

public class TextureApng implements ITexture
{
	ResourceLocation rl;

	HashMap<Frame, Integer> frameTextureID;
	List<Frame> frames;

	PngAnimationControl animationControl;
	int currentFrame;

	boolean loaded;
	boolean errored;

	long lastTimeStamp;
	int currentFrameDelay;

	public TextureApng(String textureString)
	{
		rl = new ResourceLocation(textureString);
		errored = false;
	}

	@Override
	public void bind()
	{
		if (!loaded)
		{
			load();
			loaded = true;
		}

		if (errored)
		{
			GlStateManager.bindTexture(TextureUtil.MISSING_TEXTURE.getGlTextureId());
			return;
		}
		
		while (System.currentTimeMillis() - lastTimeStamp >= currentFrameDelay)
		{
			currentFrame++;
			if (currentFrame > animationControl.numFrames - 1)
			{
				currentFrame = 0;
			}

			Frame f = frames.get(currentFrame);

			float numerator = f.control.delayNumerator;
			float denominator = f.control.delayDenominator > 0 ? f.control.delayDenominator : 100;

			this.lastTimeStamp += currentFrameDelay;
			this.currentFrameDelay = (int) ((numerator / denominator) * 1000);
		}

		GlStateManager.bindTexture(frameTextureID.get(frames.get(currentFrame)));
	}

	private void load()
	{
		frameTextureID = new HashMap<Frame, Integer>();
		try
		{
			InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(rl).getInputStream();

			Argb8888BitmapSequence pngContainer = Png.readArgb8888BitmapSequence(inputStream);
			animationControl = pngContainer.getAnimationControl();

			frames = Collections.synchronizedList(pngContainer.getAnimationFrames());

			BufferedImage canvas = new BufferedImage(pngContainer.defaultImage.width, pngContainer.defaultImage.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = canvas.createGraphics();
			graphics.setBackground(new Color(0, 0, 0, 0));

			BufferedImage frameBackup = new BufferedImage(pngContainer.defaultImage.width, pngContainer.defaultImage.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphicsBackup = frameBackup.createGraphics();
			graphicsBackup.setBackground(new Color(0, 0, 0, 0));

			for (int i = 0; i < frames.size(); i++)
			{
				Frame f = frames.get(i);

				switch (f.control.blendOp)
				{
					case 0:
						graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
						break;
					case 1:
						graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
						break;
				}

				Argb8888Bitmap bitmap = f.bitmap;

				BufferedImage buffered = new BufferedImage(bitmap.width, bitmap.height, BufferedImage.TYPE_INT_ARGB);
				buffered.setRGB(0, 0, bitmap.width, bitmap.height, bitmap.getPixelArray(), 0, bitmap.width);

				graphicsBackup.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				graphicsBackup.drawImage(canvas, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

				graphics.drawImage(buffered, f.control.xOffset, f.control.yOffset, f.control.xOffset + f.control.width, f.control.yOffset + f.control.height, 0, 0, buffered.getWidth(), buffered.getHeight(), null);

				frameTextureID.put(f, TextureUtil.uploadTextureImageAllocate(GL11.glGenTextures(), canvas, false, false));

				switch (f.control.disposeOp)
				{
					case 0:
						break;
					case 1:
						graphics.clearRect(f.control.xOffset, f.control.yOffset, f.control.width, f.control.height);
						break;
					case 2:
						graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						graphics.drawImage(frameBackup, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
						break;
				}
			}
			
			graphics.dispose();
			graphicsBackup.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			errored = true;
			return;
		}
		
		Frame frame = frames.get(0);

		float numerator = frame.control.delayNumerator;
		float denominator = frame.control.delayDenominator > 0 ? frame.control.delayDenominator : 100;

		this.currentFrameDelay = (int) (numerator / denominator * 1000);
		this.lastTimeStamp = System.currentTimeMillis();
	}
}
 */

