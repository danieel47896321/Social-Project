package com.example.socialprojectsce.GuestFuncs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialprojectsce.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ForgetPassword extends AppCompatActivity {
    private Button buttonBack, buttonResetPassword;
    private EditText inputEmail;
    private TextView SingUp;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        init();
    }
    void init(){
        setID();
        Back();
        ResetPassword();
        Register();
    }
    public void setID(){
        buttonBack = findViewById(R.id.buttonBack);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        SingUp = findViewById(R.id.SingUp);
        inputEmail = findViewById(R.id.inputEmail);
        intent = getIntent();
    }
    public void ResetPassword(){
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEmail.length()>9) {
                    String Email = inputEmail.getText().toString();
                    Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(Email);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                mAuth.sendPasswordResetEmail(Email);
                                startActivity(new Intent(ForgetPassword.this, Login.class));
                                Toast.makeText(ForgetPassword.this, "Check your Email for Password Reset Link", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else
                                Toast.makeText(ForgetPassword.this, "Email not Exists", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });
                }
                else if(inputEmail.length()<10)
                    Toast.makeText(ForgetPassword.this, "Wrong Email", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Register(){
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, Register.class));
            }
        });
    }
    public void Back(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, Login.class));
            }
        });
    }
}