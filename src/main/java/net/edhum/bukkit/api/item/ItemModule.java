package net.edhum.bukkit.api.item;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import net.edhum.bukkit.api.item.action.ItemActionFactory;
import net.edhum.bukkit.api.item.action.OpenGUItemAction;
import net.edhum.bukkit.api.player.Player;

import java.util.function.Consumer;

public class ItemModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(new TypeLiteral<Consumer<Player>>() {}, Names.named("link"), OpenGUItemAction.class)
                .build(ItemActionFactory.class));

        bind(ItemService.class).to(ItemServiceImpl.class);
    }
}
