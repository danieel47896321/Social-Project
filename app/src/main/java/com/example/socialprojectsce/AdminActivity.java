package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    Button course, logout, request, changePassword;
    TextView text;
    Intent intent;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        Logout();
        Courses();
        Request();
        Changepassword();
    }
    public void init(){
        course = findViewById(R.id.btnCourse1);
        logout = findViewById(R.id.btnLogout);
        text = findViewById(R.id.HeyText);
        request = findViewById(R.id.btnRequest);
        changePassword = findViewById(R.id.changePassword);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        text.setText( "שלום אדמין: "+user.getUsername());
    }
    public void Logout(){
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminActivity.this, ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Changepassword(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });
    }
    public void Request(){
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, RequestsActivity.class));
            }
        });
    }
    public void Courses(){
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminActivity.this, AdminCoursesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);            }
        });
    }
}