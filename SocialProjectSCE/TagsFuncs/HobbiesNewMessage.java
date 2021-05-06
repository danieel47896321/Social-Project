package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.Msg;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HobbiesNewMessage extends AppCompatActivity {
    private User user;
    private Button buttonOk,buttonCancel;
    private EditText MsgTitle;
    private String category;
    private TextView FullMsg;
    private RecyclerView viewList;
    private Intent intent;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Music");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_new_message);
        init();
    }
    public void init(){
        MsgTitle = findViewById(R.id.MsgTitle);
        FullMsg = findViewById(R.id.FullMsg);
        buttonOk = findViewById(R.id.buttonOK);
        buttonCancel = findViewById(R.id.buttonCancel);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        category = (String)intent.getSerializableExtra("category");
        OK();
        Cancel();
    }
    public void OK(){
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FullMsg.length()>0) {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Msg msg = new Msg(MsgTitle.getText().toString(),user.getFirstname() + " " + user.getLastname(),formatter.format(date),category, FullMsg.getText().toString());
                    reference.child(FullMsg.getText().toString()).setValue(msg);
                    intent = new Intent(HobbiesNewMessage.this, GenericHobbies.class);
                    intent.putExtra("user", user);
                    intent.putExtra("title", category);
                    startActivity(intent);
                }
                else
                    Toast.makeText(HobbiesNewMessage.this, "you must enter text", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Cancel(){
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HobbiesNewMessage.this, GenericHobbies.class);
                intent.putExtra("user", user);
                intent.putExtra("title", category);
                startActivity(intent);
            }
        });
    }
}