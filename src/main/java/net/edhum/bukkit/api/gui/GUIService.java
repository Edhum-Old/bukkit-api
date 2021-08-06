package net.edhum.bukkit.api.gui;

import net.edhum.bukkit.api.player.Player;

public interface GUIService {

    void open(GUI gui, Player player);

    void close(GUI gui, Player player);
}
