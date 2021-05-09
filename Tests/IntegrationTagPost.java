package com.example.socialprojectsce;

import com.example.socialprojectsce.Classes.Tags;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class IntegrationMsgPost {
        private FirebaseDatabase database = FirebaseDatabase.getInstance();
        private DatabaseReference reference = database.getReference().child("Tags");
        @Test
        public boolean validateTagPostInformation(String tagname, String category,int photo) {
            //from user
            Tags tag1 = new Tags(tagname,category,photo);
            //from db
            Tags tag2 = new Tags();
            reference.orderByChild("tagname").equalTo(tagname).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        tag2.setCategory(datas.child("category").getValue().toString());
                        tag2.setTagname(datas.child("tagname").getValue().toString());
                        tag2.setPhoto(Integer.valueOf(datas.child("photo").getValue().toString()));
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            return tag1 == tag2;
        }
}
