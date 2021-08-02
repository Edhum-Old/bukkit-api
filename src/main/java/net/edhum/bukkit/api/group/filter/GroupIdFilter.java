package net.edhum.bukkit.api.group.filter;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import net.edhum.bukkit.api.group.Group;

import java.util.function.Predicate;

public class GroupIdFilter implements Predicate<Group> {

    private final int id;

    @AssistedInject
    public GroupIdFilter(@Assisted int id) {
        this.id = id;
    }

    @Override
    public boolean test(Group group) {
        return group.getId() == this.id;
    }
}
