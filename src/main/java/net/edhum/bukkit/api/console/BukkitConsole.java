package net.edhum.bukkit.api.console;

import com.google.inject.Inject;
import net.edhum.common.console.AbstractConsole;
import net.edhum.common.console.ConsoleLanguage;
import net.edhum.common.i18n.language.Language;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class BukkitConsole extends AbstractConsole {

    @Inject
    public BukkitConsole(@ConsoleLanguage Language language) {
        super(language);
    }

    @Override
    public void sendMessage(Component component) {
        Bukkit.getConsoleSender().sendMessage(Identity.nil(), component);
    }
}
