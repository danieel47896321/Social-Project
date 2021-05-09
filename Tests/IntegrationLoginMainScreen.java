package com.example.socialprojectsce;

import androidx.annotation.NonNull;

import com.example.socialprojectsce.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class IntegrationLoginMainScreen {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    private FirebaseAuth myAuth = FirebaseAuth.getInstance();
    @Test
    public boolean validateLoginUserInformation(String email,String Password){
        //from user
        User user1 = new User();
        user1.setEmail(email);
        //from db
        User user2 = new User();
        myAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
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
                } else{  }
            }
        });
        return user1 == user2;
    }
}


