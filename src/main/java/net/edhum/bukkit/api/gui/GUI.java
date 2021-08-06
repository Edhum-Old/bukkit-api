package net.edhum.bukkit.api.gui;

import net.edhum.bukkit.api.gui.item.Item;
import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

// TODO: 06/08/2021 Create a class which handles the gui content
public class GUI {

    private final Message name;
    private final InventoryType type;
    private final Item[] content;

    public GUI(Message name, InventoryType type) {
        this.name = name;
        this.type = type;
        this.content = new Item[type.getDefaultSize()];
    }

    public void open(Player player) {
        Inventory inventory = this.build(player);

        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(player.getUniqueId())).orElseThrow();
        bukkitPlayer.openInventory(inventory);
    }

    public void close(Player player) {
        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(player.getUniqueId())).orElseThrow();
        bukkitPlayer.closeInventory();
    }

    private Inventory build(Player player) {
        String name = this.name.get(player.getLanguage());
        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(player.getUniqueId())).orElseThrow();

        Inventory inventory = Bukkit.createInventory(bukkitPlayer, this.type, Component.text(name));

        for (int i = 0; i < content.length; i++) {
            Optional<Item> optionalItem = this.get(i);

            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                inventory.setItem(i, item.build(player));
            }
        }

        return inventory;
    }

    public void add(Item item, int slot) {
        this.content[slot] = item;
    }

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
    
    public Optional<Item> get(int slot) {
        return Optional.ofNullable(this.content[slot]);
    }
}
