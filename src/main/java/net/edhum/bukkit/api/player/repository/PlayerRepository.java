package net.edhum.bukkit.api.player.repository;

import net.edhum.bukkit.api.player.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface PlayerRepository {

    void add(Player player);

    boolean contains(Predicate<Player> predicate);

    Optional<Player> find(Predicate<Player> predicate);

    Collection<Player> findAll(Predicate<Player> predicate);

    void remove(UUID uuid);
}
