package com.webcheckers.util;

import com.webcheckers.model.Player;

public class PlayerMessage {

    private Player sender;
    private Player recipient;
    private String message;
    private MessageType messageType;


    public enum MessageType {MESSAGE,NEW_GAME_REQUEST,REQUEST_ACCEPTED,REQUEST_DENIED}

    public PlayerMessage(Player sender, Player recipient, String message, MessageType messageType) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.messageType = messageType;
    }

    public PlayerMessage(Player sender, Player recipient, MessageType messageType) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageType = messageType;
    }

    public static PlayerMessage getNewGameRequest(Player sender, Player recipient){
        return new PlayerMessage(sender,recipient, MessageType.NEW_GAME_REQUEST);
    }

    public Player getSender() {
        return sender;
    }

    public Player getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}


