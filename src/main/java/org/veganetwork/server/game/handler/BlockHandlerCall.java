package org.veganetwork.server.game.handler;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.Direction;
import net.minestom.server.utils.MathUtils;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collection;

public class BlockHandlerCall implements BlockHandler {
    @Override
    public void onPlace(@NotNull Placement placement) {
        if (placement instanceof PlayerPlacement playerPlacement) {
            Instance instance = placement.getInstance();
            playerPlacement.getBlockPosition().blockX();
            playerPlacement.getBlockPosition().blockY();
            placement.getInstance().getBlock(playerPlacement.getBlockPosition());



            //Block block = event.getBlock();
            //            //MinecraftServer.LOGGER.info(block.properties().toString());
            //            //MinecraftServer.LOGGER.info(String.valueOf((int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10)));
            //            if (block.getProperty("rotation") != null) {
            //                int blockRotation = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;
            //
            //                event.setBlock(block.withProperty("rotation", String.valueOf(blockRotation)));
            //            } else if (block.getProperty("facing") != null) {
            //                int blockFacingX = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;
            //                int blockFacingY = (int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10);
            //
            //                event.setBlock(block.withProperty("facing", String.valueOf(BlockPlacement.getFacing(blockFacingX, blockFacingY))));
            //            } else if (block.getProperty("axis") != null) {
            //                int blockAxisX = (int) Math.floor((double) ((180.0F + event.getPlayer().getPosition().yaw()) * 16.0F / 360.0F) + 0.5D) & 15;
            //                int blockAxisY = (int) Math.floor((double) ((90.0F + event.getPlayer().getPosition().pitch()) / 180.0F) * 10);
            //                event.setBlock(block.withProperty("axis", String.valueOf(BlockPlacement.getAxis(blockAxisX, blockAxisY))));
            //            }
            // block logic
            Direction playerDirection = MathUtils.getHorizontalDirection(playerPlacement.getPlayer().getPosition().yaw());
            MinecraftServer.LOGGER.info("{} {} {}", playerDirection.name(), playerDirection.vec(), playerPlacement.getPlayer().getPosition().yaw());
            Block correctFacing = placement.getBlock().withProperty("facing", playerDirection.name().toLowerCase());

            instance.setBlock(placement.getBlockPosition() ,correctFacing);
            //Instance instance, Block bedBlock, Point footPosition, Point headPosition, Direction facing
            //BlockHandler.super.onPlace(placement);
        }
    }

    @Override
    public void onDestroy(@NotNull Destroy destroy) {
        BlockHandler.super.onDestroy(destroy);
    }

    @Override
    public boolean onInteract(@NotNull Interaction interaction) {
        return BlockHandler.super.onInteract(interaction);
    }

    @Override
    public void onTouch(@NotNull Touch touch) {
        BlockHandler.super.onTouch(touch);
    }

    @Override
    public void tick(@NotNull Tick tick) {
        BlockHandler.super.tick(tick);
    }

    @Override
    public boolean isTickable() {
        return BlockHandler.super.isTickable();
    }

    @Override
    public @NotNull Collection<Tag<?>> getBlockEntityTags() {
        return BlockHandler.super.getBlockEntityTags();
    }

    @Override
    public byte getBlockEntityAction() {
        return BlockHandler.super.getBlockEntityAction();
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return null;
    }
}
