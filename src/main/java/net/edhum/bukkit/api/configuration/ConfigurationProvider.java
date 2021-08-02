package net.edhum.bukkit.api.configuration;

import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

public interface ConfigurationProvider extends CheckedProvider<Configuration> {

    @Override
    Configuration get() throws IOException;
}
