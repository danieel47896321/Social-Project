package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
    private View screenView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_new_message);
        init();
    }
    public void init(){
        MsgTitle = findViewById(R.id.MsgTitle);
        screenView = findViewById(R.id.rView);
        FullMsg = findViewById(R.id.FullMsg);
        buttonOk = findViewById(R.id.buttonOK);
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
        category = (String)intent.getSerializableExtra("category");
        reference = database.getReference().child(category);
        OK();
        Cancel();
    }
    public void OK(){
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FullMsg.length()>0) {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    Msg msg = new Msg(MsgTitle.getText().toString(), user.getFirstname() + " " + user.getLastname(), formatter.format(date), category, FullMsg.getText().toString());
                    msg.setPhoto(user.getPhoto());
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