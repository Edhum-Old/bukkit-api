package net.edhum.bukkit.api.player;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.bukkit.api.player.repository.PluginCachedPlayerRepository;
import net.edhum.bukkit.api.player.repository.filter.PlayerFilterFactory;
import net.edhum.bukkit.api.player.repository.filter.PlayerNameFilter;
import net.edhum.bukkit.api.player.repository.filter.PlayerUUIDFilter;

import java.util.function.Predicate;

public class PlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(new TypeLiteral<Predicate<Player>>() {}, Names.named("uuid"), PlayerUUIDFilter.class)
                .implement(new TypeLiteral<Predicate<Player>>() {}, Names.named("name"), PlayerNameFilter.class)
                .build(PlayerFilterFactory.class));

        bind(PlayerService.class).to(PlayerServiceImpl.class);

        bind(PlayerRepository.class).to(PluginCachedPlayerRepository.class).asEagerSingleton();
    }
}
