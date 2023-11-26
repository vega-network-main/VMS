package org.veganetwork.server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import org.slf4j.Logger;
import org.veganetwork.server.game.CommandSystem;
import org.veganetwork.server.game.EventSystem;

import static org.veganetwork.configs.ConfigServer.*;

public class ServerStarter {
    private final Logger logger;
    public ServerStarter(Logger logger){
        this.logger = logger;
    }
    public void ServerStart(){
        MinecraftServer server = MinecraftServer.init();
        MinecraftServer.setTerminalEnabled(true);
        MinecraftServer.setBrandName(brand_name);
        MinecraftServer.setCompressionThreshold(network_compression);
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        // Init Instance and Events
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        instanceContainer.setGenerator(unit ->
                unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        // Event System
        EventSystem eventSystem = new EventSystem(globalEventHandler, instanceContainer);
        eventSystem.RegisterEvents();

        new CommandSystem();

        server.start(server_ip, server_port);

        logger.info(brand_name + " has started, IP: " +server_ip+ ", port: " +server_port);
    }
}
