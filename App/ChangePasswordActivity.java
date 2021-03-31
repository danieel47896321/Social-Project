package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText curpassword , newpassword, confirmpassword;
    private Button savechange, cancel;
    private Intent intent;
    private User user;
    private DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        db = new DataBase(ChangePasswordActivity.this);
        init();
    }
    public void init(){
        curpassword = findViewById(R.id.Curpassword);
        newpassword = findViewById(R.id.Newpassword);
        confirmpassword = findViewById(R.id.Confpassword);
        savechange = findViewById(R.id.btnSaveChanges);
        cancel = findViewById(R.id.btnCancel);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        Cancel();
        SaveChanges();
    }
    public void SaveChanges(){
        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curpassword.length()>5 && newpassword.length()>5 && confirmpassword.length()>5){
                    if(curpassword.getText().toString().equals(user.getPassword())){
                        if(newpassword.getText().toString().equals(confirmpassword.getText().toString())){
                            try {
                                db.update(user.getUsername(), user.getEmail(), newpassword.getText().toString(), user.getType());
                                user.setPassword(newpassword.getText().toString());
                                if (user.getType().equals("Admin"))
                                    intent = new Intent(ChangePasswordActivity.this, AdminActivity.class);
                                else if (user.getType().equals("Teacher"))
                                    intent = new Intent(ChangePasswordActivity.this, TeacherActivity.class);
                                else
                                    intent = new Intent(ChangePasswordActivity.this, StudentActivity.class);
                                Toast.makeText(ChangePasswordActivity.this, "Password Changed", Toast.LENGTH_LONG).show();
                                intent.putExtra("user", user);
                                db.close();
                                startActivity(intent);
                            }
                            catch (Exception e){
                                Toast.makeText(ChangePasswordActivity.this, "failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                            Toast.makeText(ChangePasswordActivity.this, "new password not equals to confirm password", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(ChangePasswordActivity.this, "wrong password try again", Toast.LENGTH_LONG).show();
                }
                else if(curpassword.length()<6)
                    Toast.makeText(ChangePasswordActivity.this, "cur password most have 6 or more chars", Toast.LENGTH_LONG).show();
                else if(newpassword.length()<6)
                    Toast.makeText(ChangePasswordActivity.this, "new password most have 6 or more chars", Toast.LENGTH_LONG).show();
                else if(confirmpassword.length()<6)
                    Toast.makeText(ChangePasswordActivity.this, "confirm password most have 6 or more chars", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Cancel(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getType().equals("Admin"))
                    intent = new Intent(ChangePasswordActivity.this, AdminActivity.class);
                else if(user.getType().equals("Teacher"))
                    intent = new Intent(ChangePasswordActivity.this, TeacherActivity.class);
                else
                    intent = new Intent(ChangePasswordActivity.this, StudentActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}