package com.example.socialprojectsce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class StudentCoursesActivity extends AppCompatActivity {
    private User user;
    private DataBase db;
    private Button btnBack;
    private RecyclerView viewList;
    private Intent intent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);
        init();
        SetCourses();
        StudentBack();
    }
    public void init(){
        intent = getIntent();
        btnBack = findViewById(R.id.StudentBackBtn);
        user = (User)intent.getSerializableExtra("user");
        viewList = findViewById(R.id.viewList);
        viewList.setLayoutManager(new LinearLayoutManager(this));

        db = new DataBase(StudentCoursesActivity.this);

    }
    public void SetCourses(){

    }
    public void StudentBack(){
    }
}

