package net.edhum.bukkit.api.handshake;

import net.edhum.bukkit.api.player.Player;

import java.sql.SQLException;
import java.util.UUID;

public interface HandshakeHandler {

    Player handshake(UUID uuid) throws SQLException;
}
