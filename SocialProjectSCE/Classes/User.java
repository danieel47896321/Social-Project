package com.example.socialprojectsce.Classes;

import com.example.socialprojectsce.R;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @Override
    public String toString() {
        return "User{" +
                "Photo=" + Photo +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Type='" + Type + '\'' +
                ", Age='" + Age + '\'' +
                ", City='" + City + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Depart='" + Depart + '\'' +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Flag='" + Flag + '\'' +
                ", Study='" + Study + '\'' +
                ", Year='" + Year + '\'' +
                ", Semester='" + Semester + '\'' +
                '}';
    }

    private int Photo = R.drawable.ic_student;;
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
    private String Study=null;
    private String Year=null;
    private String Semester=null;
    private String background="background";
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
    public String getStudy() { return Study; }
    public String getYear() { return Year; }
    public String getSemester() { return Semester; }
    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }
    public int getPhoto() { return Photo; }
    public void setPhoto(int photo) { Photo = photo; }
    public void setStudy(String study) { Study = study; }
    public void setYear(String year) { Year = year; }
    public void setSemester(String semester) { Semester = semester; }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return o.equals(Email) &&
                o.equals(Phone) &&
                o.equals(Type) &&
                o.equals(Age) &&
                o.equals(City) &&
                o.equals(Sex) &&
                o.equals(Depart) &&
                o.equals(Firstname) &&
                o.equals(Lastname) &&
                o.equals(Flag);
    }
}
