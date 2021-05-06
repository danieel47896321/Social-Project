package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;

public class Requests extends AppCompatActivity {
    private Button Back;
    private User user;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        init();
    }
    void init(){
        setID();
        Back();
    }
    public void setID(){
        Back = findViewById(R.id.Back);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void Back(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Requests.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}