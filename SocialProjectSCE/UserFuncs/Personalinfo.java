package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;

public class Personalinfo extends AppCompatActivity {
    private Button Back;
    private TextView  UserEmail, UserType,UserPhone;
    private Intent intent;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        init();
    }
    public void init(){
        setID();
        SetTextInfo();
        btnBack();
    }
    public void setID(){
        UserEmail = findViewById(R.id.UserEmail);
        UserType = findViewById(R.id.UserType);
        UserPhone = findViewById(R.id.UserPhone);
        Back = findViewById(R.id.Back);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void SetTextInfo(){
        if(user.getType().equals("Admin"))
            UserType.setText("Type of User:  Admin");
        else if(user.getType().equals("Teacher")) {
            if(user.getFlag().equals("true"))
                UserType.setText("Type of User:  Teacher (with Permission)");
            else
                UserType.setText("Type of User:  Teacher (with out Permission)");
        }
        else
            UserType.setText("Type of User:  Student");
        UserEmail.setText("Email:  " + user.getEmail());
        UserPhone.setText("Phone:  " + user.getPhone());
    }
    public void btnBack(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Personalinfo.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}