package net.edhum.bukkit.api.group;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import net.edhum.bukkit.api.group.filter.GroupFilterFactory;
import net.edhum.bukkit.api.group.filter.GroupIdFilter;
import net.edhum.bukkit.api.group.filter.GroupNameFilter;

import java.util.function.Predicate;

public class GroupModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(new TypeLiteral<Predicate<Group>>() {}, Names.named("id"), GroupIdFilter.class)
                .implement(new TypeLiteral<Predicate<Group>>() {}, Names.named("tag"), GroupNameFilter.class)
                .build(GroupFilterFactory.class));
    }
}
