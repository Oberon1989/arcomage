package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class lightning extends Map {
    public lightning() {
        super(MapColor.blue, 11, "Молния", "Если башня > стены врага,\nто 8 урона башне врага,\nиначе 8 урона всем", "132.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (player.getTowerCount() > enemy.getWallCount()) {
                enemy.addTower(-8);
            } else {
                player.getCommonDamage(8);
                enemy.getCommonDamage(8);
            }
        }
    }
}
