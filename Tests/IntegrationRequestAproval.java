package com.example.socialprojectsce;

import com.example.socialprojectsce.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class IntegrationRequestAproval {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Requests");
    @Test
    public boolean validateIntegrationRequestAproval(User user1) {
        //for db data than comapre with user 1
        User user2 = new User();
        reference.orderByChild(user1.getPhone()).equalTo(user1.getPhone()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    user2.setAge(datas.child("age").getValue().toString());
                    user2.setCity(datas.child("city").getValue().toString());
                    user2.setDepart(datas.child("depart").getValue().toString());
                    user2.setEmail(datas.child("email").getValue().toString());
                    user2.setFirstname(datas.child("firstname").getValue().toString());
                    user2.setFlag(datas.child("flag").getValue().toString());
                    user2.setLastname(datas.child("lastname").getValue().toString());
                    user2.setPhone(datas.child("phone").getValue().toString());
                    user2.setSex(datas.child("sex").getValue().toString());
                    user2.setType(datas.child("type").getValue().toString());
                    user2.setBackground(datas.child("background").getValue().toString());
                    user2.setBackground(datas.child("photo").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return user1 == user2;
    }
}