package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.socialprojectsce.UserFuncs.EditInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Hobbies extends AppCompatActivity {
    private User user;
    private Button btnBack;
    private TextView TagText;
    private RecyclerView viewList;
    private Intent intent;
    private List<Tags> tags;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Tags");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);
        init();
    }
    public void init(){
        tags = new ArrayList<>();
        btnBack = findViewById(R.id.Back);
        viewList = findViewById(R.id.StudiesRV);
        TagText = findViewById(R.id.TagText1);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        SetTags();
        Back();
    }
    public void SetTags(){
        reference.orderByChild("category").equalTo("Hobbies").addListenerForSingleValueEvent(new ValueEventListener() {
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Hobbies.this, TagsPage.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}