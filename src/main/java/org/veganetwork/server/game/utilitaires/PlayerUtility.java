package org.veganetwork.server.game.utilitaires;

import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;

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
}
