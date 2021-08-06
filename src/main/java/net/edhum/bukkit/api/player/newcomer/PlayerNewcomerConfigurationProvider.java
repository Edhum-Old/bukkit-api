package net.edhum.bukkit.api.player.newcomer;

import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

public interface PlayerNewcomerConfigurationProvider extends CheckedProvider<PlayerNewcomerConfiguration> {

    @Override
    PlayerNewcomerConfiguration get() throws IOException;
}
