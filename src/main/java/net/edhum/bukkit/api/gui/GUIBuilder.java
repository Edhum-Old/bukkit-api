package net.edhum.bukkit.api.gui;

import net.edhum.common.message.Message;
import org.bukkit.event.inventory.InventoryType;

public class GUIBuilder {

    private Message title;
    private InventoryType type;

    public GUIBuilder withTitle(Message title) {
        this.title = title;

        return this;
    }

    public GUIBuilder withType(InventoryType type) {
        this.type = type;

        return this;
    }

    public GUI build() {
        if (this.title == null) {
            throw new IllegalStateException("A GUI must have a name");
        }

        if (this.type == null) {
            this.type = InventoryType.CHEST;
        }

        return new GUI(this.title, this.type);
    }
}
