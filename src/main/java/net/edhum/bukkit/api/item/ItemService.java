package net.edhum.bukkit.api.item;

import net.edhum.bukkit.api.player.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemService {

    ItemStack build(Item item, Player player);
}
