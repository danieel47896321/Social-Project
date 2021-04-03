package com.example.socialprojectsce;

import java.io.Serializable;

public class User implements Serializable {
    private String Username;
    private String Email;
    private String Password;
    private String Type; //0-student 1-teacher
    //ctor
    public User(String username, String email, String password, String type)  {
        Username = username;
        Email = email;
        Password = password;
        Type = type;
    }
    public User() { }
    //getters
    public String getUsername() { return Username; }
    public String getEmail() { return Email; }
    public String getPassword() { return Password; }
    public String getType() { return Type; }
    //setters
    public void setUsername(String username) { Username = username; }
    public void setEmail(String email) { Email = email; }
    public void setPassword(String password) { Password = password; }
    public void setType(String type) { Type = type; }
    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Flag=" + Type +
                '}';
    }
}
