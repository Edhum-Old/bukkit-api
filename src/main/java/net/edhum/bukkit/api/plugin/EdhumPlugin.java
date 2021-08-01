package net.edhum.bukkit.api.plugin;

import com.google.inject.Injector;
import com.google.inject.Key;
import net.edhum.bukkit.api.plugin.bootstrap.BukkitPluginBootstrap;
import net.edhum.common.plugin.ModularPlugin;
import net.edhum.common.plugin.annotations.PluginLogger;
import net.edhum.common.shutdown.ShutdownHooks;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class EdhumPlugin extends JavaPlugin implements ModularPlugin {

    protected Injector injector;

    @Override
    public void onEnable() {
        try {
            BukkitPluginBootstrap bootstrap = new BukkitPluginBootstrap();
            injector = bootstrap.bootstrap(this);

            injector.getInstance(new Key<Logger>(PluginLogger.class) {}).info("Plugin enabled successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        this.injector.getInstance(ShutdownHooks.class).closeAll();

        injector.getInstance(new Key<Logger>(PluginLogger.class) {}).info("Plugin disabled successfully");
    }
}
