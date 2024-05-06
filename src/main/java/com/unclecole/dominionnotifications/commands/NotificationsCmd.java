package com.unclecole.dominionnotifications.commands;

import com.unclecole.dominionnotifications.DominionNotifications;
import com.unclecole.dominionnotifications.api.NotificationAPI;
import com.unclecole.dominionnotifications.utils.C;
import com.unclecole.dominionnotifications.utils.MenuUtility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.HashMap;

public class NotificationsCmd implements CommandExecutor {

    private DominionNotifications plugin;
    private FileConfiguration config;
    private NotificationAPI api;

    public NotificationsCmd(DominionNotifications plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        api = plugin.getApi();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {

        if(!(s instanceof Player)) return false;

        Player player = (Player) s;

        openMenu(player);


        return false;
    }

    public void openMenu(Player player) {
        MenuUtility gui = new MenuUtility(C.color(config.getString("GUI.Title")), config.getInt("GUI.Size"), player.getUniqueId());


        ConfigurationSection items = config.getConfigurationSection("Notifications");
        for(String key : items.getKeys(false)) {

            String toggled = "";

            if(api.getNotification(player, key)) toggled = C.color(config.getString("Notifications." + key + ".Enabled"));
            else toggled = C.color(config.getString("Notifications." + key + ".Disabled"));

            ItemBuilder item = new ItemBuilder(Material.getMaterial(config.getString("Notifications." + key + ".Material")));

            item.setName(C.color(config.getString("Notifications." + key + ".Title").replace("%notification%", toggled)));

            String finalToggled = toggled;
            config.getStringList("Notifications." + key + ".Lore").forEach(str -> {
                item.addLore(C.color(str.replace("%notification%", finalToggled)));
            });

            int slot = config.getInt("Notifications." + key + ".Slot");

            gui.setItem(item, slot, inventoryClickEvent -> {
                api.setNotification(player, key, !api.getNotification(player, key));
                openMenu(player);
            });
        }
        gui.openInventory(player);
    }
}
