package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class unicorn extends Map {
    public unicorn() {
        super(MapColor.green, 9, "Единорог", "Если монастырь больше, чем у врага,\n то 12 урона,\nиначе 8 урона", "221.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (player.getMonasteryCount() > enemy.getMonasteryCount()) {
                enemy.getCommonDamage(12);
            } else {
                enemy.getCommonDamage(8);
            }
        }
    }
}
