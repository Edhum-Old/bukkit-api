package net.edhum.bukkit.api.plugin.bootstrap;

import com.google.inject.AbstractModule;
import net.edhum.bukkit.api.configuration.ConfigurationModule;
import net.edhum.bukkit.api.group.GroupModule;
import net.edhum.bukkit.api.gui.GUIModule;
import net.edhum.bukkit.api.handshake.HandshakeModule;
import net.edhum.bukkit.api.item.ItemModule;
import net.edhum.bukkit.api.permission.PermissionModule;
import net.edhum.bukkit.api.player.PlayerModule;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ConfigurationModule());
        install(new GroupModule());
        install(new GUIModule());
        install(new HandshakeModule());
        install(new ItemModule());
        install(new PermissionModule());
        install(new PlayerModule());
    }
}
