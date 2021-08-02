package net.edhum.bukkit.api.plugin.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import net.edhum.bukkit.api.command.CommandModule;
import net.edhum.bukkit.api.console.ConsoleModule;
import net.edhum.bukkit.api.listener.ListenerModule;
import net.edhum.bukkit.api.message.MessageModule;
import net.edhum.bukkit.api.scheduler.SchedulerModule;
import net.edhum.common.plugin.annotations.PluginDataFolder;
import net.edhum.common.plugin.annotations.PluginLogger;
import net.edhum.common.plugin.annotations.PluginName;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.nio.file.Path;
import java.util.logging.Logger;

public class BukkitModule extends AbstractModule {

    private final Plugin plugin;

    public BukkitModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);
        bind(Server.class).toInstance(this.plugin.getServer());
        bind(PluginManager.class).toInstance(this.plugin.getServer().getPluginManager());
        bind(BukkitScheduler.class).toInstance(this.plugin.getServer().getScheduler());

        bind(CommandMap.class).toInstance(this.plugin.getServer().getCommandMap());

        install(new CommandModule());
        install(new ConsoleModule());
        install(new ListenerModule());
        install(new MessageModule());
        install(new SchedulerModule());
    }

    @Provides
    @PluginLogger
    public Logger providePluginLogger() {
        return this.plugin.getLogger();
    }

    @Provides
    @PluginDataFolder
    public Path providePluginDataFolder() {
        return this.plugin.getDataFolder().toPath();
    }

    @Provides
    @PluginName
    public String providePluginName() {
        return this.plugin.getName();
    }
}
