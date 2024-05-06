package com.unclecole.dominionnotifications.api;

import com.unclecole.dominionnotifications.database.PlayerData;
import org.bukkit.entity.Player;

public class NotificationAPI {

    public boolean getNotification(Player player, String notification) {
        return PlayerData.playerData.get(player.getUniqueId()).getOrDefault(notification.toLowerCase(), true);
    }

    public void setNotification(Player player, String notification, boolean value) {
        PlayerData.playerData.get(player.getUniqueId()).put(notification.toLowerCase(), value);
        PlayerData.save(player.getUniqueId().toString());
    }
}
