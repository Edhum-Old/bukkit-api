package net.edhum.bukkit.api.permission.group;

import com.google.inject.Inject;
import net.edhum.bukkit.api.group.Group;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMap;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMapProvider;
import net.edhum.bukkit.api.permission.group.map.UnavailableGroupPermissionMap;

import java.util.Collection;

public class GroupPermissionServiceImpl implements GroupPermissionService {

    private final GroupPermissionMap permissions;

    @Inject
    public GroupPermissionServiceImpl(GroupPermissionMapProvider permissionsProvider) {
        GroupPermissionMap permissions;

        try {
            permissions = permissionsProvider.get();
        } catch (Exception e) {
            e.printStackTrace();

            permissions = UnavailableGroupPermissionMap.getDefaultCityPermissionList();
        }

        this.permissions = permissions;
    }

    @Override
    public Collection<String> getPermissions(Group group) {
        return this.permissions.getPermissions(group);
    }
}
