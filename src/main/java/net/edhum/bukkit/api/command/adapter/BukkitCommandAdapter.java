package net.edhum.bukkit.api.command.adapter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.edhum.common.command.CommandNode;
import net.edhum.common.command.CommandTree;
import net.edhum.common.command.argument.exception.ArgumentException;
import net.edhum.common.command.dispatcher.CommandDispatcher;
import net.edhum.common.command.execution.exceptions.InvalidPermissionException;
import net.edhum.common.command.execution.exceptions.InvalidRequirementException;
import net.edhum.common.command.execution.exceptions.InvalidSyntaxException;
import net.edhum.common.command.execution.exceptions.UnknownNodeException;
import net.edhum.common.command.sender.CommandSenderProvider;
import net.edhum.common.command.sender.exceptions.UnsupportedSenderException;
import net.edhum.common.message.Message;
import net.edhum.common.message.MessageBuilder;
import net.edhum.common.message.MessageService;
import net.edhum.common.message.context.receiver.ReceiverContextFactory;
import net.edhum.common.message.context.writer.WriterContextFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class BukkitCommandAdapter extends BukkitCommand {

    private final CommandSenderProvider<CommandSender> commandSenderProvider;
    private final CommandDispatcher commandDispatcher;
    private final Messages messages;

    private final CommandTree tree;

    @Inject
    public BukkitCommandAdapter(CommandSenderProvider<CommandSender> commandSenderProvider,
                                CommandDispatcher commandDispatcher,
                                Messages messages,
                                @Assisted CommandTree tree) {
        super(tree.getRoot().getCommand().getName());

        this.commandSenderProvider = commandSenderProvider;
        this.commandDispatcher = commandDispatcher;
        this.messages = messages;

        this.tree = tree;
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
        net.edhum.common.command.sender.CommandSender edhumSender;

        try {
            edhumSender = this.commandSenderProvider.get(sender);
        } catch (UnsupportedSenderException e) {
            return true;
        }

        try {
            this.commandDispatcher.dispatchExecution(this.tree, edhumSender, args);
        } catch (UnknownNodeException e) {
            this.messages.unknownNode(edhumSender, e.getNode());
        } catch (InvalidPermissionException e) {
            this.messages.invalidPermission(edhumSender);
        } catch (InvalidSyntaxException e) {
            this.messages.invalidSyntax(edhumSender, e.getNode());
        } catch (ArgumentException e) {
            this.messages.invalidArgument(edhumSender, e.getError());
        } catch (InvalidRequirementException e) {
            this.messages.invalidRequirement(edhumSender, e.getError());
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

    private static class Messages {

        private final MessageService messageService;
        private final ReceiverContextFactory receiverContextFactory;
        private final WriterContextFactory writerContextFactory;

        @Inject
        public Messages(MessageService messageService, ReceiverContextFactory receiverContextFactory, WriterContextFactory writerContextFactory) {
            this.messageService = messageService;
            this.receiverContextFactory = receiverContextFactory;
            this.writerContextFactory = writerContextFactory;
        }

        public void unknownNode(net.edhum.common.command.sender.CommandSender sender, CommandNode node) {
            this.messageService.write(
                    new MessageBuilder()
                            .withPath("command.unknown_node")
                            .withArgument("node", node)
                            .build(),
                    this.receiverContextFactory.single(sender), this.writerContextFactory.chat());
        }

        public void invalidPermission(net.edhum.common.command.sender.CommandSender sender) {
            this.messageService.write(
                    new MessageBuilder()
                            .withPath("command.invalid_permissions")
                            .build(),
                    this.receiverContextFactory.single(sender), this.writerContextFactory.chat());
        }

        public void invalidSyntax(net.edhum.common.command.sender.CommandSender sender, CommandNode node) {
            this.messageService.write(
                    new MessageBuilder()
                            .withPath("command.invalid_syntax")
                            .withArgument("node", node)
                            .build(),
                    this.receiverContextFactory.single(sender), this.writerContextFactory.chat());
        }

        public void invalidArgument(net.edhum.common.command.sender.CommandSender sender, Message error) {
            this.messageService.write(error, this.receiverContextFactory.single(sender), this.writerContextFactory.chat());
        }

        public void invalidRequirement(net.edhum.common.command.sender.CommandSender sender, Message error) {
            this.messageService.write(error, this.receiverContextFactory.single(sender), this.writerContextFactory.chat());
        }
    }
}
