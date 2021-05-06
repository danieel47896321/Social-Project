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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Engineering extends AppCompatActivity {


    private User user;
    private Button btnBack;
    private TextView TagText;
    private RecyclerView viewList;
    private Intent intent;
    private List<Tags> tags;
    private ResultSet resultSet;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering);
        init();
    }
    public void init(){
        tags = new ArrayList<>();
        btnBack = findViewById(R.id.Back);
        viewList = findViewById(R.id.StudiesRV);
        TagText = findViewById(R.id.TagText1);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        title = (String)intent.getSerializableExtra("title");
        TagText.setText(title);
        SetTags();
        Back();
    }
    public void SetTags(){
        try {
            //resultSet = db.getCategory("year");
            if (resultSet.next())
                do {
                    String tagname = resultSet.getString("Name");
                    String category = resultSet.getString("Category");
                    int photo = resultSet.getInt("Photo");
                    tags.add(new Tags(tagname, category, photo));
                } while (resultSet.next());
        }
        catch (Exception e){
            Toast.makeText(Engineering.this, "Failed", Toast.LENGTH_LONG).show();
        }
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