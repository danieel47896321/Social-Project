package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private static String type = "Student";
    private EditText username,email,password,confirmpassword;
    private TextView alreadyHaveAccount;
    private Button register;
    private CheckBox student,teacher;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DataBase(RegisterActivity.this);
        init();
    }
    void init(){
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        username = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputUsername2);
        password = findViewById(R.id.inputPassword);
        confirmpassword = findViewById(R.id.inputConfirmPassword);
        register = findViewById(R.id.buttonRegister);
        student = findViewById(R.id.Student);
        teacher = findViewById(R.id.Teacher);
        student.setChecked(true);
        StudentPick();
        TeacherPick();
        AlreadyHaveAccount();
        RegisterClick();
    }

    public void StudentPick(){
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher.setChecked(false);
                type = "Student";
            }
        });
    }

    public void TeacherPick(){
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setChecked(false);
                type = "Teacher";
            }
        });
    }

    public void AlreadyHaveAccount(){
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    public int CheckExsists(){ // 0-not exists 1-username exisists 2-email exists 3-username and email exists
        Cursor cursor = db.getData();
        int username_exists=0, email_exists=0;
        if (cursor.moveToFirst())
            do{
                String USERNAME = cursor.getString(cursor.getColumnIndex("username"));
                String EMAIL = cursor.getString(cursor.getColumnIndex("email"));
                if(username.getText().toString().equals(USERNAME))
                    username_exists = 1;
                if(email.getText().toString().equals(EMAIL))
                    email_exists = 2;
            }while(cursor.moveToNext());
        cursor.close();
        if(username_exists == 1 && email_exists==2)
            return 3;
        else if(email_exists == 1)
            return 2;
        else if(username_exists == 1)
            return 1;
        return 0;
    }
    public void RegisterClick(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.length()>5 && password.length()>5 && confirmpassword.length()>5 && email.length()>10 ) {
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        if (CheckExsists() == 0) {
                            try {
                                db.insert(username.getText().toString(), email.getText().toString(), password.getText().toString(), type);
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                            catch (Exception e){
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else if (CheckExsists() == 1)
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_LONG).show();
                        else if (CheckExsists() == 2)
                            Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_LONG).show();
                        else if (CheckExsists() == 3)
                            Toast.makeText(RegisterActivity.this, "Username and Email already exists", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if(username.length()<6)
                    Toast.makeText(RegisterActivity.this, "Username must have at least 6 leatters", Toast.LENGTH_LONG).show();
                else if(email.length()<11)
                    Toast.makeText(RegisterActivity.this, "Email must have at least 11 leatters", Toast.LENGTH_LONG).show();
                else if(password.length()<6)
                    Toast.makeText(RegisterActivity.this, "Password must have at least 6 leatters ", Toast.LENGTH_LONG).show();
                else if(confirmpassword.length()<6)
                    Toast.makeText(RegisterActivity.this, "Confirm Password must have at least 6 leatters ", Toast.LENGTH_LONG).show();
            }
        });
    }
}