package net.edhum.bukkit.api.permission.group.map;

import com.google.inject.throwingproviders.CheckedProvider;

public interface GroupPermissionMapProvider extends CheckedProvider<GroupPermissionMap> {

    @Override
    GroupPermissionMap get() throws Exception;
}
