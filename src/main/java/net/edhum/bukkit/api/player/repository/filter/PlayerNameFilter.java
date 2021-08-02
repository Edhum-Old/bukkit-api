package net.edhum.bukkit.api.player.repository.filter;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.player.Player;

import java.util.function.Predicate;

public class PlayerNameFilter implements Predicate<Player> {

    private final String name;

    @AssistedInject
    public PlayerNameFilter(@Assisted String name) {
        this.name = name;
    }

    @Override
    public boolean test(Player player) {
        return player.getName().equalsIgnoreCase(this.name);
    }
}
