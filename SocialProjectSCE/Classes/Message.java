package com.example.socialprojectsce.Classes;

public class Message {



    private String Name,message, Phone,type;
    private String Date;
    private int feeling;
    public Message(String message, String date,String name) {
        this.message = message;
        this.Date = date;
        this.Name = name;
    }
    public Message() { }
    public void setName(String name) {
        this.Name = name;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return Name;
    }

    public String getMessage() {
        return message;
    }

    public String getPhone() {
        return Phone;
    }

    public String getDate() {
        return Date;
    }

    public int getFeeling() {
        return feeling;
    }

}
