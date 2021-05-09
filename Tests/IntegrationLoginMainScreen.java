package com.example.socialprojectsce;

import com.example.socialprojectsce.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class IntegrationLoginMainScreen {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    @Test
    public boolean validateLoginUserInformation(String email){
        //from user
        User user1 = new User();
        user1.setEmail(email);
        //from db
        User user2 = new User();
        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    user2.setEmail(datas.child("email").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return user1 == user2;
    }
}
