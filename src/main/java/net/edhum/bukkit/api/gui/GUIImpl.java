package net.edhum.bukkit.api.gui;

import net.edhum.bukkit.api.item.Item;
import net.edhum.common.message.Message;
import org.bukkit.event.inventory.InventoryType;

import java.util.Optional;

// TODO: 06/08/2021 Create a class which handles the gui content
public class GUIImpl implements GUI {

    private final Message name;
    private final InventoryType type;
    private final int size;
    private final Item[] content;

    public GUIImpl(Message name, InventoryType type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.content = new Item[type.getDefaultSize()];
    }

    @Override
    public Message getName() {
        return this.name;
    }

    @Override
    public InventoryType getType() {
        return this.type;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Item[] getContent() {
        return this.content;
    }

    @Override
    public void add(Item item, int slot) {
        this.content[slot] = item;
    }

    @Override
    public void add(Item item) {
        int slot = 0;

        while (slot < this.type.getDefaultSize() && this.content[slot] != null) {
            slot++;
        }

        if (slot >= this.type.getDefaultSize()) {
            throw new IllegalStateException("The GUI is full");
        }

        this.add(item, slot);
    }

    @Override
    public Optional<Item> get(int slot) {
        return Optional.ofNullable(this.content[slot]);
    }
}
