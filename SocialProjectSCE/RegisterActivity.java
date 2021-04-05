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
    private static String sex = "Male";
    private EditText username,email,password,confirmpassword,firstname,lastname,city,depart,age;
    private TextView alreadyHaveAccount;
    private Button register;
    private CheckBox student,teacher,male,female;
    private DataBase db;
    private User user;
    private Intent intent;

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
        firstname = findViewById(R.id.RegFirstname);
        lastname = findViewById(R.id.RegLastame);
        city = findViewById(R.id.RegCity);
        depart = findViewById(R.id.RegDepart);
        age = findViewById(R.id.RegAge);
        male = findViewById(R.id.RegMale);
        female = findViewById(R.id.RegFemale);
        student.setChecked(true);
        male.setChecked(true);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        StudentPick();
        TeacherPick();
        MalePick();
        FemalePick();
        AlreadyHaveAccount();
        RegisterClick();
    }
    public void MalePick(){
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(false);
                sex = "Male";
            }
        });
    }

    public void FemalePick(){
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                sex = "Female";
            }
        });
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
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                user=null;
                intent.putExtra("user", user);
                startActivity(intent);
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
                if(username.length()>5 && password.length()>5 && confirmpassword.length()>5 && email.length()>10 && firstname.length()>1 && lastname.length()>1 && city.length()>1 && depart.length()>1 && age.length()>1) {
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        if (CheckExsists() == 0) {
                            try {
                                db.insert(username.getText().toString(), email.getText().toString(), password.getText().toString(), type,age.getText().toString(),city.getText().toString(),sex,depart.getText().toString(),firstname.getText().toString(),lastname.getText().toString());
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
                else if(firstname.length()<2)
                    Toast.makeText(RegisterActivity.this, "Firstname must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(lastname.length()<2)
                    Toast.makeText(RegisterActivity.this, "Lastname must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(city.length()<2)
                    Toast.makeText(RegisterActivity.this, "City must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(depart.length()<2)
                    Toast.makeText(RegisterActivity.this, "Depart must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(age.length()<2)
                    Toast.makeText(RegisterActivity.this, "Age must have at least 2 leatters ", Toast.LENGTH_LONG).show();
            }
        });
    }
}