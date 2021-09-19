package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class bug extends Map {
    public bug() {
        super(MapColor.green, 8, "Жучара", "Если стена у врага =0,\nто 10 урона,\n иначе 6 урона", "218.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (enemy.getWallCount() == 0) {
                enemy.getCommonDamage(10);
            } else {
                enemy.getCommonDamage(6);
            }
        }
    }
}
