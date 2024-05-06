package com.unclecole.dominionnotifications.database.serializer;

import com.unclecole.dominionnotifications.DominionNotifications;

public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        DominionNotifications.getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     *
   */
    public <T> T load(T def, Class<T> clazz, String name) {
        return DominionNotifications.getPersist().loadOrSaveDefault(def, clazz, name);
    }



}
