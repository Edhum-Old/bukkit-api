package net.edhum.bukkit.api.player.repository;

import com.google.inject.Inject;
import net.edhum.bukkit.api.player.Player;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PluginCachedPlayerRepository implements PlayerRepository {

    private final Map<UUID, Player> players;

    @Inject
    public PluginCachedPlayerRepository() {
        this.players = new HashMap<>();
    }

    @Override
    public void add(Player player) {
        UUID uuid = player.getUniqueId();

        if (this.players.containsKey(uuid)) {
            throw new IllegalArgumentException(String.format("A player with uuid %s is already registered", uuid));
        }

        this.players.put(uuid, player);
    }

    @Override
    public boolean contains(Predicate<Player> predicate) {
        return this.find(predicate).isPresent();
    }

    @Override
    public Optional<Player> find(Predicate<Player> predicate) {
        return this.players.values().stream()
                .filter(predicate)
                .findAny();
    }

    @Override
    public Collection<Player> findAll(Predicate<Player> predicate) {
        return this.players.values().stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    @Override
    public void remove(UUID uuid) {
        if (!this.players.containsKey(uuid)) {
            throw new IllegalArgumentException(String.format("No player with uuid %s has been found", uuid));
        }

        this.players.remove(uuid);
    }
}
