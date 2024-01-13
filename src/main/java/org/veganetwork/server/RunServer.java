package org.veganetwork.server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import org.veganetwork.server.game.CommandSystem;
import org.veganetwork.server.game.EventSystem;
import org.veganetwork.server.game.utilitaires.GlobalInstance;
import org.veganetwork.server.game.utilitaires.MineTerminal;

import static org.veganetwork.configs.ConfigServer.*;

public class RunServer {
    public RunServer(){
    }
    public void ServerStart(){
        MinecraftServer server = MinecraftServer.init();
        // System.setProperty("minestom.terminal.disabled", "");
        System.setProperty("minestom.tps", String.valueOf(20));

        MinecraftServer.setBrandName(brand_name);
        MinecraftServer.setCompressionThreshold(network_compression);
        MinecraftServer.setTerminalEnabled(true);
        MineTerminal.startTerminalThread();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        // Init Instance and Events
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.DIRT);
            unit.modifier().fillHeight(40,41, Block.GRASS_BLOCK);
        });

        // Event System
        EventSystem eventSystem = new EventSystem(globalEventHandler, instanceContainer);
        eventSystem.RegisterEvents();
        // Command System
        new CommandSystem(instanceContainer);
        new GlobalInstance(instanceContainer);

        MinecraftServer.LOGGER.info(String.format("Using %s server mode.", server_mode.toUpperCase()));
        switch (server_mode.toUpperCase()) {
            case "OFFLINE":
                break;
            case "MOJANG":
                MojangAuth.init();
                break;
            case "VELOCITY":
                if (velocity_secret.length() <= 2)
                    throw new IllegalArgumentException("No Velocity secret found.\n Using OFFLINE configuration.");
                VelocityProxy.enable(velocity_secret);
        }

        server.start(server_ip, server_port);

        MinecraftServer.LOGGER.info(brand_name + " has started, IP: " +server_ip+ ", port: " +server_port);
    }
}
