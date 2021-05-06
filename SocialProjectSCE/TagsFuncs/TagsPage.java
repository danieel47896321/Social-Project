package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socialprojectsce.Classes.Tags;
import com.example.socialprojectsce.Classes.TagsView;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.UserFuncs.MainScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TagsPage extends AppCompatActivity {
    private User user;
    private Button Back;
    private RecyclerView viewList;
    private Intent intent;
    private List<Tags> tags;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Tags");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_page);
        init();
    }
    public void init(){
        setID();
        SetTags();
        Back();
    }
    public void setID(){
        tags = new ArrayList<>();
        Back = findViewById(R.id.Back);
        viewList = findViewById(R.id.StudentRV);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }
    public void SetTags(){
        reference.orderByChild("category").equalTo("tags").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String tagname = datas.child("tagname").getValue().toString();
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
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TagsPage.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}

