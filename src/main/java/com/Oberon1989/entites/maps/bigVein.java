package com.Oberon1989.entites.maps;
import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class bigVein extends Map {

    public bigVein() {
        super(MapColor.red,10,"Большая жила","Если шахта меньше\nчем у врага,\n то шахта +2,\n иначе шахта +1","005.png",false);
    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player)) {

                if (player.getMineCount() < enemy.getMineCount()) {
                    player.addMineCount(2);
                } else {
                    player.addMineCount(1);
                }


        }
    }
}
