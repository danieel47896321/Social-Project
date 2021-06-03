package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    private View screenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }
    public void init(){
        back = findViewById(R.id.Back);
        screenView = findViewById(R.id.rView);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        if(user.getBackground().equals("background"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
        else if(user.getBackground().equals("background1"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
        else if(user.getBackground().equals("background2"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
        else if(user.getBackground().equals("background3"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
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