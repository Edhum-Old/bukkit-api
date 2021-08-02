package net.edhum.bukkit.api.group.filter;

import net.edhum.bukkit.api.group.Group;

import java.util.function.Predicate;

public class GroupNameFilter implements Predicate<Group> {

    private final String name;

    public GroupNameFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Group group) {
        return group.getName().equals(this.name);
    }
}
