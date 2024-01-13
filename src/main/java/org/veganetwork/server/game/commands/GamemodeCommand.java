package org.veganetwork.server.game.commands;

import static java.lang.String.format;
import static net.kyori.adventure.text.Component.translatable;

import java.util.Arrays;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        var gamemodeArgument = ArgumentType.Word("gamemode").from(
            Arrays.stream(GameMode.values())
                .map(gameMode -> gameMode.name().toLowerCase())
                .toArray(String[]::new)
        );

        // TODO: 13.01.2024 Add callback if unknown gamemode (It doesn't work)

        var entityArgument = ArgumentType.Entity("target");

        setDefaultExecutor((sender, context) -> {
                sender.sendMessage("Usage: /gamemode <gamemode> [<target>]");
            }
        );

        addSyntax((sender, context) -> {
            String gamemode = context.get(gamemodeArgument);
            EntityFinder entityFinder = context.get(entityArgument);

            if (entityFinder.find(null, sender instanceof Player ? (Entity) sender : null).stream()
                .filter(e -> !e.getEntityType().equals(EntityType.PLAYER))
                .count() > 0)
            {
                sender.sendMessage(translatable("argument.player.entities").color(NamedTextColor.RED));
            } else {
                if (entityFinder.find(null, sender instanceof Player ? (Entity) sender : null).isEmpty()) {
                    sender.sendMessage(translatable("argument.entity.notfound.player").color(NamedTextColor.RED));
                } else {
                    entityFinder.find(null, sender instanceof Player ? (Entity) sender : null).forEach(entity -> {
                            GameMode gm = GameMode.valueOf(gamemode.toUpperCase());
                            ((Player) entity).setGameMode(gm);
                            sender.sendMessage(translatable("commands.gamemode.success.self").args(translatable(format("gameMode.%s", gamemode.toLowerCase()))));
                        });
                }
            }
        }, gamemodeArgument, entityArgument);

        addSyntax(((sender, context) -> {
            String gamemode = context.get(gamemodeArgument);
            if (sender instanceof Player) {
                GameMode gm = GameMode.valueOf(gamemode.toUpperCase());
                ((Player) sender).setGameMode(gm);
                sender.sendMessage(translatable("commands.gamemode.success.self").args(translatable(format("gameMode.%s", gamemode.toLowerCase()))));
            } else {
                sender.sendMessage(translatable("permissions.requires.player"));
            }
        }), gamemodeArgument);
    }
}
