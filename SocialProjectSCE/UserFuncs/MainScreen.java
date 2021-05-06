package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.TagsPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainScreen extends AppCompatActivity {
    private Button buttonTags, buttonLogout, buttonRequest, buttonChangePassword, buttonEdditInfo, buttonPersonalinfo;
    private TextView welcomeText;
    private EditText personFullName;
    private Intent intent;
    private User user;
    private FirebaseAuth curUser = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        init();
    }
    public void init(){
        setID();
        CheckType();
        Logout();
        Tags();
        Request();
        Changepassword();
        EditInfo();
        PersonalInfo();
    }
    public void setID(){
        buttonTags = findViewById(R.id.buttonTags);
        buttonLogout = findViewById(R.id.buttonLogout);
        welcomeText = findViewById(R.id.welcomeText);
        personFullName = findViewById(R.id.personFullName);
        buttonRequest = findViewById(R.id.buttonRequest);
        buttonEdditInfo =findViewById(R.id.buttonEdditInfo);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        buttonPersonalinfo = findViewById(R.id.buttonPersonalinfo);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void CheckType(){
        if (user.getType().equals("Admin")) {
            welcomeText.setText("Hello Admin");
            buttonRequest.setText("Requests for review");
        }
        else if (user.getType().equals("Teacher")) {
            welcomeText.setText("Hello Teacher");
            if(user.getFlag().equals("true")) {
                buttonRequest.setText("Pending approval");
                buttonRequest.setEnabled(false);
            }
            else if(user.getFlag().equals("agree")) {
                buttonRequest.setText("Approval");
                buttonRequest.setEnabled(false);
            }
            else {
                buttonRequest.setText("Request Permissions");
                buttonRequest.setEnabled(true);
            }
        }
        else{
            welcomeText.setText("Hello Student");
            buttonRequest.setVisibility(View.INVISIBLE);
        }
        personFullName.setText( (user.getFirstname() +" "+ user.getLastname()));
    }
    public void PersonalInfo(){
        buttonPersonalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, Personalinfo.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void EditInfo(){
        buttonEdditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, EditInfo.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Changepassword(){
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, ChangePassword.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Logout(){
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curUser.signOut();
                startActivity(new Intent(MainScreen.this, Login.class));
            }
        });
    }
    public void Request(){
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getType().equals("Admin"))
                    startActivity(new Intent(MainScreen.this, Requests.class));
                if (user.getType().equals("Teacher")) {
                    if(user.getFlag().equals("false")) {
                        user.setFlag("true");
                        reference.child(user.getPhone()).setValue(user);
                        buttonRequest.setText("Pending approval");
                    }
                }
            }
        });
    }
    public void Tags(){
        buttonTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}