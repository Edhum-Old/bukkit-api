package net.edhum.bukkit.api.permission.group.map;

import net.edhum.bukkit.api.group.Group;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class GroupPermissionMap {

    private final Map<Group, Collection<String>> permissions;

    public GroupPermissionMap(Map<Group, Collection<String>> permissions) {
        this.permissions = permissions;
    }

    public GroupPermissionMap() {
        this.permissions = Collections.emptyMap();
    }

    public Collection<String> getPermissions(Group group) {
        if (!this.permissions.containsKey(group)) {
            throw new IllegalArgumentException("Illegal group");
        }

        return this.permissions.get(group);
    }
}
