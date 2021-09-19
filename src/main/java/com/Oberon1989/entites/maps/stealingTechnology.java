package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class stealingTechnology extends Map {
    public stealingTechnology() {
        super(MapColor.red, 5, "Кража технологий", "Если шахта меньше\nшахты врага, то\nшахта становится\nравной вражеской", "008.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player))
        {
            if(player.getMineCount()<enemy.getMineCount())
            {
                player.addMineCount(enemy.getMineCount()-player.getMineCount());
            }
        }
    }
}
