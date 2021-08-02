package net.edhum.bukkit.api.player.persistence;

public record PlayerBean(String uuid,
                         String name,
                         int groupId,
                         int languageId,
                         long money) {
}
