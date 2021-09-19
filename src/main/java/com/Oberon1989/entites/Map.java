package com.Oberon1989.entites;

import java.awt.*;

public abstract class Map {
    private MapColor cardColor;
    private int price;
    private String name;
    private String description;
    private String imgPath;
    private boolean canStepAgain;
    public static final String path =  "/resources/img/cards/";

    public Map(MapColor cardColor,int price,String name,String description,String imgPath,boolean canStepAgain) {
        this.cardColor = cardColor;
        this.price=price;
        this.name=name;
        this.description=description;
        this.imgPath=path+imgPath;
        this.canStepAgain=canStepAgain;
    }

    public String getImgPath() {
        return imgPath;
    }

    public abstract void step(Player player, Player enemy);

    public String getStr(Player p1,Player p2)
    {
        String str = String.format("Карта: %s\nИмя атакаующего: %s\nИмя обороняющегося: %s Ходим снова: %s\n\n",getName(),p1.getName(),p2.getName(),isCanStepAgain());
        return str;
    }

    public MapColor getCardColor() {
        return cardColor;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanStepAgain()
    {
        return canStepAgain;
    }

    public boolean Purchase(Player p1)
    {
        switch (this.cardColor)
        {
            case red:
               if(p1.getOreCount()>=this.price)
               {
                   p1.addOreCount(-this.price);
                   return true;
               }
              break;

            case green:
                if(p1.getArmyCount()>=this.price)
                {
                    p1.addArmyCount(-this.price);
                    return true;
                }
                break;

            case blue:
                if(p1.getManaCount()>=this.price)
                {
                    p1.addManaCount(-this.price);
                    return true;
                }
                break;

        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{\"Card\":[{\"name\":\"%s\",\"description\":\"%s\"}]}",name,description);
    }

    public enum MapColor
    {
        red(Color.red),
        green(Color.green),
        blue(Color.blue);
        private Color color;
        MapColor(Color color){
            this.color = color;
        }
        public Color getColor(){ return color;}

        @Override
        public String toString() {
            return color.toString();
        }
    }
}

