package net.edhum.bukkit.api.listener;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import net.edhum.common.listener.ListenerRegisterer;
import org.bukkit.event.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ListenerRegisterer<Listener>>() {
        }).to(BukkitListenerRegisterer.class);

        Multibinder.newSetBinder(binder(), Listener.class); // Empty binder
    }
}
