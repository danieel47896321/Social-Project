package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.Msg;
import com.example.socialprojectsce.Classes.MsgView;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GenericHobbies extends AppCompatActivity {
    private User user;
    private Button btnBack,buttonOpenMsg;
    private TextView TagText;
    private RecyclerView viewList;
    private Intent intent;
    private List<Msg> msgs;
    private View screenView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_hobbies);
        init();
    }
    public void init(){
        msgs = new ArrayList<>();
        screenView = findViewById(R.id.rView);
        btnBack = findViewById(R.id.Back);
        viewList = findViewById(R.id.StudiesRV);
        TagText = findViewById(R.id.TagText1);
        buttonOpenMsg = findViewById(R.id.buttonOpenMsg);
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
        TagText.setText((String)intent.getSerializableExtra("title"));
        reference = database.getReference().child((String)intent.getSerializableExtra("title"));
        OpenNewMsg();
        SetTags();
        Back();
    }
    public void OpenNewMsg(){
        buttonOpenMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenericHobbies.this, HobbiesNewMessage.class);
                intent.putExtra("user", user);
                intent.putExtra("category", TagText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
    public void SetTags(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String Msgname = datas.child("0").child("msgname").getValue().toString();
                    String Publish = datas.child("0").child("publish").getValue().toString();
                    String Category = datas.child("0").child("category").getValue().toString();
                    String Date = datas.child("0").child("date").getValue().toString();
                    String Text = datas.child("0").child("text").getValue().toString();
                    Msg msg = new Msg(Msgname, Publish,Date, Category,Text);
                    AddMsg(msg);
                }
                ShowTags(msgs);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    public void AddMsg(Msg msg){
        msgs.add(msg);
    }
    public void ShowTags(List<Msg> msgList){
        MsgView myMsgs = new MsgView(this,msgList);
        myMsgs.setUser(user);
        viewList.setLayoutManager(new GridLayoutManager(this,1));
        viewList.setAdapter(myMsgs);
    }
    public void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenericHobbies.this, Hobbies.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}