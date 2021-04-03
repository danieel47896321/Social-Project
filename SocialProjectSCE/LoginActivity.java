package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView forgotpassword,signup;
    private EditText username,password;
    private Button login;
    private DataBase db;
    private User user;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(LoginActivity.this);
        init();
    }

    public void init(){
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.textViewSingUp);
        forgotpassword = findViewById(R.id.textForgotPassword);
        username = findViewById(R.id.inputUsername2);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.buttonLogin);
        ForgotPassword();
        Signup();
        Login();
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }

    public void ForgotPassword(){
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }
    public void Signup(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
    public void Login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.length()>5 && password.length()>5) {
                    Boolean Flag = false;
                    try {
                        Cursor cursor = db.getData();
                        if (cursor.moveToFirst()) {
                            while (cursor.moveToNext()){
                                String user = cursor.getString(cursor.getColumnIndex("username"));
                                String pass = cursor.getString(cursor.getColumnIndex("password"));
                                String email = cursor.getString(cursor.getColumnIndex("email"));
                                String type = cursor.getString(cursor.getColumnIndex("type"));
                                if (username.getText().toString().equals(user) && password.getText().toString().equals(pass)) {
                                    cursor.close();
                                    Flag = true;
                                    User USER = new User(user, email, pass, type);
                                    if (type.equals("Admin"))
                                        intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    else if (type.equals("Teacher"))
                                        intent = new Intent(LoginActivity.this, TeacherActivity.class);
                                    else if(type.equals("Student"))
                                        intent = new Intent(LoginActivity.this, StudentActivity.class);
                                    intent.putExtra("user", USER);
                                    cursor.close();
                                    db.close();
                                    startActivity(intent);
                                    break;
                                }
                            }
                            if(!Flag) {
                                cursor.close();
                                db.close();
                            }
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                    if(!Flag)
                        Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                }
                else if(username.length()<6)
                    Toast.makeText(LoginActivity.this, "Username must have at least 6 leatters", Toast.LENGTH_LONG).show();
                else if(password.length()<6)
                    Toast.makeText(LoginActivity.this, "Password must have at least 6 leatters", Toast.LENGTH_LONG).show();
            }
        });
    }
}