package net.edhum.bukkit.api.permission.group;

import com.google.inject.Inject;
import net.edhum.bukkit.api.group.Group;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMap;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMapProvider;
import net.edhum.bukkit.api.permission.group.map.UnavailableGroupPermissionMap;
import net.edhum.common.plugin.annotations.PluginLogger;

import java.util.Collection;
import java.util.logging.Logger;

public class GroupPermissionServiceImpl implements GroupPermissionService {

    private final GroupPermissionMap permissions;

    @Inject
    public GroupPermissionServiceImpl(@PluginLogger Logger logger, GroupPermissionMapProvider permissionsProvider) {
        GroupPermissionMap permissions;

        try {
            permissions = permissionsProvider.get();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("An exception has occurred while trying to read the group permissions file." + " " +
                    "The default group permissions will be used.");

            permissions = UnavailableGroupPermissionMap.getDefaultCityPermissionList();
        }

        this.permissions = permissions;
    }

    @Override
    public Collection<String> getPermissions(Group group) {
        return this.permissions.getPermissions(group);
    }
}
