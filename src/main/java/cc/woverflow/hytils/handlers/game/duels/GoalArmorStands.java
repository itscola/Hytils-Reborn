/*
 * Hytils Reborn - Hypixel focused Quality of Life mod.
 * Copyright (C) 2022  W-OVERFLOW
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cc.woverflow.hytils.handlers.game.duels;

import cc.woverflow.hytils.HytilsReborn;
import cc.woverflow.hytils.config.HytilsConfig;
import cc.woverflow.hytils.util.locraw.LocrawInformation;
import gg.essential.api.EssentialAPI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GoalArmorStands {
    private static final String[] goalArmorStandNames = {"goal", "defend", "jump in to score"};

    @SubscribeEvent
    public void onEntityRenderer(RenderLivingEvent.Pre<EntityLivingBase> event) {
        LocrawInformation locraw = HytilsReborn.INSTANCE.getLocrawUtil().getLocrawInformation();
        if (HytilsConfig.hideGoalArmorStands && EssentialAPI.getMinecraftUtil().isHypixel() && locraw != null && locraw.getGameMode().contains("BRIDGE")) {
            if (event.entity instanceof EntityArmorStand) {
                String unformattedText = event.entity.getCustomNameTag().toLowerCase();
                for (String goalArmorStands : goalArmorStandNames) {
                    if (unformattedText.contains(goalArmorStands)) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
