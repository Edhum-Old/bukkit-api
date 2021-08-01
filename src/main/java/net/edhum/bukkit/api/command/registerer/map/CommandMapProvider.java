package net.edhum.bukkit.api.command.registerer.map;

import com.google.inject.throwingproviders.CheckedProvider;
import org.bukkit.command.CommandMap;

public interface CommandMapProvider extends CheckedProvider<CommandMap> {

    @Override
    CommandMap get() throws NoSuchFieldException, IllegalAccessException;
}
