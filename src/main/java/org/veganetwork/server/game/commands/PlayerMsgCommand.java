package org.veganetwork.server.game.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.veganetwork.server.game.utilitaires.PlayerUtility;


public class PlayerMsgCommand extends Command {
    public PlayerMsgCommand() {
        super("tell", "msg");
        setDefaultExecutor(((sender, context) ->
                sender.sendMessage("Usage: /msg <player_name> <message>"))
        );

        var playerArgument = ArgumentType.String("targets");
        var playerMessage = ArgumentType.String("message");

        addSyntax((sender, context) -> {
            String playerName = context.get(playerArgument);
            String playerMsg = context.get(playerMessage);
            Player player = PlayerUtility.findPlayer(playerName);

            if (player != null) {
                player.sendMessage(sender, Component.text(playerMsg));
                sender.sendMessage(String.format("You sent a message to %s.", playerName));
            } else {
                sender.sendMessage(String.format("Player %s was not found.", playerName));
            }
        }, playerArgument, playerMessage);
    }
}
