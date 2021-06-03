package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.Tags;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Updates extends AppCompatActivity {
    private TextView update;
    private EditText MsgText;
    private Button back,addUpdate,ClearAll;
    private User user;
    private String Text=null;
    private Intent intent;
    private View screenView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Updates");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        init();
    }
    public void init(){
        setID();
        back();
        getUpdate();
        Update();
        Clear();
    }
    void setID(){
        back = findViewById(R.id.Back);
        screenView = findViewById(R.id.rView);
        addUpdate = findViewById(R.id.addUpdate);
        ClearAll = findViewById(R.id.ClearAll);
        MsgText = findViewById(R.id.MsgText);
        update = findViewById(R.id.NewsUpdate);
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
        if(user.getType().equals("Admin")){
            MsgText.setVisibility(View.VISIBLE);
            addUpdate.setVisibility(View.VISIBLE);
            ClearAll.setVisibility(View.VISIBLE);
        }
        else{
            MsgText.setVisibility(View.INVISIBLE);
            addUpdate.setVisibility(View.INVISIBLE);
            ClearAll.setVisibility(View.INVISIBLE);
        }
    }
    public void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Updates.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    public void Clear(){
        ClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("text").setValue("");
                update.setText("");
                Text = "";
            }
        });
    }
    public void getUpdate(){
        reference.orderByChild("text").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text="";
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    text+= datas.getValue().toString();
                }
                UpdateText(text);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public void UpdateText(String text){ Text = text;update.setText(Text); }
    public void Update(){
        addUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdate();
                if(MsgText.length()>0){
                    reference.child("text").setValue(Text + "\n" +MsgText.getText().toString());
                    update.setText(Text + "\n" +MsgText.getText().toString());
                }
            }
        });
    }
}