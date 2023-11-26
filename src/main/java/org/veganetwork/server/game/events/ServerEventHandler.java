package org.veganetwork.server.game.events;

import net.kyori.adventure.text.Component;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.ping.ResponseData;

import static org.veganetwork.configs.ConfigServer.max_players;
import static org.veganetwork.configs.ConfigServer.motd;

public class ServerEventHandler {
    public ServerEventHandler(GlobalEventHandler gEventHandler, InstanceContainer iContainer) {
        gEventHandler.addListener(ServerListPingEvent.class, event -> {
            ResponseData responseData = event.getResponseData();
            responseData.setMaxPlayer(max_players);
            responseData.setDescription(Component.text(motd));
        });
    }
}
