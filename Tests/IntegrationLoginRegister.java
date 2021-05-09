package com.example.socialprojectsce;

import com.example.socialprojectsce.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class IntegrationLoginRegister {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    @Test
    public boolean validateRegisterUserInformation(String email,String phone, String type, String age, String city, String sex, String depart, String firstname, String lastname, String flag){
        //from user
        User user1 = new User(email,phone, type, age,city, sex,depart,firstname,lastname,flag);
        //from db
        User user2 = new User();
        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
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
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return user1 == user2;
    }
}