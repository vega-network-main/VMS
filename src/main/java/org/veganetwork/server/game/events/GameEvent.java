package org.veganetwork.server.game.events;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.veganetwork.server.game.utilitaires.BlockPlacement;

public class GameEvent {
    public GameEvent(@NotNull GlobalEventHandler gEventHandler, InstanceContainer iContainer) {
        gEventHandler.addListener(PlayerBlockPlaceEvent.class, event -> {
            Block block = event.getBlock();
            //MinecraftServer.LOGGER.info(block.properties().toString());
            //MinecraftServer.LOGGER.info(String.valueOf((int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10)));
            if (block.getProperty("rotation") != null) {
                int blockRotation = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;

                event.setBlock(block.withProperty("rotation", String.valueOf(blockRotation)));
            } else if (block.getProperty("facing") != null) {
                int blockFacingX = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;
                int blockFacingY = (int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10);

                event.setBlock(block.withProperty("facing", String.valueOf(BlockPlacement.getFacing(blockFacingX, blockFacingY))));
            } else if (block.getProperty("axis") != null) {
                int blockAxisX = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;
                int blockAxisY = (int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10);
                event.setBlock(block.withProperty("axis", String.valueOf(BlockPlacement.getAxis(blockAxisX, blockAxisY))));
            }
//            if (block.equals(Block.SAND)) {
//                iContainer.scheduleNextTick(instance -> checkFall(instance, event.getBlockPosition()));
//            }
        });
    }
// Insufficient, needs to be redone. Code tooked from VRI and changed
//    public static void checkFall(Instance instance, Point position) {
//        MinecraftServer.LOGGER.info("CHECK");
//        Block block = instance.getBlock(position);
//        Block below = instance.getBlock(position.blockX(), position.blockY() - 1, position.blockZ());
//
//        // Exit out now if block below is solid
//        if (below.isSolid()) {
//            return;
//        }
//        instance.setBlock(position, Block.AIR);
//
//        Pos initialPosition = new Pos(position.x() + 0.5f, Math.round(position.y()), position.z() + 0.5f);
//        Entity fallingBlockEntity = new Entity(EntityType.FALLING_BLOCK);
//        ((FallingBlockMeta)fallingBlockEntity.getEntityMeta()).setBlock(block);
//        fallingBlockEntity.setInstance(instance, initialPosition);
//    }
}
