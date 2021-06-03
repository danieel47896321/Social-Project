package com.example.socialprojectsce.UserFuncs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.socialprojectsce.Classes.Message;
import com.example.socialprojectsce.Classes.Request;
import com.example.socialprojectsce.Classes.TagsView;
import com.example.socialprojectsce.Classes.User;
import com.example.socialprojectsce.GuestFuncs.Register;
import com.example.socialprojectsce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Requests extends AppCompatActivity {
    private Button Back;
    private User user;
    private Intent intent;
    private RecyclerView viewList;
    private List<User> users;
    private View screenView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Requests");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        init();
    }
    void init(){
        setID();
        Back();
        ShowUsers();
    }
    public void setID(){
        screenView = findViewById(R.id.rView);
        Back = findViewById(R.id.Back);
        viewList = findViewById(R.id.RequestRV);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        users = new ArrayList<User>();
        if(user.getBackground().equals("background"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));
        else if(user.getBackground().equals("background1"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background1));
        else if(user.getBackground().equals("background2"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background2));
        else if(user.getBackground().equals("background3"))
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background3));
    }
    public void ShowUsers(){
        reference.orderByChild("flag").equalTo("true").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    User temp = new User();
                    temp.setAge(datas.child("age").getValue().toString());
                    temp.setCity(datas.child("city").getValue().toString());
                    temp.setDepart(datas.child("depart").getValue().toString());
                    temp.setEmail(datas.child("email").getValue().toString());
                    temp.setFirstname(datas.child("firstname").getValue().toString());
                    temp.setFlag(datas.child("flag").getValue().toString());
                    temp.setLastname(datas.child("lastname").getValue().toString());
                    temp.setPhone(datas.child("phone").getValue().toString());
                    temp.setSex(datas.child("sex").getValue().toString());
                    temp.setType(datas.child("type").getValue().toString());
                    temp.setPhoto(Integer.valueOf(datas.child("photo").getValue().toString()));
                    users.add(temp);
                }
                ShowTags(users);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    public void ShowTags(List<User> User){
        Request myRequest = new Request(this,User);
        myRequest.setUser(user);
        viewList.setLayoutManager(new GridLayoutManager(this,1));
        viewList.setAdapter(myRequest);
    }
    public void Back(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Requests.this, MainScreen.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}