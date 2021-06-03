package com.example.socialprojectsce.GuestFuncs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.UserFuncs.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private TextView forgotPassword, SingUp;
    private EditText inputEmail, inputPassword;
    private Button buttonLogin;
    private Intent intent;
    private User user = new User();
    private FirebaseAuth myAuth = FirebaseAuth.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
    FirebaseUser curUser = myAuth.getCurrentUser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }
    public void init(){
        setID();
        ForgotPassword();
        Signup();
        Login();
    }
    public void setID(){
        SingUp = findViewById(R.id.SingUp);
        forgotPassword = findViewById(R.id.forgotPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }
    public void ForgotPassword(){
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgetPassword.class));
            }
        });
    }
    public void Signup(){
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    public void Login(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEmail.length()>0 && inputPassword.length()>0) {
                    myAuth.signInWithEmailAndPassword(inputEmail.getText().toString(),inputPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                reference.orderByChild("email").equalTo(inputEmail.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                                            user.setAge(datas.child("age").getValue().toString());
                                            user.setCity(datas.child("city").getValue().toString());
                                            user.setDepart(datas.child("depart").getValue().toString());
                                            user.setEmail(datas.child("email").getValue().toString());
                                            user.setFirstname(datas.child("firstname").getValue().toString());
                                            user.setFlag(datas.child("flag").getValue().toString());
                                            user.setLastname(datas.child("lastname").getValue().toString());
                                            user.setPhone(datas.child("phone").getValue().toString());
                                            user.setSex(datas.child("sex").getValue().toString());
                                            user.setType(datas.child("type").getValue().toString());
                                            user.setBackground(datas.child("background").getValue().toString());
                                        }
                                        intent = new Intent(Login.this, MainScreen.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                        finish();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) { }
                                });
                            } else{
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(Login.this, errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}