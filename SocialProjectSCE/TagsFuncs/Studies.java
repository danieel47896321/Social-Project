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
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.Classes.TagsView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Studies extends AppCompatActivity {
    private User user;
    private Button btnBack;
    private TextView TagText;
    private RecyclerView viewList;
    private Intent intent;
    private List<Tags> tags;
    private ResultSet resultSet;
    /*
    private static String TAG = null;
    private static String Engn = null;
    private static String Year = null;
    private static String Semester = null;
    private String Eng[] = {"הנדסת תוכנה","הנדסת חשמל","הנדסת תעשייה וניהול","הנדסת בנין","הנדסת מכונות","הנדסת כימיה","הנדסת תקשורת"};
    private String Years[] = {"מכינה","שנה א","שנה ב","שנה ג","שנה ד"};
    private String Sem[] = {"סמסטר קיץ","סמסטר א","סמסטר ב"};*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void init(){
        setContentView(R.layout.activity_studies);
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
        try {
           // resultSet = db.getCategory("לימודים");
            if (resultSet.next())
                do {
                    String tagname = resultSet.getString("Name");
                    String category = resultSet.getString("Category");
                    int photo = resultSet.getInt("Photo");
                    tags.add(new Tags(tagname, category, photo));
                } while (resultSet.next());
        }
        catch (Exception e){
            Toast.makeText(Studies.this, "Failed", Toast.LENGTH_LONG).show();
        }
        TagsView myTags = new TagsView(this,tags);
        viewList.setLayoutManager(new GridLayoutManager(this,3));
        viewList.setAdapter(myTags);
    }
    public void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent = new Intent(Studies.this, TagsPage.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
            }
        });
    }
}