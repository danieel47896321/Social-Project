package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button back, resetpassword;
    private EditText email,username;
    private TextView register;
    private DataBase db;
    private Intent intent;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        db = new DataBase(ForgotPasswordActivity.this);
        init();
    }
    void init(){
        back = findViewById(R.id.btnBack);
        resetpassword = findViewById(R.id.btnAgreeResetPassword);
        register = findViewById(R.id.textViewSingUp);
        email = findViewById(R.id.EmailToResetPassword);
        username = findViewById(R.id.usernameToReset);
        Back();
        ResetPassword();
        Register();
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void ResetPassword(){
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.length()>10 && username.length()>5) {
                    try{
                        Cursor cursor = db.getData();
                        if (cursor.moveToFirst()) {
                            do {
                                String USERNAME = cursor.getString(cursor.getColumnIndex("username"));
                                String EMAIL = cursor.getString(cursor.getColumnIndex("email"));
                                String PASSWORD = cursor.getString(cursor.getColumnIndex("password"));
                                String TYPE = cursor.getString(cursor.getColumnIndex("type"));
                                if (email.getText().toString().equals(EMAIL) && username.getText().toString().equals(USERNAME)) {
                                    user = new User(USERNAME,EMAIL,PASSWORD,TYPE);
                                    cursor.close();
                                    db.close();
                                    intent = new Intent(ForgotPasswordActivity.this, SetNewPasswordActivity.class);
                                    intent.putExtra("user", user);
                                    cursor.close();
                                    db.close();
                                    startActivity(intent);
                                    return;
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                            db.close();
                            Toast.makeText(ForgotPasswordActivity.this, "Wrong username or email", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ForgotPasswordActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                }
                else if(username.length()<6)
                    Toast.makeText(ForgotPasswordActivity.this, "Username is too short", Toast.LENGTH_LONG).show();
                else if(email.length()<10)
                    Toast.makeText(ForgotPasswordActivity.this, "Email is too short", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Register(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ForgotPasswordActivity.this, RegisterActivity.class);
                user=null;
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                user=null;
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}