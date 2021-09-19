package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class spearMan extends Map {
    public spearMan() {
        super(MapColor.green, 2, "Копьеносец", "Если стена больше, чем у врага,\nто 3 урона,\nиначе 2 урона", "229.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (player.getWallCount() > enemy.getWallCount()) {
                enemy.getCommonDamage(3);
            } else {
                enemy.getCommonDamage(2);
            }
        }
    }
}
