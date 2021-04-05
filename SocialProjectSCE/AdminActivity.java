package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    private Button tags, logout, request, changePassword,editinfo,personalinfo;
    private TextView text;
    private Intent intent;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }
    public void init(){
        tags = findViewById(R.id.btnTags);
        logout = findViewById(R.id.btnLogout);
        text = findViewById(R.id.HeyText);
        request = findViewById(R.id.btnRequest);
        editinfo =findViewById(R.id.EdditInfo);
        changePassword = findViewById(R.id.changePassword);
        personalinfo = findViewById(R.id.btnPersonalinfo);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        text.setText( (user.getFirstname() +" "+ user.getLastname()));
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
                intent = new Intent(AdminActivity.this, PersonalinfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void EditInfo(){
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminActivity.this, EditInfoActivity.class);
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
                intent = new Intent(AdminActivity.this, ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminActivity.this, LoginActivity.class);
                user=null;
                intent.putExtra("user", user);
                startActivity(intent);
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
    public void Tags(){
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminActivity.this, AdminTagsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);            }
        });
    }
}