package net.edhum.bukkit.api.player.newcomer;

import net.edhum.bukkit.api.group.Group;
import net.edhum.common.i18n.Language;

public class UnavailablePlayerNewcomerConfiguration {

    private static final String DEFAULT_GROUP = Group.PLAYER.getName();
    private static final String DEFAULT_LANGUAGE = Language.FRENCH.getTag();
    private static final long DEFAULT_MONEY = 100L;

    public static PlayerNewcomerConfiguration getDefaultConfiguration() {
        PlayerNewcomerConfiguration configuration = new PlayerNewcomerConfiguration();

        configuration.setGroup(DEFAULT_GROUP);
        configuration.setLanguage(DEFAULT_LANGUAGE);
        configuration.setMoney(DEFAULT_MONEY);

        return configuration;
    }
}
