package net.edhum.bukkit.api.message;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import net.edhum.bukkit.api.message.context.receiver.BroadcastReceiverContext;
import net.edhum.bukkit.api.message.context.receiver.BukkitReceiverContextFactory;
import net.edhum.common.message.context.receiver.ReceiverContext;

public class MessageModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(ReceiverContext.class, Names.named("broadcast"), BroadcastReceiverContext.class)
                .build(BukkitReceiverContextFactory.class));
    }
}
