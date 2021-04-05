package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentTagsActivity extends AppCompatActivity {
    private User user;
    private DataBase db;
    private Button btnBack;
    private RecyclerView viewList;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tags);
        init();
        //SetCourses();
        StudentBack();
    }
    public void init(){
        btnBack = findViewById(R.id.StudentBackBtn);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        db = new DataBase(StudentTagsActivity.this);
    }
    public void SetCourses(){

    }
    public void StudentBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentTagsActivity.this, StudentActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}

