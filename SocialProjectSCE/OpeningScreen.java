package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.socialprojectsce.Classes.Tags;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Login;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpeningScreen extends AppCompatActivity {
    private LinearLayout gotoLoginPage;
    private Intent intent;
    private View screenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        init();
    }
    public void init() {

        gotoLoginPage = findViewById(R.id.gotoLoginPage);
        Login();
    }
    public void Login() {
        gotoLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OpeningScreen.this, Login.class);
                startActivity(intent);
            }
        });
    }
}