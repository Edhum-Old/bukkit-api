package net.edhum.bukkit.api.gui.repository;

import com.google.inject.Inject;
import net.edhum.bukkit.api.gui.GUI;
import net.edhum.bukkit.api.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GUIRepositoryImpl implements GUIRepository {

    private final Map<UUID, GUI> guis;

    @Inject
    public GUIRepositoryImpl() {
        this.guis = new HashMap<>();
    }

    @Override
    public void add(Player player, GUI gui) {
        this.guis.put(player.getUniqueId(), gui);
    }

    @Override
    public Optional<GUI> get(Player owner) {
        return Optional.ofNullable(this.guis.get(owner.getUniqueId()));
    }

    @Override
    public void remove(Player player) {
        this.guis.remove(player.getUniqueId());
    }
}
