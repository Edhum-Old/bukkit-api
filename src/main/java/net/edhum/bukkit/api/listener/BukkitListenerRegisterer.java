package net.edhum.bukkit.api.listener;

import com.google.inject.Inject;
import net.edhum.common.listener.ListenerRegisterer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitListenerRegisterer implements ListenerRegisterer<Listener> {

    private final Plugin plugin;
    private final PluginManager pluginManager;

    @Inject
    public BukkitListenerRegisterer(Plugin plugin, PluginManager pluginManager) {
        this.plugin = plugin;
        this.pluginManager = pluginManager;
    }

    @Override
    public void registerListener(Listener listener) {
        this.pluginManager.registerEvents(listener, this.plugin);
    }
}
