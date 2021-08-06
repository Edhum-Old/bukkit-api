package net.edhum.bukkit.api.gui;

import com.google.inject.Inject;
import net.edhum.bukkit.api.item.Item;
import net.edhum.bukkit.api.item.ItemService;
import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.MessageService;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class GUIServiceImpl implements GUIService {

    private final GUIRepository guiRepository;
    private final ItemService itemService;
    private final MessageService messageService;

    @Inject
    public GUIServiceImpl(GUIRepository guiRepository, ItemService itemService, MessageService messageService) {
        this.guiRepository = guiRepository;
        this.itemService = itemService;
        this.messageService = messageService;
    }

    @Override
    public void open(GUI gui, Player player) {
        Inventory inventory = this.build(gui, player);
        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(player.getUniqueId())).orElseThrow();
        bukkitPlayer.openInventory(inventory);

        this.guiRepository.add(player, gui);
    }

    @Override
    public void close(GUI gui, Player player) {
        gui.close(player);
        this.guiRepository.remove(player);
    }

    private Inventory build(GUI gui, Player player) {
        String name = this.messageService.get(gui.getName(), player);
        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(player.getUniqueId())).orElseThrow();

        Inventory inventory = Bukkit.createInventory(bukkitPlayer, gui.getType(), Component.text(name));

        for (int i = 0; i < gui.getContent().length; i++) {
            Optional<Item> optionalItem = gui.get(i);

            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                ItemStack itemStack = this.itemService.build(item, player);
                inventory.setItem(i, itemStack);
            }
        }

        return inventory;
    }
}
