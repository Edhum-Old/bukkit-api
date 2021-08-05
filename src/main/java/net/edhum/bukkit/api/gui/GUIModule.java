package net.edhum.bukkit.api.gui;

import com.google.inject.AbstractModule;

public class GUIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GUIManager.class).to(GUIManagerImpl.class);

        bind(GUIRepository.class).to(GUIRepositoryImpl.class).asEagerSingleton();
    }
}
