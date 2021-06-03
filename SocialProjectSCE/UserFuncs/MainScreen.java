package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.Msg;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.TagsPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    private Button buttonTags, buttonLogout, buttonRequest, buttonChangePassword, buttonEdditInfo, buttonPersonalinfo,DayBackground,NightBackground, AdminBackground, TeacherBackground;
    private TextView welcomeText;
    private EditText personFullName;
    private Intent intent;
    private View screenView;
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
        intent = getIntent();
        setID();
        CheckType();
        Logout();
        Tags();
        Request();
        Changepassword();
        EditInfo();
        PersonalInfo();
        DayColor();
        NightColor();
        TeacherBackground();
        AdminBackground();
    }
    public void setID(){
        screenView = findViewById(R.id.rView);
        TeacherBackground = findViewById(R.id.TeacherBackground);
        AdminBackground = findViewById(R.id.AdminBackground);
        user = (User)intent.getSerializableExtra("user");
        if(user.getBackground().equals("background"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
        else if(user.getBackground().equals("background1"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
        else if(user.getBackground().equals("background2"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
        else if(user.getBackground().equals("background3"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
        buttonTags = findViewById(R.id.buttonTags);
        buttonLogout = findViewById(R.id.buttonLogout);
        DayBackground = findViewById(R.id.DayBackground);
        NightBackground = findViewById(R.id.NightBackground);
        welcomeText = findViewById(R.id.welcomeText);
        personFullName = findViewById(R.id.personFullName);
        buttonRequest = findViewById(R.id.buttonRequest);
        buttonEdditInfo =findViewById(R.id.buttonEdditInfo);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        buttonPersonalinfo = findViewById(R.id.buttonPersonalinfo);
    }
    public void CheckType(){
        if(user!=null) {
            if (user.getType().equals("Admin")) {
                welcomeText.setText("Hello Admin");
                buttonRequest.setText("Requests for review");
            } else if (user.getType().equals("Teacher")) {
                welcomeText.setText("Hello Teacher");
                AdminBackground.setVisibility(View.INVISIBLE);
                if (user.getFlag().equals("true")) {
                    buttonRequest.setText("Pending approval");
                    buttonRequest.setEnabled(false);

                } else if (user.getFlag().equals("agree")) {
                    buttonRequest.setText("Approval");
                    buttonRequest.setEnabled(false);
                } else {
                    buttonRequest.setText("Request Permissions");
                    buttonRequest.setEnabled(true);
                }
            } else {
                welcomeText.setText("Hello Student");
                AdminBackground.setVisibility(View.INVISIBLE);
                TeacherBackground.setVisibility(View.INVISIBLE);
                buttonRequest.setVisibility(View.INVISIBLE);
            }
            personFullName.setText((user.getFirstname() + " " + user.getLastname()));
        }
    }
    public void DayColor(){
        DayBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
                user.setBackground("background1");
                reference.child(user.getPhone()).child("background").setValue("background1");
            }
        });
    }
    public void NightColor(){
        NightBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
                user.setBackground("background");
                reference.child(user.getPhone()).child("background").setValue("background");
            }
        });
    }
    public void TeacherBackground(){
        TeacherBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
                user.setBackground("background2");
                reference.orderByChild("background").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<User> users = new ArrayList<User>();
                        User temp = new User();
                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                            temp = new User();
                            temp.setPhone(datas.child("phone").getValue().toString());
                            users.add(temp);
                        }
                        for(int i=0;i<users.size();i++){
                            users.get(i).setBackground("background2");
                            reference.child(users.get(i).getPhone()).child("background").setValue("background2");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }
        });
    }
    public void AdminBackground(){
        AdminBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
                user.setBackground("background3");
                reference.orderByChild("background").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<User> users = new ArrayList<User>();
                        User temp = new User();
                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                            temp = new User();
                            temp.setPhone(datas.child("phone").getValue().toString());

                            users.add(temp);
                        }
                        for(int i=0;i<users.size();i++){
                            users.get(i).setBackground("background3");
                            reference.child(users.get(i).getPhone()).child("background").setValue("background3");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }
        });
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
                if (user.getType().equals("Admin")) {
                    intent = new Intent(MainScreen.this, Requests.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                if (user.getType().equals("Teacher")) {
                    if(user.getFlag().equals("false")) {
                        user.setFlag("true");
                        reference.child(user.getPhone()).setValue(user);
                        buttonRequest.setText("Pending approval");
                        finish();
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference().child("Requests");
                        ref.child(user.getPhone()).setValue(user);
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