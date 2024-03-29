package org.veganetwork.server.game;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.instance.InstanceContainer;
import org.veganetwork.server.game.commands.GamemodeCommand;
import org.veganetwork.server.game.commands.KillCommand;
import org.veganetwork.server.game.commands.PlayerMsgCommand;
import org.veganetwork.server.game.commands.TeleportCommand;
import org.veganetwork.server.game.utilitaires.PlayerUtility;

public class CommandSystem {
    public CommandSystem (InstanceContainer iContainer) {
        CommandManager registerCommand = MinecraftServer.getCommandManager();
        new PlayerUtility(iContainer);
        registerCommand.register(new KillCommand());
        registerCommand.register(new GamemodeCommand());
        registerCommand.register(new PlayerMsgCommand());
        registerCommand.register(new TeleportCommand());
    }
}
