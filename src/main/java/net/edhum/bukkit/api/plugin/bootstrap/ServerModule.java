package net.edhum.bukkit.api.plugin.bootstrap;

import com.google.inject.AbstractModule;
import net.edhum.bukkit.api.group.GroupModule;
import net.edhum.bukkit.api.handshake.HandshakeModule;
import net.edhum.bukkit.api.permission.PermissionModule;
import net.edhum.bukkit.api.player.PlayerModule;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new GroupModule());
        install(new HandshakeModule());
        install(new PermissionModule());
        install(new PlayerModule());
    }
}
