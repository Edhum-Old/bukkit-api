package net.edhum.bukkit.api.gui.item;

import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
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

    public ItemStack build(Player player) {
        ItemStack itemStack = new ItemStack(this.material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(this.name.get(player.getLanguage())));

        if (this.lore != null) {
            itemMeta.lore(Collections.singletonList(Component.text(this.lore.get(player.getLanguage()))));
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
