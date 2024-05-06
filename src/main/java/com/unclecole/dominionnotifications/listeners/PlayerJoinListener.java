package com.unclecole.dominionnotifications.listeners;

import com.unclecole.dominionnotifications.database.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        PlayerData.load(event.getPlayer().getUniqueId().toString());
    }
}
