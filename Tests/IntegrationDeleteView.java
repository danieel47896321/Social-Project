package com.example.socialprojectsce;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import org.junit.Test;

public class IntegrationDeleteView {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Updates");
    @Test
    public boolean validateIntegrationPostView(String Text) {
        //for db data than comapre with Text
        final String[] db_text = {""};
        reference.orderByChild("text").equalTo(Text).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    db_text[0] = datas.child("age").getValue().toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return Text == db_text[0];
    }
}