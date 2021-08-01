package net.edhum.bukkit.api.command.adapter;

import net.edhum.common.command.CommandTree;

public interface CommandAdapterFactory {

    BukkitCommandAdapter createBukkitCommandAdapter(CommandTree command);
}
