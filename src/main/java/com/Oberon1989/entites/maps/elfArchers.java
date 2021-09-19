package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class elfArchers extends Map {
    public elfArchers() {
        super(MapColor.green, 10, "Эльфы-лучники", "Если стена больше,\nчем у врага, то\n6 урона башне врага,\nиначе 6 урона", "222.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if (Purchase(player)) {
            if (player.getWallCount() > enemy.getWallCount()) {
                enemy.addTower(-6);
            } else {
                enemy.getCommonDamage(6);
            }
        }
    }
}
