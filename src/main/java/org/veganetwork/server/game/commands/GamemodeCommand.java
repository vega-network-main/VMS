package org.veganetwork.server.game.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import org.veganetwork.server.game.utilitaires.PlayerUtility;

import static org.veganetwork.Main.logger;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");
        setDefaultExecutor(((sender, context) ->
                sender.sendMessage("Usage: /gamemode <type> <target>"))
        );

        var gamemodeType = ArgumentType.String("gm_type");
        var playerArgument = ArgumentType.String("player_id");

        addSyntax((sender, context) -> {
            String gamemode = context.get(gamemodeType);
            String playerName = context.get(playerArgument);

            Player player = PlayerUtility.findPlayer(playerName);
            if (player != null) {
                try {
                    GameMode gm = GameMode.valueOf(gamemode.toUpperCase());
                    player.setGameMode(gm);
                    sender.sendMessage(String.format("Player %s has changed his gamemode to %s", playerName, gamemode));
                    logger.info(String.format("Player %s executed command /gamemode", playerName));
                } catch (IllegalArgumentException iae) {
                    sender.sendMessage("Invalid game mode provided");
                }
            } else {
                sender.sendMessage(String.format("No for player %s was changed to %s", playerName, gamemode));
            }
        },gamemodeType, playerArgument);
    }
}
