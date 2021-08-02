package net.edhum.bukkit.api.group;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Group {

    PLAYER(1, "player"),
    BUILDER(2, "builder"),
    DEVELOPER(3, "developer"),
    ADMINISTRATOR(4, "administrator");

    private final int id;
    private final String name;

    Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<Group> find(Predicate<Group> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findAny();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
