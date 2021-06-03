package com.example.socialprojectsce.Classes;

public class Tags {
    private String Tagname;
    private String Category;
    private int Photo;
    public Tags() { }
    public Tags(String tagname, String category,int photo) {
        this.Tagname = tagname;
        this.Category = category;
        this.Photo = photo;
    }

    public int getPhoto() { return Photo; }
    public String getText() { return Tagname; }
    public String getCategory() { return Category; }
    public void setTagname(String tagname) {  this.Tagname = tagname; }
    public void setCategory(String category) {  this.Category = category; }
    public void setPhoto(int photo) {  this.Photo = photo; }

}
