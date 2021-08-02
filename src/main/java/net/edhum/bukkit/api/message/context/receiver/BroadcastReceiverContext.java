package net.edhum.bukkit.api.message.context.receiver;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.player.Player;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.common.command.sender.CommandSender;
import net.edhum.common.message.context.receiver.ReceiverContext;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class BroadcastReceiverContext implements ReceiverContext {

    private final PlayerRepository playerRepository;

    private final Collection<UUID> blacklisted;

    @AssistedInject
    public BroadcastReceiverContext(PlayerRepository playerRepository,
                                    @Assisted Collection<UUID> blacklisted) {
        this.playerRepository = playerRepository;

        this.blacklisted = blacklisted;
    }

    @AssistedInject
    public BroadcastReceiverContext(PlayerRepository playerRepository,
                                    @Assisted UUID except) {
        this(playerRepository, Collections.singleton(except));
    }

    @AssistedInject
    public BroadcastReceiverContext(PlayerRepository playerRepository,
                                    @Assisted Player except) {
        this(playerRepository, Collections.singleton(except.getUniqueId()));
    }

    @AssistedInject
    public BroadcastReceiverContext(PlayerRepository playerRepository) {
        this(playerRepository, Collections.emptySet());
    }

    @Override
    public Collection<? extends CommandSender> getReceivers() {
        return this.playerRepository.findAll(player -> !this.blacklisted.contains(player.getUniqueId()));
    }
}
