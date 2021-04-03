package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {
    Button course, logout, changePassword;
    TextView text;
    Intent intent;
    User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
        Logout();
        Courses();
        Changepassword();
    }
    public void init(){
        course = findViewById(R.id.btnCourse1);
        logout = findViewById(R.id.btnLogout);
        text = findViewById(R.id.StudentID);
        changePassword = findViewById(R.id.changePassword);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        text.setText(user.getUsername());
        text.setEnabled(false);
    }
    public void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, LoginActivity.class);
                user=null;
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Changepassword(){
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Courses(){
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, StudentCoursesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}