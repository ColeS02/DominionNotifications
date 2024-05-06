package com.unclecole.dominionnotifications.database;

import com.unclecole.dominionnotifications.database.serializer.Persist;
import com.unclecole.dominionnotifications.database.serializer.Serializer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {

    public static transient PlayerData instance = new PlayerData();

    public static transient HashMap<UUID, HashMap<String, Boolean>> playerData = new HashMap<>();

    public static HashMap<String, Boolean> notifications;

    public static void save(String uuid) {
        notifications = playerData.get(UUID.fromString(uuid));
        new Persist().save(instance, "/playerdata/" + uuid);
    }

    public static void load(String uuid) {
        notifications = new HashMap<>();
        new Serializer().load(instance, PlayerData.class, "/playerdata/" + uuid);
        playerData.put(UUID.fromString(uuid), notifications);
    }

}