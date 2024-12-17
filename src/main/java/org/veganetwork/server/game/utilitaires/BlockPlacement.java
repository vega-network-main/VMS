package org.veganetwork.server.game.utilitaires;

import net.minestom.server.MinecraftServer;

import java.util.List;
public class BlockPlacement {
    private static final List<Integer> Y = List.of(4,5,6);
    public static String getFacing(int inputX, int inputY) {
        MinecraftServer.LOGGER.info(String.valueOf(!Y.contains(inputY)));
        MinecraftServer.LOGGER.info("{} {}", inputX, inputY);
        if(Y.contains(inputY)) {
            return switch (inputX) {
                case 0, 1, 2, 15 -> "south";
                case 3, 4, 5, 6 -> "west";
                case 7, 8, 9, 10 -> "north";
                default -> "east";
            };
        } else {
            return switch (inputY) {
                case 0, 1, 2, 3 -> "down";
                case 7, 8, 9, 10 -> "up";
                default -> null;
            };
        }
    }
    public static String getAxis(int inputX, int inputY) {
        if(Y.contains(inputY)) {
            return switch (inputX) {
                case 0, 1, 2, 7, 8, 9, 10, 15 -> "z";
                default -> "x";
            };
        } else {
            return "y";
        }
    }
}