package net.edhum.bukkit.api.gui;

import net.edhum.bukkit.api.player.Player;

import java.util.Optional;

public interface GUIRepository {

    void add(Player player, GUI gui);

    Optional<GUI> get(Player owner);

    void remove(Player player);
}
