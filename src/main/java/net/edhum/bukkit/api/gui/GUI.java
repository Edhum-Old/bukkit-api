package net.edhum.bukkit.api.gui;

import net.edhum.bukkit.api.item.Item;
import net.edhum.common.message.Message;
import org.bukkit.event.inventory.InventoryType;

import java.util.Optional;

public interface GUI {

    Message getName();

    InventoryType getType();

    int getSize();

    Item[] getContent();

    void add(Item item, int slot);

    void add(Item item);

    Optional<Item> get(int slot);
}
