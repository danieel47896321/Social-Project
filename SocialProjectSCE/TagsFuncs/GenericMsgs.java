package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.Message;
import com.example.socialprojectsce.Classes.MessageAdapter;
import com.example.socialprojectsce.Classes.Msg;
import com.example.socialprojectsce.Classes.Tags;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GenericMsgs extends AppCompatActivity {
    private User user;
    private EditText MessageBox;
    private ImageView sendBtn;
    private RecyclerView viewList;
    private Intent intent;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference ;
    private ArrayList<Msg> msgs;
    private View screenView;
    private String PostMsg;
    private String Name;
    private String Date;
    private String MSG;
    private String flag="false";
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_msgs);
        init();
    }
    public void init() {
        msgs = new ArrayList<Msg>();
        screenView = findViewById(R.id.rView);
        MessageBox = findViewById(R.id.MessageBox);
        sendBtn = findViewById(R.id.sendBtn);
        viewList = findViewById(R.id.MsgsView);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        PostMsg = (String) intent.getSerializableExtra("title");
        Name = (String) intent.getSerializableExtra("name");
        Date = (String) intent.getSerializableExtra("date");
        MSG = (String) intent.getSerializableExtra("msg");
        category = (String) intent.getSerializableExtra("category");
        if (user.getSemester() != null) {
            reference = database.getReference().child(user.getStudy()).child(user.getYear()).child(user.getSemester()).child(PostMsg);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        String Msgname = datas.child("msgname").getValue().toString();
                        String Publish = datas.child("publish").getValue().toString();
                        String Category = datas.child("category").getValue().toString();
                        String Date = datas.child("date").getValue().toString();
                        String Text = datas.child("text").getValue().toString();
                        Msg msg = new Msg(Msgname, Publish,Date, Category,Text);
                        msgs.add(msg);
                    }
                    AllMesseges(msgs);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        }
        else {
            reference = database.getReference().child(category).child(PostMsg);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        String Msgname = datas.child("msgname").getValue().toString();
                        String Publish = datas.child("publish").getValue().toString();
                        String Category = datas.child("category").getValue().toString();
                        String Date = datas.child("date").getValue().toString();
                        String Text = datas.child("text").getValue().toString();
                        Msg msg = new Msg(Msgname, Publish,Date, Category,Text);
                        msgs.add(msg);
                    }
                    AllMesseges(msgs);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        }
        Send();
    }
    public void AllMesseges(ArrayList<Msg> messages){
        MessageAdapter messageAdapter = new MessageAdapter(this,messages);
        messageAdapter.setUser(user);
        viewList.setLayoutManager(new GridLayoutManager(this,1));
        viewList.setAdapter(messageAdapter);
    }
    public void Send(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg message = new Msg(PostMsg,user.getFirstname()+" "+user.getLastname(),Date,category,MessageBox.getText().toString());
                msgs.add(message);
                AllMesseges(msgs);
                reference.setValue(msgs);
                MessageBox.setText("");
            }
        });
    }

}