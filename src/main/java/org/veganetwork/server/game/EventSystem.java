package org.veganetwork.server.game;

import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.item.ItemEntityMeta;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.ping.ResponseData;

import java.util.Objects;

import static org.veganetwork.Main.logger;
import static org.veganetwork.configs.ConfigServer.*;

public class EventSystem {
    private final GlobalEventHandler globalEventHandler;
    private final InstanceContainer instanceContainer;
    public EventSystem(GlobalEventHandler globalEventHandler, InstanceContainer instanceContainer) {
        this.globalEventHandler = globalEventHandler;
        this.instanceContainer = instanceContainer;
    }
    public void RegisterEvents() {
        PlayerEventHandler();
        ServerPingHandler();
    }
    private void PlayerEventHandler() {
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
            logger.info("%p has joined the server".replace("%p", player.getUsername()));
        });
        globalEventHandler.addListener(PlayerDisconnectEvent.class,event -> {
            final Player player = event.getPlayer();
            logger.info("%p has left the server".replace("%p", player.getUsername()));
        });
        // read manual
        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            Instance instance = event.getInstance();

            Block block = event.getBlock();

            ItemStack block_item = ItemStack.of(Material.fromNamespaceId(block.namespace()));

            Entity bs = new Entity(EntityType.ITEM);
            ItemEntityMeta bm = (ItemEntityMeta) bs.getEntityMeta();

            bm.setItem(block_item);
            bs.setInstance(instance, event.getBlockPosition());
        });
        globalEventHandler.addListener(PlayerChatEvent.class, event -> {
           logger.info(String.format("%s: %s", event.getPlayer().getUsername(), event.getMessage()));
        });
        globalEventHandler.addListener(PickupItemEvent.class, event -> {
           logger.warn(event.getEntity() + "s");
           Player player = (Player) event.getEntity();
           player.getInventory().addItemStack(event.getItemStack());
        });
        // REWRITE
        globalEventHandler.addListener(PlayerGameModeChangeEvent.class, event -> {
            Player player = event.getPlayer();
            player.setGameMode(event.getNewGameMode());
        });
    }
    private void ServerPingHandler() {
        globalEventHandler.addListener(ServerListPingEvent.class, event -> {
            ResponseData responseData = event.getResponseData();
            responseData.setMaxPlayer(max_players);
            responseData.setDescription(Component.text(motd));
        });
    }
}
