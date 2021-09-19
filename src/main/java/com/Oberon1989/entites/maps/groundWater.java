package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class groundWater extends Map {

    public groundWater() {
        super(MapColor.red, 6,"Грунтовые воды", "Игрок с меньшей стеной\nтеряет 1 казарму и\n получает 2 урона к башне", "031.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {
            if(player.getWallCount()<enemy.getWallCount())
            {
                player.addBarracksCount(-1);
                player.addTower(-2);
            }
            else if(enemy.getWallCount()<player.getWallCount())
            {
                enemy.addBarracksCount(-1);
                enemy.addTower(-2);
            }
        }
    }
}
