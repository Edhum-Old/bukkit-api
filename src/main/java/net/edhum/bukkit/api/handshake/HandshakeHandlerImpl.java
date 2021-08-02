package net.edhum.bukkit.api.handshake;

import com.google.inject.Inject;
import net.edhum.bukkit.api.player.Player;
import net.edhum.bukkit.api.player.PlayerService;

import java.sql.SQLException;
import java.util.UUID;

public class HandshakeHandlerImpl implements HandshakeHandler {

    private final PlayerService playerService;

    @Inject
    public HandshakeHandlerImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Player handshake(UUID uuid) throws SQLException {
        if (!this.playerService.playerExists(uuid)) {
            this.playerService.createPlayer(uuid);
        }

        return this.playerService.loadPlayer(uuid);
    }
}
