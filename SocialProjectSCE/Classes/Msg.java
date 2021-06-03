package com.example.socialprojectsce.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg {
    private String Msgname;
    private String Publish;
    private String Date;
    private String Category;
    private String Text;
    private int Photo;
    public Msg() { }
    public Msg(String Msgname, String Publish,String Date,String Category,String Text) {
        this.Msgname = Msgname;
        this.Publish = Publish;
        this.Date = Date;
        this.Category = Category;
        this.Text = Text;
    }
    public int getPhoto() { return Photo; }
    public void setPhoto(int photo) { Photo = photo; }
    public String getDate() { return Date; }
    public String getCategory() { return Category; }
    public String getMsgname() { return Msgname; }
    public String getPublish() { return Publish; }
    public String getText() { return Text; }
    public void setText(String Text) {  this.Text = Text; }
    public void setMsgname(String Msgname) {  this.Msgname = Msgname; }
    public void setPublish(String Publish) {  this.Publish = Publish; }
    public void setDate(String Date) {  this.Date = Date; }
    public void setCategory(String Category) {  this.Category = Category; }
}
