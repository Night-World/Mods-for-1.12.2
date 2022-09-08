/*
 * Copyright (c) 2022  MathUtil by FoxesWorld
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

package foxesworld.hardcontent.gui.mainmenu.util;

import javax.vecmath.Vector2f;

public class MathUtil
{
	public static boolean isPointInQuad(Vector2f pointP, Vector2f pointA, Vector2f pointB, Vector2f pointC, Vector2f pointD)
	{
		float apd = getTriangleArea(pointA, pointP, pointD);
		float dpc = getTriangleArea(pointD, pointP, pointC);
		float cpb = getTriangleArea(pointC, pointP, pointB);
		float pba = getTriangleArea(pointP, pointB, pointA);

		float rectArea = getQuadArea(pointA, pointB, pointC);

		float sum = apd + dpc + cpb + pba;

		if (sum > rectArea)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public static float getQuadArea(Vector2f pointA, Vector2f pointB, Vector2f pointC)
	{
		Vector2f abVec = (Vector2f) pointB.clone();
		abVec.sub(pointA);

		float ab = abVec.length();

		Vector2f bcVec = (Vector2f) pointC.clone();
		bcVec.sub(pointB);

		float bc = bcVec.length();

		return ab * bc;
	}

	public static float getTriangleArea(Vector2f pointA, Vector2f pointB, Vector2f pointC)
	{
		Vector2f ab = (Vector2f) pointB.clone();
		ab.sub(pointA);

		float a = ab.length();

		Vector2f bc = (Vector2f) pointC.clone();
		bc.sub(pointB);

		float b = bc.length();

		Vector2f ca = (Vector2f) pointA.clone();
		ca.sub(pointC);

		float c = ca.length();

		float s = (a + b + c) / 2;

		return (float) Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}
}
