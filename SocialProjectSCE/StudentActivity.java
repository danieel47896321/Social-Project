package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {
    Button tags, logout, changePassword,editinfo,personalinfo;
    TextView text;
    Intent intent;
    User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
    }
    public void init(){
        tags = findViewById(R.id.btnTags);
        logout = findViewById(R.id.btnLogout);
        text = findViewById(R.id.StudentID);
        editinfo =findViewById(R.id.btnEdditInfo);
        changePassword = findViewById(R.id.changePassword);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        text.setText(user.getFirstname() +" "+ user.getLastname());
        text.setEnabled(false);
        personalinfo = findViewById(R.id.Personinfo);
        Logout();
        Tags();
        Changepassword();
        EditInfo();
        PersonalInfo();
    }
    public void PersonalInfo(){
        personalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, PersonalinfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void EditInfo(){
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, EditInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
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
    public void Tags(){
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StudentActivity.this, StudentTagsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}