package net.edhum.bukkit.api.gui.item;

import net.edhum.bukkit.api.gui.GUI;
import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.Message;
import org.bukkit.Material;

import java.util.function.Consumer;

public class ItemBuilder {

    private Message name;
    private Material material;
    private Message lore;
    private Consumer<Player> action;

    public ItemBuilder() {
        this.action = player -> {};
    }

    public ItemBuilder withName(Message name) {
        this.name = name;

        return this;
    }

    public ItemBuilder withMaterial(Material material) {
        this.material = material;

        return this;
    }

    public ItemBuilder withLore(Message lore) {
        this.lore = lore;

        return this;
    }

    public ItemBuilder link(GUI link) {
        this.action = this.action.andThen(link::open);

        return this;
    }

    public Item build() {
        if (this.name == null) {
            throw new IllegalStateException("An item must have a name");
        }

        if (this.material == null) {
            throw new IllegalStateException("An item must be associated to a material");
        }

        return new Item(this.name, this.material, this.lore, this.action);
    }
}
