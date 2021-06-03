package com.example.socialprojectsce.UserFuncs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private EditText currentPassword, newPassword, confirmnewPassword;
    private Button buttonSaveChanges, buttonCancel;
    private Intent intent;
    private View screenView;
    private User user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();
    private String Password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }
    public void init(){
        setID();
        Cancel();
        SaveChanges();
    }
    public void setID(){
        screenView = findViewById(R.id.rView);
        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmnewPassword = findViewById(R.id.confirmnewPassword);
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonCancel = findViewById(R.id.buttonCancel);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        if(user.getBackground().equals("background"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
        else if(user.getBackground().equals("background1"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
        else if(user.getBackground().equals("background2"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
        else if(user.getBackground().equals("background3"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
    }
    public void SaveChanges(){
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPassword.length()>5 && newPassword.length()>5 && confirmnewPassword.length()>5){
                        mAuth.signInWithEmailAndPassword(user.getEmail(),currentPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                        if(newPassword.getText().toString().equals(confirmnewPassword.getText().toString())){
                                            try {
                                                currentUser.updatePassword(newPassword.getText().toString());
                                                intent = new Intent(ChangePassword.this, MainScreen.class);
                                                Toast.makeText(ChangePassword.this, "Password Changed", Toast.LENGTH_LONG).show();
                                                intent.putExtra("user", user);
                                                startActivity(intent);
                                                finish();
                                            }
                                            catch (Exception e){
                                                Toast.makeText(ChangePassword.this, "failed", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else
                                            Toast.makeText(ChangePassword.this, "new password not equals to confirm password", Toast.LENGTH_LONG).show();
                                }
                                else
                                    Toast.makeText(ChangePassword.this, "wrong current password try again", Toast.LENGTH_LONG).show();
                            }
                        });
                }
                else if(currentPassword.length()<6)
                    Toast.makeText(ChangePassword.this, "cur password most have 6 or more chars", Toast.LENGTH_LONG).show();
                else if(newPassword.length()<6)
                    Toast.makeText(ChangePassword.this, "new password most have 6 or more chars", Toast.LENGTH_LONG).show();
                else if(confirmnewPassword.length()<6)
                    Toast.makeText(ChangePassword.this, "confirm password most have 6 or more chars", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Cancel(){
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ChangePassword.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}