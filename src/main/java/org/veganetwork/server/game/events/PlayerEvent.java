package org.veganetwork.server.game.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import static org.veganetwork.server.game.utilitaires.GlobalInstance.spawnItemStack;

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
            Block block = event.getBlock();
            Material block_item = Material.fromNamespaceId(block.namespace());
            spawnItemStack(block_item, Pos.fromPoint(event.getBlockPosition()), iContainer);
        });
        gEventHandler.addListener(PickupItemEvent.class, event ->{
            Player targetPlayer = (Player) event.getEntity();
            Material itemType = event.getItemStack().getMaterial();
            targetPlayer.getInventory().addItemStack(ItemStack.of(itemType, 1));
        });
        gEventHandler.addListener(PlayerChatEvent.class, event ->
                event.setChatFormat((sub_event) -> Component.text(sub_event.getEntity().getUsername())
                .append(Component.text(" | ", NamedTextColor.DARK_GRAY)
                .append(Component.text(sub_event.getMessage(), NamedTextColor.WHITE)))));
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
        gEventHandler.addListener(PlayerCommandEvent.class, event -> MinecraftServer.LOGGER.info(String.format("%s executed command %s", event.getPlayer(), event.getCommand())));
    }
}
