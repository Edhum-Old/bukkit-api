package net.edhum.bukkit.api.console;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import net.edhum.common.console.ConsoleLanguage;
import net.edhum.common.i18n.Language;

public class ConsoleModule extends AbstractModule {

    private static final Language CONSOLE_LANGUAGE = Language.ENGLISH;

    @Provides
    @ConsoleLanguage
    public Language provideConsoleLanguage() {
        return CONSOLE_LANGUAGE;
    }
}
