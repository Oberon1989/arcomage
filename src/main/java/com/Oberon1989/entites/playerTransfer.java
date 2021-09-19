package com.Oberon1989.entites;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.ArrayList;
@JsonAutoDetect
public class playerTransfer {
    private String name;
    private ArrayList<Map> maps;
    private int monasteryCount;
    private int mineCount;
    private int barracksCount;

    private int manaCount;
    private int oreCount;
    private int armyCount;

    private int tower;
    private int wall;
    private boolean canStep;

    public playerTransfer(String name, ArrayList<Map> maps, int monasteryCount, int mineCount, int barracksCount, int manaCount, int oreCount, int armyCount, int tower, int wall,boolean canStep) {
        this.name = name;
        this.maps = maps;
        this.monasteryCount = monasteryCount;
        this.mineCount = mineCount;
        this.barracksCount = barracksCount;
        this.manaCount = manaCount;
        this.oreCount = oreCount;
        this.armyCount = armyCount;
        this.tower = tower;
        this.wall = wall;
        this.canStep=canStep;
    }

    public boolean isCanStep() {
        return canStep;
    }

    public void setCanStep(boolean canStep) {
        this.canStep = canStep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<Map> maps) {
        this.maps = maps;
    }

    public int getMonasteryCount() {
        return monasteryCount;
    }

    public void setMonasteryCount(int monasteryCount) {
        this.monasteryCount = monasteryCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getBarracksCount() {
        return barracksCount;
    }

    public void setBarracksCount(int barracksCount) {
        this.barracksCount = barracksCount;
    }

    public int getManaCount() {
        return manaCount;
    }

    public void setManaCount(int manaCount) {
        this.manaCount = manaCount;
    }

    public int getOreCount() {
        return oreCount;
    }

    public void setOreCount(int oreCount) {
        this.oreCount = oreCount;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(int armyCount) {
        this.armyCount = armyCount;
    }

    public int getTower() {
        return tower;
    }

    public void setTower(int tower) {
        this.tower = tower;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }
}
