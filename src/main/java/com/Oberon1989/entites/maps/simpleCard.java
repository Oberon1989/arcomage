package com.Oberon1989.entites.maps;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.Player;

public class simpleCard extends Map {

    private int player1ManaCount;
    private int player2ManaCount;

    private int player1OreCount;
    private int player2OreCount;

    private int player1ArmyCount;
    private int player2ArmyCount;

    private int player1MonasteryCount;
    private int player2MonasteryCount;

    private int player1MineCount;
    private int player2MineCount;

    private int player1BarracksCount;
    private int player2BarracksCount;

    private int player1TowerCount;
    private int player2TowerCount;

    private int player1WallCount;
    private int player2WallCount;

    private int player1CommonDamage;
    private int player2CommonDamage;

    public simpleCard(MapColor cardColor, int price, String name, String description, String imgPath, int player1ManaCount, int player2ManaCount, int player1OreCount, int player2OreCount, int player1ArmyCount, int player2ArmyCount, int player1MonasteryCount, int player2MonasteryCount, int player1MineCount, int player2MineCount, int player1BarracksCount, int player2BarracksCount, int player1TowerCount, int player2TowerCount, int player1WallCount, int player2WallCount,int player1CommonDamage,int player2CommonDamage,boolean canStepAgain) {
        super(cardColor, price, name, description, imgPath,canStepAgain);
        this.player1ManaCount = player1ManaCount;
        this.player2ManaCount = player2ManaCount;
        this.player1OreCount = player1OreCount;
        this.player2OreCount = player2OreCount;
        this.player1ArmyCount = player1ArmyCount;
        this.player2ArmyCount = player2ArmyCount;
        this.player1MonasteryCount = player1MonasteryCount;
        this.player2MonasteryCount = player2MonasteryCount;
        this.player1MineCount = player1MineCount;
        this.player2MineCount = player2MineCount;
        this.player1BarracksCount = player1BarracksCount;
        this.player2BarracksCount = player2BarracksCount;
        this.player1TowerCount = player1TowerCount;
        this.player2TowerCount = player2TowerCount;
        this.player1WallCount = player1WallCount;
        this.player2WallCount = player2WallCount;
        this.player1CommonDamage=player1CommonDamage;
        this.player2CommonDamage=player2CommonDamage;

    }

    @Override
    public void step(Player player, Player enemy) {
        if(Purchase(player))
        {
            player.addManaCount(player1ManaCount);
            enemy.addManaCount(player2ManaCount);

            player.addOreCount(player1OreCount);
            enemy.addOreCount(player2OreCount);

            player.addArmyCount(player1ArmyCount);
            enemy.addArmyCount(player2ArmyCount);

            player.addMonasteryCount(player1MonasteryCount);
            enemy.addMonasteryCount(player2MonasteryCount);

            player.addMineCount(player1MineCount);
            enemy.addMineCount(player2MineCount);

            player.addBarracksCount(player1BarracksCount);
            enemy.addBarracksCount(player2BarracksCount);

            player.addTower(player1TowerCount);
            enemy.addTower(player2TowerCount);

            player.addWall(player1WallCount);
            enemy.addWall(player2WallCount);

            player.getCommonDamage(player1CommonDamage);
            enemy.getCommonDamage(player2CommonDamage);
        }


    }
}
