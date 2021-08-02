package net.edhum.bukkit.api.permission;

import com.google.inject.Inject;
import net.edhum.bukkit.api.permission.group.GroupPermissionService;
import net.edhum.bukkit.api.player.Player;

import java.util.Collection;
import java.util.HashSet;

public class PermissionServiceImpl implements PermissionService {

    private final GroupPermissionService groupPermissionService;

    @Inject
    public PermissionServiceImpl(GroupPermissionService groupPermissionService) {
        this.groupPermissionService = groupPermissionService;
    }

    @Override
    public void updatePermissions(Player player) {
        Collection<String> permissions = new HashSet<>();

        permissions.addAll(this.groupPermissionService.getPermissions(player.getGroup()));

        player.updatePermissions(permissions);
    }
}
