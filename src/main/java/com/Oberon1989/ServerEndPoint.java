package com.Oberon1989;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;
import com.Oberon1989.gameLogic.Game;
import com.Oberon1989.util.GameUtil;
import com.Oberon1989.util.MessageForFinishGame;
import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;

@ServerEndpoint(value = "/arcomage/{playerName}", encoders= MessageEncoder.class)
public class ServerEndPoint {
    private String name;
    private Session session;
    private Player player;
    private Player enemy;
    private int id;
    ObjectMapper mapper;
    private webSockConnections connections;
    private Game game;


    @OnOpen
    public void OnOpen(@PathParam("playerName")String playerName, Session session) throws IOException, EncodeException {
     mapper=new ObjectMapper();
        this.connections=webSockConnections.getInstance();
        this.session=session;
        this.name=playerName;
        this.id=Integer.parseInt(this.session.getId());
        this.player = new Player(session,name,Integer.parseInt(session.getId()),3,3,3,10,10,10,21,25);
        connections.addPlayer(this.player);
        if(connections.getGameCount()>0)
        {
           sendStartState();

        }
        else
        {
           this.session.getBasicRemote().sendText("{\"action\":\"wait\",\"message\":\"Ожидание второго игрока\"}");
        }


    }

    @OnClose
    public void onClose() throws IOException {
        Game game = connections.getGameById(player.getGameId());
        connections.removePlayer(player);
        if(game!=null)
        {
            Player[]players = game.getPlayers(id);
            if(players!=null)
            {
                if(players[1]!=null&&players[0].getSession().isOpen())
                {
                    players[1].getSession().getBasicRemote().sendText("{\"action\":\"end\",\"message\":\"Противник покинул игру\"}");
                }
            }
            connections.closeRunGame(game);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {

        if(connections.getGameCount()>0)
        {
            this.game=player.getGame();
            this.enemy=game.getEnemy(player);

            String action=mapper.readTree(message).findValue("action").asText();

            switch (action) {

                case "Step": {

                    id = Integer.parseInt(mapper.readTree(message).findValue("mapId").asText());
                    Map map = player.getMapByIndex(id);
                    map.step(player, enemy);
                    MessageForFinishGame msg = GameUtil.CheckVin(player, enemy, game.getMaxTower(), game.getMaxResources());
                    game.getCard(player, id);

                    if (!map.isCanStepAgain()) {
                        player.nextTurn();
                        enemy.nextTurn();
                    }
                    sendGameState("turnPlayer", action, map, false);
                    if (msg != null) {
                        this.session.getBasicRemote().sendText(msg.getMessageVin());
                        enemy.getSession().getBasicRemote().sendText(msg.getMessageLose());
                        onClose();
                        return;
                    }


                        msg = giveResourceForEnemy();
                        if (msg != null) {
                            enemy.getSession().getBasicRemote().sendText(msg.getMessageVin());
                            this.session.getBasicRemote().sendText(msg.getMessageLose());
                            onClose();
                            return;
                        }



                    break;
                }
                case "Drop": {
                    id = Integer.parseInt(mapper.readTree(message).findValue("mapId").asText());
                    Map map = player.getMapByIndex(id);
                    game.getCard(player, id);
                    player.nextTurn();
                    enemy.nextTurn();

                    MessageForFinishGame msg = giveResourceForEnemy();
                    sendGameState("turnPlayer", action, map, true);
                    if(msg!=null)
                    {  enemy.getSession().getBasicRemote().sendText(msg.getMessageVin());
                        this.session.getBasicRemote().sendText(msg.getMessageLose());
                        onClose();
                        return;

                    }
                break;
            }
                case "Msg":
                    String msg = mapper.readTree(message).findValue("message").asText();
                    enemy.getSession().getBasicRemote().sendText("{\"action\":\"msg\",\"message\":\""+msg+"\"}");
                    break;
            }

        }
    }
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

    private void sendGameState(String action,String cardAction,Map turnMap,boolean isDrop) throws IOException, EncodeException {
        Player[] players = connections.getPlayersFromGame(Integer.parseInt(this.session.getId()));
        players[0].getSession().getBasicRemote().sendObject(new GameState(action, GameUtil.createPlayerTransfer(players[0],players[1]),null,null,false));
        players[1].getSession().getBasicRemote().sendObject(new GameState("turnEnemy", GameUtil.createPlayerTransfer(players[1],players[0]),cardAction,turnMap,isDrop));
    }

    private void sendStartState() throws IOException, EncodeException {
        Player[] players = connections.getPlayersFromGame(Integer.parseInt(this.session.getId()));
        players[0].getSession().getBasicRemote().sendObject(new GameState("start", GameUtil.createPlayerTransfer(players[0],players[1]),null,null,false));
        players[1].getSession().getBasicRemote().sendObject(new GameState("start", GameUtil.createPlayerTransfer(players[1],players[0]),null,null,false));
    }

    private void sendMessage(Player player,String message) throws IOException {
        if(player!=null)
        {
            if(player.getSession()!=null)
            {
                if(player.getSession().isOpen())
                {
                    player.getSession().getBasicRemote().sendText(message);
                }
            }
        }
    }
    private MessageForFinishGame giveResourceForEnemy()
    {
        game.incomePerTurn(enemy);
        return GameUtil.CheckVin(enemy,player,game.getMaxTower(), game.getMaxResources());
    }




}
