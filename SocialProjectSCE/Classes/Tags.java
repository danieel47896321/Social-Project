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

    public int getThumbnail() { return Photo; }
    public String getText() { return Tagname; }
    public String getCategory() { return Category; }
    public void setText(String tagname) {  this.Tagname = tagname; }
    public void setCategory(String category) {  this.Category = category; }
    public void setThumbnail(int photo) {  this.Photo = photo; }

}
