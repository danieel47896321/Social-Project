package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;

public class Updates extends AppCompatActivity {
    private TextView update;
    private Button back;
    private User user;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        init();

    }
    public void init(){
        setID();
        back();
    }
    void setID(){
        back = findViewById(R.id.Back);
        update = findViewById(R.id.NewsUpdate);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Updates.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}