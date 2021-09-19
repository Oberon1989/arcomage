package com.Oberon1989;

import com.Oberon1989.entites.Player;
import com.Oberon1989.gameLogic.Game;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class webSockConnections {
    private ArrayList<Player> players;
    private ArrayList<Player> allPlayers;
    private Map<Player,Player> pairOfPlayers;
    private ArrayList<Game> runGames;
    private int countStep;
    private static volatile webSockConnections instance;
    private static final AtomicInteger gameIds = new AtomicInteger(0);
    private static String url=null;

    private webSockConnections() {
        this.countStep=0;
        players=new ArrayList<>();
        pairOfPlayers=new HashMap<>();
        runGames=new ArrayList<>();
        allPlayers=new ArrayList<>();
    }

    public static webSockConnections getInstance() {
        webSockConnections result = instance;
        if (instance != null) {
            return result;
        }
        synchronized (webSockConnections.class) {
            if (instance == null) {
                instance = new webSockConnections();
            }
            return instance;
        }
    }


    public void addPlayer(Player player) throws IOException {
        this.players.add(player);
        if(players.size()==2)
        {
            int gameID=gameIds.getAndIncrement();
            players.get(0).setGameId(gameID);
            players.get(1).setGameId(gameID);
            Random rnd = new Random();
            int numbers = rnd.nextInt(2);
            players.get(numbers).nextTurn();
            Game game = new Game(players.get(0),players.get(1),gameID,250,100);
            players.get(0).setGame(game);
            players.get(1).setGame(game);
            runGames.add(game);
            players.clear();
        }
    }
    public void removePlayer(Player player)
    {
        if(players.contains(player))
        {
            players.remove(player);
        }
    }

    public Game getGameById(int id)
    {
        for (Game game:runGames)
        {
            if(game.getGameId()==id)
            {
                return game;
            }
        }
        return null;
    }

    public int getGameCount()
    {
        return runGames.size();
    }


    public Player[] getPlayersFromGame(int id)
    {
        for (int i = 0; i < runGames.size(); i++) {
            Player[] tmp=runGames.get(i).getPlayers(id);
            if(tmp!=null)return tmp;
        }
        return null;
    }

    public Player[] getPairPlayer(int id) {
        for (Map.Entry<Player, Player> entry : pairOfPlayers.entrySet()) {
            if (entry.getValue().getId()==id) {
                return new Player[]{entry.getValue(),entry.getKey()};
            }
            if (entry.getKey().getId()==id) {
                return new Player[]{entry.getKey(),entry.getValue()};
            }
        }

            return null;
    }

    public void closeRunGame(Game game)
    {
        if(runGames.contains(game))
        {
            runGames.remove(game);
        }
    }

}
