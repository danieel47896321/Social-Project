package com.example.socialprojectsce.TagsFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialprojectsce.Classes.Message;
import com.example.socialprojectsce.Classes.Msg;
import com.example.socialprojectsce.Classes.MsgView;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GenericMsgs extends AppCompatActivity {
    private User user;
    private EditText MessageBox;
    private ImageView attachment,camera,sendBtn;
    private RecyclerView viewList;
    private Intent intent;
    private List<Message> messages;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference ;
    private View screenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_msgs);
        init();
    }
    public void init(){
        messages = new ArrayList<Message>();
        screenView = findViewById(R.id.rView);
        MessageBox = findViewById(R.id.MessageBox);
        attachment = findViewById(R.id.attachment);
        camera = findViewById(R.id.camera);
        sendBtn = findViewById(R.id.sendBtn);
        viewList = findViewById(R.id.MsgsView);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        if(user.getSemester()!=null)
            reference = database.getReference().child(user.getStudy()).child(user.getYear()).child(user.getSemester());
        else
            reference = database.getReference().child("HobbiesTags").child(user.getPhone());
        Attach();
        Photo();
        Send();
    }
    public void Attach(){
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void Photo(){
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void Send(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}