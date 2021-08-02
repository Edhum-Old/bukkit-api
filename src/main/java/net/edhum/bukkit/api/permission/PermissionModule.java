package net.edhum.bukkit.api.permission;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import net.edhum.bukkit.api.permission.group.GroupPermissionModule;

public class PermissionModule extends AbstractModule {

    private static final String PERMISSION_FOLDER = "permissions";

    @Override
    protected void configure() {
        install(new GroupPermissionModule());

        bind(PermissionService.class).to(PermissionServiceImpl.class);
    }

    @Provides
    @Named("PermissionFolder")
    public String providePermissionFolder() {
        return PERMISSION_FOLDER;
    }
}
