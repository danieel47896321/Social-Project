package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class AdminTagsActivity extends AppCompatActivity {
    private User user;
    private DataBase db;
    private GridView gridView;
    private Button btnBack;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tags);
        db = new DataBase(AdminTagsActivity.this);
        init();
        SetCourses();
    }
    public void init(){
        intent = getIntent();
        btnBack = findViewById(R.id.AdminBackBtn);
        user = (User)intent.getSerializableExtra("user");
        gridView = findViewById(R.id.admin_gridview);
    }
    public void SetCourses(){

    }
    public void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminTagsActivity.this, AdminActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}