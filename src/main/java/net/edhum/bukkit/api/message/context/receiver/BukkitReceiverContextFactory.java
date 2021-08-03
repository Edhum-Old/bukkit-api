package net.edhum.bukkit.api.message.context.receiver;

import net.edhum.bukkit.api.player.Player;
import net.edhum.common.message.context.receiver.ReceiverContext;
import net.edhum.common.message.context.receiver.ReceiverContextFactory;

import javax.inject.Named;
import java.util.Collection;
import java.util.UUID;

public interface BukkitReceiverContextFactory {

    @Named("broadcast")
    ReceiverContext broadcast(Collection<UUID> excepts);

    @Named("broadcast")
    ReceiverContext broadcast(UUID except);

    @Named("broadcast")
    ReceiverContext broadcast(Player except);

    @Named("broadcast")
    ReceiverContext broadcast();
}
