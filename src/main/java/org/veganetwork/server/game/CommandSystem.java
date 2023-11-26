package org.veganetwork.server.game;

import net.minestom.server.MinecraftServer;
import org.veganetwork.server.game.commands.KillCommand;

public class CommandSystem {
    public CommandSystem () {
        MinecraftServer.getCommandManager().register(new KillCommand());
    }
}
