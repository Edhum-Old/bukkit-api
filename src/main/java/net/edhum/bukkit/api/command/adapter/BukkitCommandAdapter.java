package net.edhum.bukkit.api.command.adapter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.edhum.common.command.CommandTree;
import net.edhum.common.command.argument.exception.ArgumentException;
import net.edhum.common.command.dispatcher.CommandDispatcher;
import net.edhum.common.command.execution.exceptions.InvalidPermissionException;
import net.edhum.common.command.execution.exceptions.InvalidRequirementException;
import net.edhum.common.command.execution.exceptions.InvalidSyntaxException;
import net.edhum.common.command.execution.exceptions.UnknownNodeException;
import net.edhum.common.command.sender.CommandSenderProvider;
import net.edhum.common.command.sender.exceptions.UnsupportedSenderException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class BukkitCommandAdapter extends BukkitCommand {

    private final CommandSenderProvider<CommandSender> commandSenderProvider;
    private final CommandDispatcher commandDispatcher;

    private final CommandTree tree;

    @Inject
    public BukkitCommandAdapter(CommandSenderProvider<CommandSender> commandSenderProvider,
                                CommandDispatcher commandDispatcher,
                                @Assisted CommandTree tree) {
        super(tree.getRoot().getCommand().getName());

        this.commandSenderProvider = commandSenderProvider;
        this.commandDispatcher = commandDispatcher;

        this.tree = tree;
    }

    // TODO: 27/07/2021 Error messages
    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
        try {
            this.commandDispatcher.dispatchExecution(this.tree, this.commandSenderProvider.get(sender), args);
        } catch (UnknownNodeException e) {
            e.printStackTrace();
        } catch (InvalidPermissionException e) {
            e.printStackTrace();
        } catch (UnsupportedSenderException e) {
            e.printStackTrace();
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        } catch (ArgumentException e) {
            e.printStackTrace();
        } catch (InvalidRequirementException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Nonnull
    @Override
    public List<String> tabComplete(@Nonnull CommandSender sender, @Nonnull String alias, @Nonnull String[] args) throws IllegalArgumentException {
        try {
            return this.commandDispatcher.dispatchTabCompletion(this.tree, this.commandSenderProvider.get(sender), args);
        } catch (UnsupportedSenderException e) {
            e.printStackTrace();

            return Collections.emptyList();
        }
    }
}