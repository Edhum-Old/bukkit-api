package net.edhum.bukkit.api.handshake;

import com.google.inject.AbstractModule;

public class HandshakeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HandshakeHandler.class).to(HandshakeHandlerImpl.class);
    }
}
