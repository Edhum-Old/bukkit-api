package net.edhum.bukkit.api.item;

import com.google.inject.Inject;
import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.Message;
import net.edhum.common.message.MessageService;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    private final MessageService messageService;

    @Inject
    public ItemServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public ItemStack build(Item item, Player player) {
        ItemStack itemStack = new ItemStack(item.getMaterial());

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(this.messageService.get(item.getName(), player)));

        Optional<Message> optionalLore = item.getLore();
        if (optionalLore.isPresent()) {
            Message lore = optionalLore.get();

            itemMeta.lore(Collections.singletonList(Component.text(this.messageService.get(lore, player))));
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
