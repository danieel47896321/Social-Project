package com.example.socialprojectsce.Classes;

public class Message {



    private String messageID,message,senderID;
    private long timestamp;
    private int feeling;
    public Message(String message, String senderID, long timestamp) {
        this.message = message;
        this.senderID = senderID;
        this.timestamp = timestamp;
    }
    public Message() { }
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }


    public String getMessageID() {
        return messageID;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderID() {
        return senderID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getFeeling() {
        return feeling;
    }

}
