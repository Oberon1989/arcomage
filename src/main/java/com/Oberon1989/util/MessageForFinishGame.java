package com.Oberon1989.util;

public class MessageForFinishGame {
    private String messageVin;
    private String messageLose;

    public MessageForFinishGame(String messageVin, String messageLose) {
        this.messageVin = "{\"action\":\"vin\",\"message\":\"" + messageVin + "\"}";
        this.messageLose = "{\"action\":\"lose\",\"message\":\""+ messageLose + "\"}";
    }

    public String getMessageVin() {
        return messageVin;
    }

    public String getMessageLose() {
        return messageLose;
    }
}
