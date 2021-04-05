package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalinfoActivity extends AppCompatActivity {
    private Button back;
    private TextView username,email,type;
    private Intent intent;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        init();
    }
    public void init(){
        username = findViewById(R.id.UserUsername);
        email = findViewById(R.id.UserEmail);
        type = findViewById(R.id.UserType);
        back = findViewById(R.id.PersonalinfoBackbtn);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        SetTextInfo();
        btnBack();
    }
    public void SetTextInfo(){
        if(user.getType().equals("Admin"))
            type.setText("משתמש מסוג - אדמין");
        else if(user.getType().equals("Teacher"))
            type.setText("משתמש מסוג - מרצה");
        else
            type.setText("משתמש מסוג - סטודנט");
        email.setText(user.getEmail()+" - אימייל");
        username.setText(user.getUsername()+" - שם משתמש");
    }
    public void btnBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getType().equals("Admin"))
                    intent = new Intent(PersonalinfoActivity.this, AdminActivity.class);
                else if (user.getType().equals("Teacher"))
                    intent = new Intent(PersonalinfoActivity.this, TeacherActivity.class);
                else
                    intent = new Intent(PersonalinfoActivity.this, StudentActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}