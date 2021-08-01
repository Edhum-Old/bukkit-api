package net.edhum.bukkit.api.command.sender;

import com.google.inject.Inject;
import net.edhum.common.command.sender.CommandSender;
import net.edhum.common.command.sender.CommandSenderProvider;
import net.edhum.common.command.sender.exceptions.UnsupportedSenderException;
import net.edhum.common.console.Console;
import net.edhum.common.player.repository.PlayerRepository;
import net.edhum.common.player.repository.filter.PlayerRepositoryFilterFactory;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitCommandSenderProvider implements CommandSenderProvider<org.bukkit.command.CommandSender> {

    private final PlayerRepository playerRepository;
    private final PlayerRepositoryFilterFactory playerRepositoryFilterFactory;
    private final Console console;

    @Inject
    public BukkitCommandSenderProvider(PlayerRepository playerRepository,
                                       PlayerRepositoryFilterFactory playerRepositoryFilterFactory,
                                       Console console) {
        this.playerRepository = playerRepository;
        this.playerRepositoryFilterFactory = playerRepositoryFilterFactory;
        this.console = console;
    }

    public CommandSender get(org.bukkit.command.CommandSender bukkitSender) throws UnsupportedSenderException {
        if (bukkitSender instanceof Player bukkitPlayer) {
            UUID uuid = bukkitPlayer.getUniqueId();
            return this.playerRepository.find(this.playerRepositoryFilterFactory.uuid(uuid)).orElseThrow();
        } else if (bukkitSender instanceof ConsoleCommandSender) {
            return this.console;
        } else {
            throw new UnsupportedSenderException();
        }
    }
}
