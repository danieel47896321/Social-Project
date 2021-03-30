package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TeacherCoursesActivity extends AppCompatActivity {
    private User user;
    private DataBase db;
    private GridView gridView;
    private Intent intent;
    private Button btnBack;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_courses);
        db = new DataBase(TeacherCoursesActivity.this);
        init();
        SetCourses();
        TeacherBack();
    }
    public void init(){
        intent = getIntent();
        btnBack = findViewById(R.id.TeacherBackBtn);
        user = (User)intent.getSerializableExtra("user");
        gridView = findViewById(R.id.teacher_gridview);
    }
    public void SetCourses(){

    }
    public void TeacherBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherCoursesActivity.this, TeacherActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}