package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class acridCloud extends Map {
    public acridCloud() {
        super(MapColor.green, 11, "Едкое облако", "Если стена врага > 10,\nто 10 урона,\nиначе 7 урона", "220.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (enemy.getWallCount() > 10) {
                enemy.getCommonDamage(10);
            } else {
                enemy.getCommonDamage(7);
            }
        }
    }
}
