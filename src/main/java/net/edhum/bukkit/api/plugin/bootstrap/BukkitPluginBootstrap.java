package net.edhum.bukkit.api.plugin.bootstrap;

import com.google.inject.Injector;
import net.edhum.bukkit.api.plugin.EdhumPlugin;
import net.edhum.common.plugin.bootstrap.PluginBootstrap;

public class BukkitPluginBootstrap {

    public Injector bootstrap(EdhumPlugin plugin) {
        return new PluginBootstrap().bootstrap(plugin, new BukkitModule(plugin), new ServerModule());
    }
}
