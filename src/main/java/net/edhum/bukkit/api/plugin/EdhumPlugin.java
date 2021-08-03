package net.edhum.bukkit.api.plugin;

import com.google.inject.Injector;
import com.google.inject.Key;
import net.edhum.bukkit.api.plugin.bootstrap.BukkitPluginBootstrap;
import net.edhum.common.command.disabler.CommandDisabler;
import net.edhum.common.command.enabler.CommandEnabler;
import net.edhum.common.listener.ListenerPostExecutor;
import net.edhum.common.persistence.sql.SQLPostExecutor;
import net.edhum.common.persistence.sql.exception.SQLInitialisationException;
import net.edhum.common.plugin.ModularPlugin;
import net.edhum.common.plugin.annotations.PluginLogger;
import net.edhum.common.scheduler.Scheduler;
import net.edhum.common.shutdown.ShutdownHooks;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class EdhumPlugin extends JavaPlugin implements ModularPlugin {

    protected Injector injector;
    protected Logger logger;

    @Override
    public void onEnable() {
        try {
            BukkitPluginBootstrap bootstrap = new BukkitPluginBootstrap();
            injector = bootstrap.bootstrap(this);

            this.logger = injector.getInstance(new Key<>(PluginLogger.class) {
            });

            injector.getInstance(CommandEnabler.class).enableCommands();
            injector.getInstance(new Key<ListenerPostExecutor<Listener>>() {
            }).registerListeners();

            injector.getInstance(SQLPostExecutor.class).init();

            injector.getInstance(Scheduler.class).runTaskLater(() -> injector.getInstance(CommandDisabler.class).disableCommands(), 1); // Workaround to wait for the server to load completely (See https://www.spigotmc.org/threads/server-finish-load-up-event.331213/)

            this.logger.info("Plugin enabled successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
            BukkitPluginBootstrap bootstrap = new BukkitPluginBootstrap();
            injector = bootstrap.bootstrap(this);

            this.logger = injector.getInstance(new Key<>(PluginLogger.class) {
            });

            this.logger.info("Plugin enabled successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onDisable() {
        this.injector.getInstance(ShutdownHooks.class).closeAll();

        this.logger.info("Plugin disabled successfully");
    }
}
