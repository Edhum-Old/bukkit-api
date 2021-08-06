package net.edhum.bukkit.api.gui;

import net.edhum.common.message.Message;
import org.bukkit.event.inventory.InventoryType;

public class GUIBuilder {

    private static final int ROW_SIZE = 9;
    private static final int MAXIMAL_SIZE = 54;
    private static final int MAXIMAL_ROWS = MAXIMAL_SIZE / ROW_SIZE;

    private Message title;
    private InventoryType type;
    private int rows;

    public GUIBuilder withTitle(Message title) {
        this.title = title;

        return this;
    }

    public GUIBuilder withType(InventoryType type) {
        this.type = type;

        if (type != InventoryType.CHEST && this.rows != 0) {
            this.rows = 0;
        }

        return this;
    }

    public GUIBuilder withRows(int rows) {
        if (rows < 0 || rows > MAXIMAL_ROWS) {
            throw new IllegalArgumentException(String.format("Rows must be a positive number smaller or equal to %d", MAXIMAL_ROWS));
        }

        this.rows = rows;

        if (this.type != null && this.type != InventoryType.CHEST) {
            this.type = null;
        }

        return this;
    }

    public GUI build() {
        if (this.title == null) {
            throw new IllegalStateException("A GUI must have a name");
        }

        if (this.type == null) {
            this.type = InventoryType.CHEST;
        }

        int size = (this.rows == 0)
                ? this.type.getDefaultSize()
                : this.rows * ROW_SIZE;

        return new GUIImpl(this.title, this.type, size);
    }
}
