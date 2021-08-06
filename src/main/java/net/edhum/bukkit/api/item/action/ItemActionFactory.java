package net.edhum.bukkit.api.item.action;

import com.google.inject.name.Named;
import net.edhum.bukkit.api.gui.GUIImpl;
import net.edhum.bukkit.api.player.Player;

import java.util.function.Consumer;

public interface ItemActionFactory {

    @Named("link")
    Consumer<Player> link(GUIImpl gui);
}
