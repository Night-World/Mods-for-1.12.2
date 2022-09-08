/*
 * Copyright (c) 2022  DynamicEventHandler by FoxesWorld
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

package foxesworld.hardcontent.dynamic;

import foxesworld.hardcontent.dynamic.world.WorldEventListener;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static foxesworld.hardcontent.main.logger;

public class DynamicEventHandler {

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        int dimID = event.getWorld().provider.getDimension();
        logger.info("adding world listener for dimID: " + dimID + ", remote?: " + event.getWorld().isRemote);
        event.getWorld().addEventListener(new WorldEventListener(dimID));
    }


}
