package org.veganetwork.server.game.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.veganetwork.server.game.utilitaires.PlayerUtility;

public class TeleportCommand extends Command {
    public TeleportCommand() {
        super("tp");
        setDefaultExecutor(((sender, context) ->
                sender.sendMessage("Usage: /tp <player>"))
        );

        var playerArgument = ArgumentType.String("where");

        addSyntax((sender, context) -> {
            String playerName = context.get(playerArgument);
            Player playerTarget = PlayerUtility.findPlayer(playerName);
            Player player = PlayerUtility.findPlayer(sender.toString());

            if (playerTarget != null && player != null) {
                player.teleport(playerTarget.getPosition());
            } else {
                sender.sendMessage(String.format("Player %s was not found.", playerName));
            }
        }, playerArgument);
    }
}
