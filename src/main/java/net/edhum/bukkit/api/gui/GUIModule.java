package net.edhum.bukkit.api.gui;

import com.google.inject.AbstractModule;
import net.edhum.bukkit.api.gui.repository.GUIRepository;
import net.edhum.bukkit.api.gui.repository.GUIRepositoryImpl;

public class GUIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GUIService.class).to(GUIServiceImpl.class);

        bind(GUIRepository.class).to(GUIRepositoryImpl.class).asEagerSingleton();
    }
}
