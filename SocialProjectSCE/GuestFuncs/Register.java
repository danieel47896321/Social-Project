package com.example.socialprojectsce.GuestFuncs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.UserFuncs.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private static String type = "Student";
    private static String sex = "Male";
    private EditText inputEmail,inputPassword,inputConfirmPassword, inputFirstname, inputLastname,inputCity, inputDepart,age,inputPhone;
    private TextView alreadyHaveAccount;
    private Button register;
    private CheckBox Student, Teacher, Male, Female;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance() ;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    void init(){
        setID();
        StudentPick();
        TeacherPick();
        MalePick();
        FemalePick();
        AlreadyHaveAccount();
        RegisterClick();
    }
    public void setID(){
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        inputPhone = findViewById(R.id.inputPhone);
        inputFirstname = findViewById(R.id.inputFirstname);
        inputLastname = findViewById(R.id.inputLastname);
        inputCity = findViewById(R.id.inputCity);
        inputDepart = findViewById(R.id.inputDepart);
        age = findViewById(R.id.Age);
        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        Student = findViewById(R.id.Student);
        Teacher = findViewById(R.id.Teacher);
        register = findViewById(R.id.buttonRegister);
        Student.setChecked(true);
        Male.setChecked(true);
    }
    public void MalePick(){
        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Female.setChecked(false);
                sex = "Male";
            }
        });
    }
    public void FemalePick(){
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Male.setChecked(false);
                sex = "Female";
            }
        });
    }
    public void StudentPick(){
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher.setChecked(false);
                type = "Student";
            }
        });
    }
    public void TeacherPick(){
        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student.setChecked(false);
                type = "Teacher";
            }
        });
    }
    public void AlreadyHaveAccount(){
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
    public void RegisterClick(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEmail.length()>10 && inputPassword.length()>5 && inputConfirmPassword.length()>5 && inputFirstname.length()>1 && inputLastname.length()>1 && inputCity.length()>1 && inputDepart.length()>1 && age.length()>1) {
                    if (inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(inputEmail.getText().toString(),inputPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    User user = new User(inputEmail.getText().toString(),inputPhone.getText().toString(), type,age.getText().toString(),inputCity.getText().toString(),sex, inputDepart.getText().toString(), inputFirstname.getText().toString(), inputLastname.getText().toString(),"false");
                                    reference.child(user.getPhone()).setValue(user);
                                    startActivity(new Intent(Register.this, Login.class));
                                    finish();
                                }else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(Register.this, "Error: " +errorMessage, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else
                        Toast.makeText(Register.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if(inputEmail.length()<11)
                    Toast.makeText(Register.this, "Email must have at least 11 leatters", Toast.LENGTH_LONG).show();
                else if(inputPassword.length()<4)
                    Toast.makeText(Register.this, "Password must have at least 6 leatters ", Toast.LENGTH_LONG).show();
                else if(inputConfirmPassword.length()<4)
                    Toast.makeText(Register.this, "Confirm Password must have at least 6 leatters ", Toast.LENGTH_LONG).show();
                else if(inputFirstname.length()<2)
                    Toast.makeText(Register.this, "Firstname must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(inputLastname.length()<2)
                    Toast.makeText(Register.this, "Lastname must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(inputCity.length()<2)
                    Toast.makeText(Register.this, "City must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(inputDepart.length()<2)
                    Toast.makeText(Register.this, "Depart must have at least 2 leatters ", Toast.LENGTH_LONG).show();
                else if(age.length()<2)
                    Toast.makeText(Register.this, "Age must have at least 2 leatters ", Toast.LENGTH_LONG).show();
            }
        });
    }
}