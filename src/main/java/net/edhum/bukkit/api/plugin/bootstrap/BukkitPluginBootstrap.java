package net.edhum.bukkit.api.plugin.bootstrap;

import com.google.inject.Injector;
import com.google.inject.Key;
import net.edhum.bukkit.api.plugin.EdhumPlugin;
import net.edhum.common.command.CommandPostExecutor;
import net.edhum.common.listener.ListenerPostExecutor;
import net.edhum.common.persistence.sql.SQLPostExecutor;
import net.edhum.common.persistence.sql.exception.SQLInitialisationException;
import net.edhum.common.plugin.bootstrap.PluginBootstrap;
import org.bukkit.event.Listener;

public class BukkitPluginBootstrap {

    public Injector bootstrap(EdhumPlugin plugin) throws SQLInitialisationException {
        Injector injector = new PluginBootstrap().bootstrap(plugin, new PluginModule(plugin));

        injector.getInstance(CommandPostExecutor.class).init();
        injector.getInstance(new Key<ListenerPostExecutor<Listener>>() {}).registerListeners();

        injector.getInstance(SQLPostExecutor.class).init();

        return injector;
    }
}
