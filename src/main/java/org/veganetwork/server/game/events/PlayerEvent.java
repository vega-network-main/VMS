package org.veganetwork.server.game.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

import static org.veganetwork.server.game.utilitaires.PlayerUtility.getRandomValueInRange;
import static org.veganetwork.server.game.utilitaires.PlayerUtility.spawnItemStack;


public class PlayerEvent {
    public PlayerEvent(GlobalEventHandler gEventHandler, InstanceContainer iContainer) {
        gEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(iContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
            MinecraftServer.LOGGER.info("%p has joined the server".replace("%p", player.getUsername()));
        });
        gEventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            final Player player = event.getPlayer();
            MinecraftServer.LOGGER.info("%p has left the server".replace("%p", player.getUsername()));
        });
        gEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                Block block = event.getBlock();
                Material block_item = Material.fromNamespaceId(block.namespace());
                spawnItemStack(block_item, Pos.fromPoint(event.getBlockPosition()), iContainer, false);
            }
        });
        gEventHandler.addListener(PickupItemEvent.class, event ->{
            Player targetPlayer = (Player) event.getEntity();
            targetPlayer.getInventory().addItemStack(event.getItemStack());
        });
        gEventHandler.addListener(PlayerChatEvent.class, event ->
            event.setChatFormat((sub_event) -> Component.text(sub_event.getEntity().getUsername())
                .append(Component.text(" | ", NamedTextColor.BLACK)
                .append(Component.text(sub_event.getMessage(), NamedTextColor.WHITE)))));
        gEventHandler.addListener(ItemDropEvent.class, event -> {

        });


        // requires NEW formula, x and z is broken
//        gEventHandler.addListener(ItemDropEvent.class, event -> {
//            Player player = event.getPlayer();
//            Pos playerPos = player.getPosition();
//
//            MinecraftServer.LOGGER.info("Player Pos: " + playerPos);
//
//            double distanceOfDrop = 1.0;
//            double pitch = Math.toRadians(playerPos.pitch());
//
//            double offsetX = playerPos.x() + Math.cos(pitch);
//            double offsetZ = playerPos.z() + Math.cos(pitch);
//
//            Pos dropPos = new Pos(offsetX, playerPos.y(), offsetZ);
//            MinecraftServer.LOGGER.info("block spawn pos: " + dropPos);
//            // Spawn the dropped item at the calculated position
//            spawnItemStack(event.getItemStack().material(), dropPos, iContainer, true);
//        });
        // Not working
//        gEventHandler.addListener(PickupItemEvent.class, event -> {
//            logger.warn(event.getEntity() + "s");
//            Player player = (Player) event.getEntity();
//            player.getInventory().addItemStack(event.getItemStack());
//        });
        // no usage
//        gEventHandler.addListener(PlayerGameModeChangeEvent.class, event -> {
//            Player player = event.getPlayer();
//            player.setGameMode(event.getNewGameMode());
//        });
        gEventHandler.addListener(PlayerCommandEvent.class, event -> MinecraftServer.LOGGER.info(String.format("%s executed command /%s", event.getPlayer().getUsername(), event.getCommand())));
    }
}
