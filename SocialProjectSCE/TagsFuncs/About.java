package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;

public class About extends AppCompatActivity {
    private Button back;
    private Intent intent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }
    public void init(){
        back = findViewById(R.id.Back);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        PersonalInfo();
    }
    public void PersonalInfo(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(About.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}