package net.edhum.bukkit.api.console;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import net.edhum.common.console.Console;
import net.edhum.common.console.ConsoleLanguage;
import net.edhum.common.i18n.language.Language;
import net.edhum.common.i18n.language.map.LanguageMap;
import net.edhum.common.i18n.language.map.LanguageMapProvider;
import net.edhum.common.i18n.language.map.UnavailableLanguageMap;

import java.sql.SQLException;

public class ConsoleModule extends AbstractModule {

    private static final String CONSOLE_LANGUAGE_NAME = "english";

    @Override
    protected void configure() {
        bind(Console.class).to(BukkitConsole.class);
    }

    @Provides
    @ConsoleLanguage
    public Language provideConsoleLanguage(LanguageMapProvider languageMapProvider) {
        LanguageMap languageMap;

        try {
            languageMap = languageMapProvider.get();
        } catch (SQLException e) {
            e.printStackTrace();

            languageMap = UnavailableLanguageMap.getDefaultLanguageMap();
        }

        return languageMap.getLanguageByName(CONSOLE_LANGUAGE_NAME).orElse(UnavailableLanguageMap.ENGLISH);
    }
}
