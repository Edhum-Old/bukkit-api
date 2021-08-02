package net.edhum.bukkit.api.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import net.edhum.common.configuration.ObjectMapper;
import net.edhum.common.plugin.annotations.PluginDataFolder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigurationModule extends AbstractModule {

    private static final String CONFIGURATION_FILE_NAME = "config.yml";

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));
    }

    @SuppressWarnings("BindingAnnotationWithoutInject")
    @CheckedProvides(ConfigurationProvider.class)
    public Configuration provideConfiguration(@PluginDataFolder Path dataFolder, ObjectMapper mapper) throws IOException {
        try (InputStream in = Files.newInputStream(dataFolder.resolve(CONFIGURATION_FILE_NAME))) {
            return mapper.load(Configuration.class, in);
        }
    }
}
