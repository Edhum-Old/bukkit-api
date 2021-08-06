package net.edhum.bukkit.api.gui.repository;

import net.edhum.bukkit.api.gui.GUI;
import net.edhum.bukkit.api.player.Player;

import java.util.Optional;

public interface GUIRepository {

    void add(Player player, GUI gui);

    Optional<GUI> get(Player owner);

    void remove(Player player);
}
