package com.Oberon1989;

import com.Oberon1989.entites.Map;
import com.Oberon1989.entites.playerTransfer;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class GameState {
    public String action;
    public playerTransfer[] message;
    public String cardAction;
    public Map turnMap;
    boolean isDrop;

    public GameState(String action, playerTransfer[] message,String cardAction,Map turnMap,boolean isDrop) {
        this.action = action;
        this.message = message;
        this.cardAction=cardAction;
        this.turnMap=turnMap;
        this.isDrop=isDrop;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public playerTransfer[] getMessage() {
        return message;
    }

    public void setMessage(playerTransfer[] message) {
        this.message = message;
    }

    public String getCardAction() {
        return cardAction;
    }

    public void setCardAction(String cardAction) {
        this.cardAction = cardAction;
    }

    public Map getTurnMap() {
        return turnMap;
    }

    public void setTurnMap(Map turnMap) {
        this.turnMap = turnMap;
    }

    public boolean getIsDrop() {
        return isDrop;
    }

    public void setIsDrop(boolean drop) {
        isDrop = drop;
    }
}
