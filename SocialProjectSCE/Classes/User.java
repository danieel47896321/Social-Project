package com.example.socialprojectsce.Classes;

import java.io.Serializable;

public class User implements Serializable {
    private String Email;
    private String Phone;
    private String Type; //0-student 1-teacher 2-admin
    private String Age;
    private String City;
    private String Sex;
    private String Depart;
    private String Firstname;
    private String Lastname;
    private String Flag;
    public User() {}

        //ctor
    public User(String email,String phone, String type, String age, String city, String sex, String depart, String firstname, String lastname, String flag) {
        Email = email;
        Phone = phone;
        Type = type;
        Age = age;
        City = city;
        Sex = sex;
        Depart = depart;
        Firstname = firstname;
        Lastname = lastname;
        Flag = flag;
    }
    //getters
    public String getEmail() { return Email; }
    public String getType() { return Type; }
    public String getAge() { return Age; }
    public String getCity() { return City; }
    public String getSex() { return Sex; }
    public String getDepart() { return Depart; }
    public String getFirstname() { return Firstname; }
    public String getLastname() { return Lastname; }
    public String getFlag() { return Flag; }
    public String getPhone() { return Phone; }

    //setters
    public void setPhone(String phone) { Phone = phone; }
    public void setEmail(String email) { Email = email; }
    public void setType(String type) { Type = type; }
    public void setAge(String age) { Age = age; }
    public void setCity(String city) { City = city; }
    public void setSex(String sex) { Sex = sex; }
    public void setDepart(String depart) { Depart = depart; }
    public void setFirstname(String firstname) { Firstname = firstname; }
    public void setLastname(String lastname) { Lastname = lastname; }
    public void setFlag(String flag) { Flag = flag; }
}
