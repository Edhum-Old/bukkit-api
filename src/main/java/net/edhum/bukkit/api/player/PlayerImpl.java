package net.edhum.bukkit.api.player;

import net.edhum.bukkit.api.group.Group;
import net.edhum.common.command.sender.AbstractPlayerCommandSender;
import net.edhum.common.i18n.Language;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PlayerImpl extends AbstractPlayerCommandSender implements Player {

    private final UUID uuid;
    private String name;
    private Group group;
    private long money;
    private final boolean isNewcomer;

    public PlayerImpl(UUID uuid,
                      String name,
                      Group group,
                      Language language,
                      long money,
                      boolean isNewcomer) {
        super(language);

        this.uuid = uuid;
        this.name = name;
        this.group = group;
        this.language = language;
        this.money = money;
        this.isNewcomer = isNewcomer;
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public boolean isNewcomer() {
        return this.isNewcomer;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public long getMoney() {
        return this.money;
    }

    @Override
    public boolean hasEnoughMoney(long amount) {
        return this.money >= amount;
    }

    @Override
    public void creditMoney(long amount) {
        this.money = Math.max(this.money + amount, 0);
    }

    @Override
    public void withdrawMoney(long amount) {
        this.money = Math.max(this.money - amount, 0);
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public void updatePermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public void sendMessage(Component component) {
        org.bukkit.entity.Player bukkitPlayer = Optional.ofNullable(Bukkit.getPlayer(this.uuid)).orElseThrow();
        bukkitPlayer.sendMessage(component);
    }
}
