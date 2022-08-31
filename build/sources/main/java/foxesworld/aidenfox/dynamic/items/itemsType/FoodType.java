/*
 * Copyright (c) 2022  ItemFood by FoxesWorld
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

package foxesworld.aidenfox.dynamic.items.itemsType;

import foxesworld.aidenfox.cfg.CreativeTab;
import foxesworld.aidenfox.cfg.Environment;
import foxesworld.aidenfox.dynamic.items.itemsType.actions.FoodActions;
import foxesworld.aidenfox.methods.PlayerMethods;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static foxesworld.aidenfox.methods.Utils.addLore;

public class FoodType extends ItemFood {

    private String itemName;
    private String onEatenEffect;
    private int actionCoolDown;


    public FoodType(String name, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible, String onEatenEffect, int actionCoolDown) {
        super(amount, saturation, isWolfFood);
        this.itemName = name;
        this.onEatenEffect = onEatenEffect;
        this.actionCoolDown = actionCoolDown;
        this.setTranslationKey(name);
        this.setRegistryName(Environment.MODID, name);
        if (alwaysEdible) {
            this.setAlwaysEdible();
        }
        this.setCreativeTab(CreativeTab.MOD_TAB);

        Environment.ITEMS.put(name, this);
    }

    @Override
    protected void onFoodEaten(ItemStack item, World world, EntityPlayer player) {
        if (!onEatenEffect.equals("")) {
            PlayerMethods pMethods = new PlayerMethods(player);
            FoodActions foodActions = new FoodActions(item, world, player, onEatenEffect, pMethods);
            foodActions.performAction();
            //player.sendMessage(new TextComponentString(onEatenEffect));
        }
        if (!player.isCreative()) {
            player.getCooldownTracker().setCooldown(this, this.actionCoolDown);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addLore(this.itemName, "item", tooltip);
    }
}