package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class Foundation extends Map {
    public Foundation() {
        super(MapColor.red, 3, "Фундамент", "Если стена = 0,\nто +5 к стене,\nиначе +3 к стене", "012.png", false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player))
        {
            if(player.getWallCount()==0)
            {
                player.addWall(5);
            }
            else
            {
                player.addWall(3);
            }
        }
    }
}
