package net.edhum.bukkit.api.item.action;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.gui.GUI;
import net.edhum.bukkit.api.gui.GUIService;
import net.edhum.bukkit.api.player.Player;

import java.util.function.Consumer;

public class OpenGUItemAction implements Consumer<Player> {

    private final GUIService guiService;
    private final GUI menu;

    @AssistedInject
    public OpenGUItemAction(GUIService guiService, @Assisted GUI menu) {
        this.guiService = guiService;
        this.menu = menu;
    }

    @Override
    public void accept(Player player) {
        this.guiService.open(this.menu, player);
    }
}
