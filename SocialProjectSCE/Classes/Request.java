package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.OpeningScreen;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.UserFuncs.MainScreen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Request extends RecyclerView.Adapter<Request.MyViewHolder> {
    private Context context;
    private List<User> users;
    private User user;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Requests");
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference ref = db.getReference().child("Users");;
    public Request(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView FullName;
        TextView Phone;
        TextView Email;
        ImageView textViewImage;
        Button Agree,Reject;
        ConstraintLayout cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            FullName = itemView.findViewById(R.id.PostMsg);
            Phone = itemView.findViewById(R.id.PhoneRequest);
            Email = itemView.findViewById(R.id.EmailRequest);
            Agree = itemView.findViewById(R.id.RequestAgree);
            Reject = itemView.findViewById(R.id.RequestReject);
            textViewImage = itemView.findViewById(R.id.UserImage);
            cardView = itemView.findViewById(R.id.Request_id);
        }
    }
    public void setUser(User user){ this.user = user; }
    public Request.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.request_view,parent,false);
        return new Request.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.FullName.setText("Name: "+users.get(position).getFirstname()+" "+users.get(position).getLastname());
        holder.Phone.setText("Phone:" +users.get(position).getPhone());
        holder.Email.setText("Email: "+users.get(position).getEmail());
        holder.textViewImage.setImageResource(users.get(position).getPhoto());
        holder.Agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(users.get(position).getPhone()).child("flag").setValue("agree");
                ref.child(users.get(position).getPhone()).child("flag").setValue("agree");
            }
        });
        holder.Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(users.get(position).getPhone()).child("flag").setValue("false");
                ref.child(users.get(position).getPhone()).child("flag").setValue("false");
            }
        });
    }

    public int getItemCount() { return users.size(); }
}
