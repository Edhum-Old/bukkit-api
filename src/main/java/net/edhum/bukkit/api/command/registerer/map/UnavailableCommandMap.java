package net.edhum.bukkit.api.command.registerer.map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

public class UnavailableCommandMap {

    public static CommandMap getDefaultCommandMap() {
        return new SimpleCommandMap(Bukkit.getServer());
    }
}
