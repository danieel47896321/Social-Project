package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {
    Button tags, logout, request, changePassword,editinfo,personalinfo;
    TextView text;
    Intent intent;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        init();
    }
    public void init(){
        tags = findViewById(R.id.btnTags);
        logout = findViewById(R.id.btnLogout);
        text = findViewById(R.id.TeacherID);
        request = findViewById(R.id.btnRequest);
        editinfo =findViewById(R.id.EdditInfo);
        changePassword = findViewById(R.id.changePassword);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        text.setText(user.getFirstname() +" "+ user.getLastname());
        text.setEnabled(false);
        personalinfo = findViewById(R.id.btnPersonalinfo);
        Logout();
        Tags();
        Request();
        Changepassword();
        EditInfo();
        PersonalInfo();
    }
    public void PersonalInfo(){
        personalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, PersonalinfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void EditInfo(){
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, EditInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, LoginActivity.class);
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
                intent = new Intent(TeacherActivity.this, ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);            }
        });
    }
    public void Request(){
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, RequestsActivity.class));
            }
        });
    }
    public void Tags(){
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, TeacherTagsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}