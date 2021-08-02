package net.edhum.bukkit.api.command.sender;

import com.google.inject.Inject;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.bukkit.api.player.repository.filter.PlayerFilterFactory;
import net.edhum.common.command.sender.CommandSender;
import net.edhum.common.command.sender.CommandSenderProvider;
import net.edhum.common.command.sender.ConsoleCommandSender;
import net.edhum.common.command.sender.exceptions.UnsupportedSenderException;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitCommandSenderProvider implements CommandSenderProvider<org.bukkit.command.CommandSender> {

    private final PlayerRepository playerRepository;
    private final PlayerFilterFactory playerFilterFactory;
    private final ConsoleCommandSender console;

    @Inject
    public BukkitCommandSenderProvider(PlayerRepository playerRepository,
                                       PlayerFilterFactory playerFilterFactory,
                                       ConsoleCommandSender console) {
        this.playerRepository = playerRepository;
        this.playerFilterFactory = playerFilterFactory;
        this.console = console;
    }

    public CommandSender get(org.bukkit.command.CommandSender bukkitSender) throws UnsupportedSenderException {
        if (bukkitSender instanceof Player bukkitPlayer) {
            UUID uuid = bukkitPlayer.getUniqueId();
            return this.playerRepository.find(this.playerFilterFactory.uuid(uuid)).orElseThrow();
        } else if (bukkitSender instanceof org.bukkit.command.ConsoleCommandSender) {
            return this.console;
        } else {
            throw new UnsupportedSenderException();
        }
    }
}
