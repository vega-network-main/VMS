package org.veganetwork.server.game;

import net.minestom.server.MinecraftServer;
import net.minestom.server.listener.manager.PacketListenerManager;

public class PacketListenerSystem {
    public static PacketListenerManager getPacketListener() {
        return MinecraftServer.getPacketListenerManager();
    };
}
