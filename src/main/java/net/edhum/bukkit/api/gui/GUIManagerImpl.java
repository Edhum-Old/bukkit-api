package net.edhum.bukkit.api.gui;

import com.google.inject.Inject;
import net.edhum.bukkit.api.player.Player;

public class GUIManagerImpl implements GUIManager {

    private final GUIRepository guiRepository;

    @Inject
    public GUIManagerImpl(GUIRepository guiRepository) {
        this.guiRepository = guiRepository;
    }

    @Override
    public void open(GUI gui, Player player) {
        gui.open(player);
        this.guiRepository.add(player, gui);
    }

    @Override
    public void close(GUI gui, Player player) {
        gui.close(player);
        this.guiRepository.remove(player);
    }
}
