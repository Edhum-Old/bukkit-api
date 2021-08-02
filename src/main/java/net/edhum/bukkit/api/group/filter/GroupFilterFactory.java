package net.edhum.bukkit.api.group.filter;

import net.edhum.bukkit.api.group.Group;

import javax.inject.Named;
import java.util.function.Predicate;

public interface GroupFilterFactory {

    @Named("id")
    Predicate<Group> id(int id);

    @Named("name")
    Predicate<Group> name(String name);
}
