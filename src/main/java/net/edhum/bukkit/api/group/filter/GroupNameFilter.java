package net.edhum.bukkit.api.group.filter;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.group.Group;

import java.util.function.Predicate;

public class GroupNameFilter implements Predicate<Group> {

    private final String name;

    @AssistedInject
    public GroupNameFilter(@Assisted String name) {
        this.name = name;
    }

    @Override
    public boolean test(Group group) {
        return group.getName().equals(this.name);
    }
}
