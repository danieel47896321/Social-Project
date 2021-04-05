package com.example.socialprojectsce;

import java.io.Serializable;

public class User implements Serializable {
    private String Username;
    private String Email;
    private String Password;
    private String Type; //0-student 1-teacher 2-admin
    private String Age;
    private String City;
    private String Sex;
    private String Depart;
    private String Firstname;
    private String Lastname;
    //ctor
    public User(String username, String email, String password, String type, String age, String city, String sex, String depart, String firstname, String lastname) {
        Username = username;
        Email = email;
        Password = password;
        Type = type;
        Age = age;
        City = city;
        Sex = sex;
        Depart = depart;
        Firstname = firstname;
        Lastname = lastname;
    }
    public User() { }
    //getters
    public String getUsername() { return Username; }
    public String getEmail() { return Email; }
    public String getPassword() { return Password; }
    public String getType() { return Type; }
    public String getAge() { return Age; }
    public String getCity() { return City; }
    public String getSex() { return Sex; }
    public String getDepart() { return Depart; }
    public String getFirstname() { return Firstname; }
    public String getLastname() { return Lastname; }
    //setters
    public void setUsername(String username) { Username = username; }
    public void setEmail(String email) { Email = email; }
    public void setPassword(String password) { Password = password; }
    public void setType(String type) { Type = type; }
    public void setAge(String age) { Age = age; }
    public void setCity(String city) { City = city; }
    public void setSex(String sex) { Sex = sex; }
    public void setDepart(String depart) { Depart = depart; }
    public void setFirstname(String firstname) { Firstname = firstname; }
    public void setLastname(String lastname) { Lastname = lastname; }
    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Type='" + Type + '\'' +
                ", Age='" + Age + '\'' +
                ", City='" + City + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Depart='" + Depart + '\'' +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                '}';
    }
}
