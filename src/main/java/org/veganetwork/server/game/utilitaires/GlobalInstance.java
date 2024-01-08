package org.veganetwork.server.game.utilitaires;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class GlobalInstance {
    private static InstanceContainer iContainer;
    public GlobalInstance(InstanceContainer iContainer){
    }
    public static void spawnItemStack(Material type, Pos position, InstanceContainer ic) {
        ItemEntity item = new ItemEntity(ItemStack.of(type));
        item.setInstance(ic, position);
    }
}
