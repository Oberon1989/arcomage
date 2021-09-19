package com.Oberon1989.gameLogic;
import com.Oberon1989.entites.Player;
import com.Oberon1989.util.CardDeck;
import com.Oberon1989.util.GameUtil;
import java.io.IOException;
import java.util.HashMap;


public class Game {
    java.util.Map<Player,Player> players;
    public CardDeck cardDeck;
    private final int gameId;
    private int maxResources;
    private int maxTower;

    public int getMaxResources() {
        return maxResources;
    }

    public int getMaxTower() {
        return maxTower;
    }

    public Game(Player p1, Player p2, int gameId, int maxResources, int maxTower) throws IOException {
        this.gameId=gameId;
        this.maxResources=maxResources;
        this.maxTower=maxTower;
        players=new HashMap();
        players.put(p1,p2);
        cardDeck= GameUtil.CreateCardDeck();
        cardDeck.shuffleCards();
        giveCards(getPlayers());
    }

    public int getGameId() {
        return gameId;
    }

    public void getCard(Player player, int index)
    {
       cardDeck.push(player.getMapByIndex(index));
       player.setMapByIndex(index,cardDeck.pop());

    }


    public boolean containsPlayer(Player player)
    {
        for (java.util.Map.Entry<Player, Player> entry : players.entrySet()) {
            if (entry.getKey().getId() == player.getId() || entry.getValue().getId() == player.getId()) {
                return true;
            }
        }
        return false;

    }
    private Player[] getPlayers()
    {
        Player[] tmp=new Player[2];
        for (java.util.Map.Entry<Player, Player> entry : players.entrySet()) {
          tmp[0]=entry.getKey();
          tmp[1]=entry.getValue();
        }
        return tmp;
    }
    public Player[] getPlayers(int id)
    {
        Player[] tmp=new Player[2];
        for (java.util.Map.Entry<Player, Player> entry : players.entrySet()) {
            if(entry.getKey().getId()==id) {
                tmp[0] = entry.getKey();
                tmp[1] = entry.getValue();
            }
            if(entry.getValue().getId()==id)
            {
                tmp[0] = entry.getValue();
                tmp[1] = entry.getKey();

            }
        }
        return tmp;
    }
    public Player getEnemy(Player player) {
        for (java.util.Map.Entry<Player, Player> entry : players.entrySet()) {
            if (entry.getKey().equals(player)) {
                return entry.getValue();
            }
            if (entry.getValue().equals(player)) {
                return entry.getKey();

            }
        }
        return null;
    }

    private void giveCards(Player[] players)
    {

        for (int i = 0; i < 12; i++) {
            if(i%2==0)
            {
               players[0].addMap(cardDeck.pop());
            }
            else
            {
                players[1].addMap(cardDeck.pop());
            }
        }
    }

    public void incomePerTurn(Player player)
    {
        player.addManaCount(player.getMonasteryCount());

        player.addOreCount(player.getMineCount());
       player.addArmyCount(player.getBarracksCount());

    }

}
