package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class barracks extends Map {
    public barracks() {
        super(MapColor.red, 10, "Казармы", "+6 отрядов,\n+6 к стене.\nЕсли казарма < вражеской\n,то +1 казарма", "032.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player))
        {
            player.addArmyCount(6);
            player.addWall(6);

                if(player.getBarracksCount()<enemy.getBarracksCount())
                {
                    player.addBarracksCount(1);
                }

        }
    }
}
