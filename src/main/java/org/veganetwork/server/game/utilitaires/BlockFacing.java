package org.veganetwork.server.game.utilitaires;

public enum BlockFacing {
    NORTH(new int[]{15, 0, 1, 2}),
    EAST(new int[]{3, 4, 5, 6}),
    SOUTH(new int[]{7, 8, 9, 10}),
    WEST(new int[]{11, 12, 13, 14});

    private final int[] intValue;
    BlockFacing(int[] input) {
        this.intValue = input;
    }
    public int[] getIntValues() {
        return intValue;
    }
}
