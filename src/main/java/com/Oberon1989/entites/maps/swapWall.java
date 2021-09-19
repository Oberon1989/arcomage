package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class swapWall extends Map {
    public swapWall() {
        super(MapColor.red, 17, "Сдвиг", "Ваша и вражеская стена\nменяются местами", "034.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player))
        {
            int tmpEnemyWall=enemy.getWallCount();
            enemy.addWall(-tmpEnemyWall);
            enemy.addWall(player.getWallCount());
            player.addWall(-player.getWallCount());
            player.addWall(tmpEnemyWall);
        }
    }
}
