package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;

public class Contact extends AppCompatActivity {
    private TextView sendMail;
    private Button buttonBack;
    private Intent intent;
    private String email="Social-Project@gmail.com";
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();
    }
    public void init(){
        setID();
        sendmail();
        Back();
    }
    void setID(){
        sendMail = findViewById(R.id.sendMail);
        buttonBack = findViewById(R.id.buttonBack);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void sendmail(){
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_SEND);
                String[] toArray = new String[1];
                toArray[0]=email;
                intent.putExtra(Intent.EXTRA_EMAIL, toArray);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contact - App Social-Project");
                intent.putExtra(Intent.EXTRA_TEXT, "Peace development team,");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email app:"));
                finish();
            }
        });
    }
    public void Back(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Contact.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}