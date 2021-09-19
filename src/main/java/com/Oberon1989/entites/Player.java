package com.Oberon1989.entites;

import com.Oberon1989.gameLogic.Game;

import javax.websocket.Session;
import java.util.ArrayList;

public class Player {
    private final Session session;
    private final String name;
    private final int id;
    private int gameId;
    private Game game;
    private ArrayList<Map> maps;
    private boolean isCanTurn;
    private boolean isRequireDrop;


    private int monasteryCount;
    private int mineCount;
    private int barracksCount;

    private int manaCount;
    private int oreCount;
    private int armyCount;

    private int tower;
    private int wall;





    public Player(Session session,String name,int id,int monasteryCount, int mineCount, int barracksCount, int manaCount, int oreCount, int armyCount,int tower,int wall) {
        this.name = name;
        this.session=session;
        this.id=id;
        this.isCanTurn=false;
        this.isRequireDrop=false;
        this.maps =new ArrayList<>();
        this.monasteryCount = monasteryCount;
        this.mineCount = mineCount;
        this.barracksCount = barracksCount;
        this.manaCount = manaCount;
        this.oreCount = oreCount;
        this.armyCount = armyCount;
        this.tower=tower;
        this.wall=wall;


    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getId() {
        return id;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean getTurn() {
        return isCanTurn;
    }
    public void nextTurn()
    {
        this.isCanTurn=!isCanTurn;
    }


    public String getName() {
        return name;
    }

   public void addMap(Map map)
   {
       maps.add(map);
   }

   public void addMap(Map map,int index)
   {
       maps.add(index,map);
   }

   public int getIndexForMap(Map map)
   {
       return maps.indexOf(map);
   }

   public Map getMapByIndex(int index) throws NullPointerException
   {
       return maps.get(index);
   }

   public Map getMapByName(String name)
   {
       for (Map m : maps)
       {
           if(m.getName().equals(name))
           {
               return m;
           }
       }
       return null;
   }
   public ArrayList<Map> getMaps()
    {
        return maps;
    }

   public void setMapByIndex(int index, Map map)
   {
       maps.set(index,map);
   }

    public int getMonasteryCount() {
        return monasteryCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getBarracksCount() {
        return barracksCount;
    }

    public int getManaCount() {
        return manaCount;
    }

    public int getOreCount() {
        return oreCount;
    }

    public int getArmyCount() {
        return armyCount;
    }
    public int getTowerCount()
    {
        return tower;
    }


    public void setMaps(ArrayList<Map> maps) {
        this.maps = maps;
    }

    public void addMonasteryCount(int monasteryCount) {
        if(this.monasteryCount+monasteryCount<=0)
        {
            this.monasteryCount=0;
            return;
        }

        this.monasteryCount += monasteryCount;
    }

    public void addMineCount(int mineCount) {
        if(this.mineCount+mineCount<=0)
        {
            this.mineCount=0;
            return;
        }
        this.mineCount += mineCount;
    }

    public void addBarracksCount(int barracksCount) {
        if(this.barracksCount+barracksCount<=0)
        {
            this.barracksCount=0;
            return;
        }
        this.barracksCount += barracksCount;
    }

    public void addManaCount(int manaCount) {
        if(this.manaCount+manaCount<=0)
        {
            this.manaCount=0;
            return;
        }
        this.manaCount += manaCount;
    }

    public void addOreCount(int oreCount) {
        if(this.oreCount+oreCount<=0)
        {
            this.oreCount=0;
            return;
        }
        this.oreCount += oreCount;
    }

    public void addArmyCount(int armyCount) {
        if(this.armyCount+armyCount<=0)
        {
            this.armyCount=0;
            return;
        }
        this.armyCount += armyCount;
    }

    public void addWall(int count)
    {
        if(this.wall+count<=0)
        {
            this.wall=0;
        }
        else
        {
            this.wall+=count;
        }

    }
    public void addTower(int count)
    {
       if(this.tower+count<=0)
       {
           this.tower=0;
       }
       else
       {
           tower+=count;
       }

    }

    public void getCommonDamage(int damage)
    {
        if(damage<=this.wall) {
            this.wall -= damage;
        }
        else
        {
            int dmgTower = damage-this.wall;
            this.wall=0;
            if(damage<=this.tower) {
                this.tower -= dmgTower;
            }
            else
            {
                this.tower=0;
            }
        }
    }
    public void getDamageWall(int damage)
    {
        if(damage<=this.wall)
        {
            this.wall-=damage;
        }
        else
        {
            this.wall=0;
        }
    }

    public void getDamageTower(int damage)
    {
        if(damage<=this.tower)
        {
            this.tower-=damage;
        }
        else
        {
            this.tower=0;
        }
    }

    public int getWallCount()
    {
        return wall;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {

        return String.format("{\"Player\":[{\"name\":\"%s\",\"monasteryCount\":\"%s\",\"mineCount\":\"%s\"," +
                "\"barracksCount\":\"%s\",\"tower\":\"%s\",\"wall\":\"%s\"}]}",name,monasteryCount,mineCount,barracksCount,tower,wall);
    }

    public Game getGame() {
        return this.game;
    }
}
