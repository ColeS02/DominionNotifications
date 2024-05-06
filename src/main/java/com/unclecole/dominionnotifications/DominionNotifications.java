package com.unclecole.dominionnotifications;

import com.unclecole.dominionnotifications.api.NotificationAPI;
import com.unclecole.dominionnotifications.commands.NotificationsCmd;
import com.unclecole.dominionnotifications.database.PlayerData;
import com.unclecole.dominionnotifications.database.serializer.Persist;
import com.unclecole.dominionnotifications.listeners.PlayerJoinListener;
import com.unclecole.dominionnotifications.utils.MenuUtility;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DominionNotifications extends JavaPlugin {

    @Getter private static DominionNotifications instance;
    @Getter public static Persist persist;
    @Getter public static NotificationAPI api;

    @Override
    public void onEnable() {
        instance = this;
        persist = new Persist();
        api = new NotificationAPI();
        saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(MenuUtility.getListener(), this);
        getCommand("notifications").setExecutor(new NotificationsCmd(this));

        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            PlayerData.load(player.getUniqueId().toString());
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
