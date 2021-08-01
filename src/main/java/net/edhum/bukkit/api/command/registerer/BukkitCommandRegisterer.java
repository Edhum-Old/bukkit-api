package net.edhum.bukkit.api.command.registerer;

import com.google.inject.Inject;
import net.edhum.bukkit.api.command.adapter.CommandAdapterFactory;
import net.edhum.common.command.CommandTree;
import net.edhum.common.command.registerer.CommandRegisterer;
import net.edhum.common.plugin.annotations.PluginName;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class BukkitCommandRegisterer implements CommandRegisterer {

    private final CommandMap commandMap;
    private final String pluginName;
    private final CommandAdapterFactory commandAdapterFactory;

    @Inject
    public BukkitCommandRegisterer(CommandMap commandMap, @PluginName String pluginName, CommandAdapterFactory commandAdapterFactory) {
        this.commandMap = commandMap;

        this.pluginName = pluginName;
        this.commandAdapterFactory = commandAdapterFactory;
    }

    @Override
    public void registerCommand(CommandTree command) {
        this.commandMap.register(pluginName, commandAdapterFactory.createBukkitCommandAdapter(command));
    }

    @Override
    public void unregisterCommand(String name) {
        Command command = this.commandMap.getCommand(name);

        if (command == null) {
            throw new IllegalArgumentException(String.format("No command with name %s has been found", name));
        }

        command.unregister(this.commandMap);
    }
}
