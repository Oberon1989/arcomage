package com.Oberon1989.util;

import com.Oberon1989.entites.Map;

import java.util.ArrayList;
import java.util.Random;


public class CardDeck {
    private ArrayList<Map> cards;
    public CardDeck(ArrayList<Map> cards)
    {
        this.cards=cards;
    }

    public int size()
    {
        return cards.size();
    }
    public Map get(int index)
    {
        return cards.get(index);
    }

    public Map pop()
    {
        Map tmp = cards.get(0);
        cards.remove(0);
        return tmp;
    }
    public void push(Map card)
    {
        cards.add(cards.size()-1,card);
    }

    public void shuffleCards()
    {
        Random rnd = new Random();
       ArrayList<Map> tmp = getWhiteStack();

        int position=0;

        for (int i = 0; i < cards.size(); i++) {
            do {
                position = rnd.nextInt(tmp.size());
            }
            while (tmp.get(position)!=null);
            tmp.set(position,cards.get(i));
        }
        cards=tmp;
    }

    private ArrayList<Map> getWhiteStack()
    {
        ArrayList<Map> tmp = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            tmp.add(null);
        }
        return tmp;
    }
}
