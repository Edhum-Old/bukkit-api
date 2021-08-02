package net.edhum.bukkit.api.player;

import com.google.inject.AbstractModule;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.bukkit.api.player.repository.PluginCachedPlayerRepository;

public class PlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlayerService.class).to(PlayerServiceImpl.class);

        bind(PlayerRepository.class).to(PluginCachedPlayerRepository.class).asEagerSingleton();
    }
}
