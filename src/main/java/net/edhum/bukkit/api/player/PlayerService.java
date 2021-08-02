package net.edhum.bukkit.api.player;

import java.sql.SQLException;
import java.util.UUID;

public interface PlayerService {

    boolean playerExists(UUID uuid) throws SQLException;

    void createPlayer(UUID uuid) throws SQLException;

    Player loadPlayer(UUID uuid) throws SQLException;

    void unloadPlayer(UUID uuid) throws SQLException;
}
