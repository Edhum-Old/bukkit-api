package net.edhum.bukkit.api.command.registerer;

import com.google.inject.Inject;
import net.edhum.bukkit.api.command.adapter.CommandAdapterFactory;
import net.edhum.bukkit.api.command.registerer.map.CommandMapProvider;
import net.edhum.bukkit.api.command.registerer.map.UnavailableCommandMap;
import net.edhum.common.command.CommandTree;
import net.edhum.common.command.registerer.CommandRegisterer;
import net.edhum.common.plugin.annotations.PluginLogger;
import net.edhum.common.plugin.annotations.PluginName;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.util.logging.Logger;

public class BukkitCommandRegisterer implements CommandRegisterer {

    private final CommandMap commandMap;
    private final String pluginName;
    private final CommandAdapterFactory commandAdapterFactory;

    @Inject
    public BukkitCommandRegisterer(@PluginLogger Logger logger, CommandMapProvider commandMapProvider, @PluginName String pluginName, CommandAdapterFactory commandAdapterFactory) {
        CommandMap commandMap;

        try {
            commandMap = commandMapProvider.get();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            logger.warning("An exception occurred while attempting to get the command map. The default one will be used instead");

            commandMap = UnavailableCommandMap.getDefaultCommandMap();
        }

        this.commandMap = commandMap;

        this.pluginName = pluginName;
        this.commandAdapterFactory = commandAdapterFactory;
    }

    @Override
    public void registerCommand(CommandTree command) {
        this.commandMap.register(pluginName, commandAdapterFactory.createBukkitCommandAdapter(command));
    }

    @Override
    public void unregisterCommand(String commandName) {
        Command command = this.commandMap.getCommand(commandName);

        if (command == null) {
            throw new IllegalArgumentException(String.format("No command with name %s has been found", commandName));
        }

        command.unregister(this.commandMap);
    }
}
