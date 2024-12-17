package org.veganetwork.server.game.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.veganetwork.server.game.utilitaires.PlayerUtility;

public class KillCommand extends Command {
    public KillCommand() {
        super("kill");
        setDefaultExecutor(((sender, context) ->
                sender.sendMessage("Usage: /kill <player_name>"))
        );
        ArgumentString playerArgument = ArgumentType.String("player_id");

        addSyntax((sender, context) -> {
            String playerName = context.get(playerArgument);
            Player player = PlayerUtility.findPlayer(playerName);
            if (player != null) {
                player.kill();
                sender.sendMessage(String.format("Player %s was killed", playerName));
            } else {
                sender.sendMessage(String.format("Player %s was not found", playerName));
            }
        }, playerArgument);
    }
}
