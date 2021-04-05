package com.example.socialprojectsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditInfoActivity extends AppCompatActivity {
    private EditText firstname,lastname,city,depart,age;
    private Button cancel,confirm;
    private CheckBox male,female;
    private Intent intent;
    private User user;
    private DataBase db;
    private static String type = "male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        init();
    }
    public void init(){
        db = new DataBase(EditInfoActivity.this);
        firstname = findViewById(R.id.firstnameET);
        lastname = findViewById(R.id.lastnameET);
        city = findViewById(R.id.cityET);
        depart = findViewById(R.id.departET);
        age = findViewById(R.id.ageET);
        cancel = findViewById(R.id.EditinfoCancel);
        confirm = findViewById(R.id.EditinfoConfirm);
        male = findViewById(R.id.maleCheck);
        female = findViewById(R.id.femaleCheck);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        if(user.getSex().toString().equals("Male"))
            male.setChecked(true);
        else
            female.setChecked(true);
        malePick();
        femalePick();
        ShowInfo();
        Confirm();
        Cancel();
    }
    public void ShowInfo(){
        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        depart.setText(user.getDepart());
        city.setText(user.getCity());
        age.setText(user.getAge());
    }
    public void malePick(){
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(false);
                type = "Male";
            }
        });
    }
    public void femalePick(){
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                type = "Female";
            }
        });
    }
    public void Confirm(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstname.getText().length()>1 || lastname.getText().length()>1 || age.getText().length()>1 || city.getText().length()>1 || depart.getText().length()>1) {
                    try {
                        if(firstname.getText().length()>1)
                            user.setFirstname(firstname.getText().toString());
                        if(lastname.getText().length()>1)
                            user.setLastname(lastname.getText().toString());
                        if(age.getText().length()>1)
                            user.setAge(age.getText().toString());
                        if(city.getText().length()>1)
                            user.setCity(city.getText().toString());
                        if(depart.getText().length()>1)
                            user.setDepart(depart.getText().toString());
                        user.setSex(type);
                        db.update(user.getUsername(), user.getEmail(),user.getPassword(), user.getType(), user.getAge(), user.getCity(), user.getSex(), user.getDepart(), user.getFirstname(), user.getLastname());
                        db.close();
                    } catch (Exception e) {
                        Toast.makeText(EditInfoActivity.this, "Faield", Toast.LENGTH_LONG).show();
                    }
                    if (user.getType().equals("Admin"))
                        intent = new Intent(EditInfoActivity.this, AdminActivity.class);
                    else if (user.getType().equals("Teacher"))
                        intent = new Intent(EditInfoActivity.this, TeacherActivity.class);
                    else
                        intent = new Intent(EditInfoActivity.this, StudentActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditInfoActivity.this, "U cant leave all info empty fill", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Cancel(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getType().equals("Admin"))
                    intent = new Intent(EditInfoActivity.this, AdminActivity.class);
                else if (user.getType().equals("Teacher"))
                    intent = new Intent(EditInfoActivity.this, TeacherActivity.class);
                else
                    intent = new Intent(EditInfoActivity.this, StudentActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}