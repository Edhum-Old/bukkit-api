package net.edhum.bukkit.api.item;

import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.Message;
import org.bukkit.Material;

import java.util.Optional;
import java.util.function.Consumer;

public class Item {

    private final Message name;
    private final Material material;
    private final Message lore;
    private final Consumer<Player> action;

    public Item(Message name, Material material, Message lore, Consumer<Player> action) {
        this.name = name;
        this.material = material;
        this.lore = lore;
        this.action = action;
    }

    public void click(Player player) {
        this.action.accept(player);
    }

    public Message getName() {
        return this.name;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Optional<Message> getLore() {
        return Optional.ofNullable(this.lore);
    }
}
