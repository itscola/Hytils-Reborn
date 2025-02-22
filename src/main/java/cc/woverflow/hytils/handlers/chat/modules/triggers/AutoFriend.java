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

package cc.woverflow.hytils.handlers.chat.modules.triggers;

import cc.woverflow.hytils.HytilsReborn;
import cc.woverflow.hytils.config.HytilsConfig;
import cc.woverflow.hytils.handlers.chat.ChatReceiveModule;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

/**
 * Taken and adapted from AutoFriend under MIT
 * https://github.com/minemanpi/AutoFriend/blob/master/LICENSE
 * @author 2Pi
 */
public class AutoFriend implements ChatReceiveModule {
    @Override
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText().replace("\n", "");
        Matcher matcher = getLanguage().chatCleanerMysteryBoxFindRegex.matcher(message);
        if (matcher.matches()) {
            String name = matcher.group("name");
            if (name.startsWith("[")) {
                name = name.substring(name.indexOf("] ") + 2);
                HytilsReborn.INSTANCE.getCommandQueue().queue("/friend " + name);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return HytilsConfig.autoFriend;
    }

    @Override
    public int getPriority() {
        return -1;
    }
}
