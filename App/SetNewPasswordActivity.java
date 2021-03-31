package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetNewPasswordActivity extends AppCompatActivity {
    private EditText password,confirmpassword;
    private Button agree,cancel;
    private User user;
    private Intent intent;
    private DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        db = new DataBase(SetNewPasswordActivity.this);
        init();
    }
    public void init(){
        password = findViewById(R.id.editTextPassword);
        confirmpassword = findViewById(R.id.editTextConfirmPassword);
        agree = findViewById(R.id.buttonAgree);
        cancel = findViewById(R.id.buttonCancel);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        Cancel();
        SaveChanges();
    }
    public void Cancel(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void SaveChanges(){
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.length()>5 && confirmpassword.length()>5 ){
                    if(password.getText().toString().equals(confirmpassword.getText().toString())){
                            try {
                                db.update(user.getUsername(), user.getEmail(), password.getText().toString(), user.getType());
                                user.setPassword(password.getText().toString());
                                intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                                Toast.makeText(SetNewPasswordActivity.this, "Password Changed", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                            catch (Exception e){
                                Toast.makeText(SetNewPasswordActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                    }
                    else
                        Toast.makeText(SetNewPasswordActivity.this, "password dont equals to confirm password", Toast.LENGTH_LONG).show();
                }
                else if(password.length()<6)
                    Toast.makeText(SetNewPasswordActivity.this, "password most have 6 or more chars", Toast.LENGTH_LONG).show();
                else if(confirmpassword.length()<6)
                    Toast.makeText(SetNewPasswordActivity.this, "confirm password most have 6 or more chars", Toast.LENGTH_LONG).show();
            }
        });
    }
}