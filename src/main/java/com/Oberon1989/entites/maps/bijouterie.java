package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class bijouterie extends Map {
    public bijouterie() {
        super(MapColor.blue, 0, "Бижутерия", "Если башня < вражеской,\nто +2 к башне,\n иначе +1","129.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if (player.getTowerCount() < enemy.getTowerCount()) {
                player.addTower(2);
            } else {
                player.addTower(1);
            }
        }
    }
}
