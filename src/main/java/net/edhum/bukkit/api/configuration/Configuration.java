package net.edhum.bukkit.api.configuration;

import net.edhum.bukkit.api.player.newcomer.PlayerNewcomerConfiguration;

public class Configuration {

    private PlayerNewcomerConfiguration newcomerConfiguration;

    public PlayerNewcomerConfiguration getNewcomerConfiguration() {
        return newcomerConfiguration;
    }

    public void setNewcomerConfiguration(PlayerNewcomerConfiguration newcomerConfiguration) {
        this.newcomerConfiguration = newcomerConfiguration;
    }
}
