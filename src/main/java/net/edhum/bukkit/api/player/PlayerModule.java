package net.edhum.bukkit.api.player;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import net.edhum.bukkit.api.configuration.ConfigurationProvider;
import net.edhum.bukkit.api.player.newcomer.PlayerNewcomerConfiguration;
import net.edhum.bukkit.api.player.newcomer.PlayerNewcomerConfigurationProvider;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.bukkit.api.player.repository.PluginCachedPlayerRepository;
import net.edhum.bukkit.api.player.repository.filter.PlayerFilterFactory;
import net.edhum.bukkit.api.player.repository.filter.PlayerNameFilter;
import net.edhum.bukkit.api.player.repository.filter.PlayerUUIDFilter;

import java.io.IOException;
import java.util.function.Predicate;

public class PlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        install(new FactoryModuleBuilder()
                .implement(new TypeLiteral<Predicate<Player>>() {}, Names.named("uuid"), PlayerUUIDFilter.class)
                .implement(new TypeLiteral<Predicate<Player>>() {}, Names.named("name"), PlayerNameFilter.class)
                .build(PlayerFilterFactory.class));

        bind(PlayerService.class).to(PlayerServiceImpl.class);

        bind(PlayerRepository.class).to(PluginCachedPlayerRepository.class).asEagerSingleton();
    }

    @CheckedProvides(PlayerNewcomerConfigurationProvider.class)
    public PlayerNewcomerConfiguration providePlayerNewcomerConfiguration(ConfigurationProvider configurationProvider) throws IOException {
        return configurationProvider.get().getNewcomerConfiguration();
    }
}
