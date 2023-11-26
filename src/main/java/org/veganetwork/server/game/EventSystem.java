package org.veganetwork.server.game;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import org.veganetwork.server.game.events.PlayerEventHandler;
import org.veganetwork.server.game.events.ServerEventHandler;

// Go to package `events` to make an event or edit if needed
// To Initialize new events use RegisterEvents
public class EventSystem {
    private final GlobalEventHandler gEventHandler;
    private final InstanceContainer iContainer;
    public EventSystem(GlobalEventHandler globalEventHandler, InstanceContainer instanceContainer) {
        this.gEventHandler = globalEventHandler;
        this.iContainer = instanceContainer;
    }
    public void RegisterEvents() {
        new PlayerEventHandler(gEventHandler, iContainer);
        new ServerEventHandler(gEventHandler, iContainer);
    }
}
