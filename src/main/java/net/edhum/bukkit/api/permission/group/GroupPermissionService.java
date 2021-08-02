package net.edhum.bukkit.api.permission.group;

import net.edhum.bukkit.api.group.Group;

import java.util.Collection;

public interface GroupPermissionService {

    Collection<String> getPermissions(Group group);
}
