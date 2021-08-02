package net.edhum.bukkit.api.player;

import net.edhum.bukkit.api.group.Group;
import net.edhum.common.command.sender.CommandSender;
import net.edhum.common.i18n.Language;

import java.util.Collection;
import java.util.UUID;

public interface Player extends CommandSender {

    UUID getUniqueId();

    boolean isNewcomer();

    String getName();

    void setName(String name);

    Group getGroup();

    void setGroup(Group group);

    long getMoney();

    boolean hasEnoughMoney(long amount);

    void creditMoney(long amount);

    void withdrawMoney(long amount);

    void setLanguage(Language language);

    void updatePermissions(Collection<String> permissions);
}
