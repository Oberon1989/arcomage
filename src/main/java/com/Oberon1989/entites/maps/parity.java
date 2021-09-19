package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class parity extends Map {
    public parity() {
        super(MapColor.blue, 7, "Паритет", "Монастырь всех\nстановится равным\nмонастырю сильнейшего", "113.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            int max = player.getMonasteryCount();
            if (max < enemy.getMonasteryCount()) {
                max = enemy.getMonasteryCount();
            }
            enemy.addMonasteryCount(-enemy.getMonasteryCount());
            player.addMonasteryCount(-player.getMonasteryCount());
            player.addMonasteryCount(max);
            enemy.addMonasteryCount(max);
        }
    }
}
