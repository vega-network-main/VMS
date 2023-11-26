package org.veganetwork.server.game.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;

public class KillCommand extends Command {
    public KillCommand() {
        super("kill");
        setDefaultExecutor(((sender, context) ->
                sender.sendMessage("Usage: /kill <player_name>"))
        );
        var playerArgument = ArgumentType.String("player_id");
        addSyntax((sender, context) ->
                sender.sendMessage("You tried to kill player " + context.get(playerArgument)), playerArgument
        );

    }
}
