package net.edhum.bukkit.api.command;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import net.edhum.bukkit.api.command.adapter.CommandAdapterFactory;
import net.edhum.bukkit.api.command.registerer.BukkitCommandRegisterer;
import net.edhum.bukkit.api.command.sender.BukkitCommandSenderProvider;
import net.edhum.common.command.registerer.CommandRegisterer;
import net.edhum.common.command.sender.CommandSenderProvider;
import org.bukkit.command.CommandSender;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(CommandAdapterFactory.class));

        bind(new TypeLiteral<CommandSenderProvider<CommandSender>>() {
        }).to(BukkitCommandSenderProvider.class);

        bind(CommandRegisterer.class).to(BukkitCommandRegisterer.class);
    }

    /*@CheckedProvides(CommandMapProvider.class)
    public CommandMap provideCommandMap(PluginManager pluginManager) throws NoSuchFieldException, IllegalAccessException {
        Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandMapField.get(pluginManager);
        commandMapField.setAccessible(false);

        return commandMap;
    }*/
}
