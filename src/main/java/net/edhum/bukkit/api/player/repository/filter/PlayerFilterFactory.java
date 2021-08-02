package net.edhum.bukkit.api.player.repository.filter;

import java.util.UUID;

public interface PlayerFilterFactory {

    PlayerNameFilter name(String name);

    PlayerUUIDFilter uuid(UUID uuid);
}
