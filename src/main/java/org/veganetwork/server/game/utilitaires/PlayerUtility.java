package org.veganetwork.server.game.utilitaires;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.time.Duration;

// All calls and function for Player instance will be here
public class PlayerUtility {
    private static InstanceContainer iContainer;
    public PlayerUtility(InstanceContainer instanceContainer) {
        iContainer = instanceContainer;
    }
    public static Player findPlayer(String playerName) {
        for (Player player : iContainer.getPlayers()) {
            if (player.getUsername().equals(playerName)) {
                return player;
            }
        }
        return null;
    }
    public static void spawnItemStack(Material type, Pos position, InstanceContainer ic, boolean playerDroped) {
        ItemEntity item = new ItemEntity(ItemStack.of(type));
        item.setPickupDelay(Duration.ofMillis(500));
        if(!playerDroped) {
            double randomX = (position.x() + 0.5) + getRandomValueInRange(-0.3, 0.3);
            double randomY = position.y() + getRandomValueInRange(0.2, 0.5);
            double randomZ = (position.z() + 0.5) + getRandomValueInRange(-0.3, 0.3);
            item.setInstance(ic, new Pos(randomX, randomY, randomZ));
        } else {
            item.setInstance(ic, position);
        }
    }
    public static double getRandomValueInRange(double min, double max) {
        return min + Math.random() * (max - min);
    }
}
