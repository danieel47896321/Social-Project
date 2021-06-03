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

import com.example.socialprojectsce.Classes.Tags;
import com.example.socialprojectsce.Classes.TagsView;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Engineering extends AppCompatActivity {
    private User user;
    private Button btnBack;
    private TextView TagText;
    private RecyclerView viewList;
    private Intent intent;
    private View screenView;
    private List<Tags> tags;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Year");
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering);
        init();
    }
    public void init(){
        tags = new ArrayList<>();
        screenView = findViewById(R.id.rView);

        btnBack = findViewById(R.id.Back);
        viewList = findViewById(R.id.StudiesRV);
        TagText = findViewById(R.id.TagText1);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        if(user.getBackground().equals("background"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
        else if(user.getBackground().equals("background1"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
        else if(user.getBackground().equals("background2"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
        else if(user.getBackground().equals("background3"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
        title = (String)intent.getSerializableExtra("title");
        if(user.getStudy()!=null) {
            user.setYear(null);
            TagText.setText(user.getStudy());
        }
        else {
            user.setStudy(title);
            TagText.setText(user.getStudy());
        }
        SetTags();
        Back();
    }
    public void SetTags(){
        reference.orderByChild("category").equalTo("Year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String tagname = datas.child("text").getValue().toString();
                    String category = datas.child("category").getValue().toString();
                    int photo = Integer.valueOf(datas.child("photo").getValue().toString());
                    tags.add(new Tags(tagname, category, photo));
                }
                ShowTags(tags);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    public void ShowTags(List<Tags> tagList){
        TagsView myTags = new TagsView(this,tags);
        myTags.setUser(user);
        viewList.setLayoutManager(new GridLayoutManager(this,3));
        viewList.setAdapter(myTags);
    }
    public void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Engineering.this, Studies.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}