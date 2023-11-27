package org.veganetwork.server.game.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.*;
import net.minestom.server.instance.InstanceContainer;

import static org.veganetwork.Main.logger;

public class PlayerEventHandler {
    public PlayerEventHandler(GlobalEventHandler gEventHandler, InstanceContainer iContainer) {
        gEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(iContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
            logger.info("%p has joined the server".replace("%p", player.getUsername()));
        });
        gEventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            final Player player = event.getPlayer();
            logger.info("%p has left the server".replace("%p", player.getUsername()));
        });



        // read manual + rewrite, bad implementation
//        gEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
//            Instance instance = event.getInstance();
//
//            Block block = event.getBlock();
//
//            ItemStack block_item = ItemStack.of(Material.fromNamespaceId(block.namespace()));
//
//            Entity bs = new Entity(EntityType.ITEM);
//            ItemEntityMeta bm = (ItemEntityMeta) bs.getEntityMeta();
//
//            bm.setItem(block_item);
//            bs.setInstance(instance, event.getBlockPosition());
//        });
        // Listen to chat event (not command or slash chat event)
        gEventHandler.addListener(PlayerChatEvent.class, event ->
                logger.info(String.format("%s: %s", event.getPlayer().getUsername(), event.getMessage()))
        );
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
        gEventHandler.addListener(PlayerCommandEvent.class, event -> {
            logger.info(String.format("%s executed command %s", event.getPlayer(), event.getCommand()));
        });
    }
}
