package net.edhum.bukkit.api.player.repository.filter;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.player.Player;

import java.util.UUID;
import java.util.function.Predicate;

public class PlayerUUIDFilter implements Predicate<Player> {

    private final UUID uuid;

    @AssistedInject
    public PlayerUUIDFilter(@Assisted UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean test(Player player) {
        return player.getUniqueId().equals(this.uuid);
    }
}
