package net.edhum.bukkit.api.command.sender;

import com.google.inject.Inject;
import net.edhum.common.command.sender.AbstractConsoleCommandSender;
import net.edhum.common.command.sender.ConsoleCommandSender;
import net.edhum.common.console.ConsoleLanguage;
import net.edhum.common.i18n.Language;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class BukkitConsoleCommandSender extends AbstractConsoleCommandSender implements ConsoleCommandSender {

    @Inject
    public BukkitConsoleCommandSender(@ConsoleLanguage Language language) {
        super(language);
    }

    @Override
    public void sendMessage(Component component) {
        Bukkit.getConsoleSender().sendMessage(component);
    }
}
