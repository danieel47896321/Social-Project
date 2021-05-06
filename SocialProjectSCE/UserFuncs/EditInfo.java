package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditInfo extends AppCompatActivity {
    private EditText FirstName, LastName, City, Depart, Age;
    private Button Cancel, Confirm;
    private CheckBox MaleBox, FemaleBox;
    private Intent intent;
    private User user;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    private static String type = "Male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        init();
    }
    public void init(){
        setID();
        malePick();
        femalePick();
        ShowInfo();
        Confirm();
        Cancel();
    }
    public void setID(){
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        City = findViewById(R.id.City);
        Depart = findViewById(R.id.Depart);
        Age = findViewById(R.id.Age);
        Cancel = findViewById(R.id.Cancel);
        Confirm = findViewById(R.id.Confirm);
        MaleBox = findViewById(R.id.MaleBox);
        FemaleBox = findViewById(R.id.FemaleBox);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        if(user.getSex().toString().equals("Male"))
            MaleBox.setChecked(true);
        else
            FemaleBox.setChecked(true);
    }
    public void ShowInfo(){
        FirstName.setText(user.getFirstname());
        LastName.setText(user.getLastname());
        Depart.setText(user.getDepart());
        City.setText(user.getCity());
        Age.setText(user.getAge());
    }
    public void malePick(){
        MaleBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FemaleBox.setChecked(false);
                type = "Male";
            }
        });
    }
    public void femalePick(){
        FemaleBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaleBox.setChecked(false);
                type = "Female";
            }
        });
    }
    public void Confirm(){
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirstName.getText().length()>0 && LastName.getText().length()>0 && Age.getText().length()>0 && City.getText().length()>0 && Depart.getText().length()>0) {
                    try {
                        if(FirstName.getText().length()>1)
                            user.setFirstname(FirstName.getText().toString());
                        if(LastName.getText().length()>1)
                            user.setLastname(LastName.getText().toString());
                        if(Age.getText().length()>0)
                            user.setAge(Age.getText().toString());
                        if(City.getText().length()>1)
                            user.setCity(City.getText().toString());
                        if(Depart.getText().length()>1)
                            user.setDepart(Depart.getText().toString());
                        user.setSex(type);
                        reference.child(user.getPhone()).setValue(user);
                    } catch (Exception e) {
                        Toast.makeText(EditInfo.this, "Faield", Toast.LENGTH_LONG).show();
                    }
                    intent = new Intent(EditInfo.this, MainScreen.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditInfo.this, "Must fill in all the details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Cancel(){
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(EditInfo.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}