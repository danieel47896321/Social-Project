package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.GenericMsgs;

import java.util.List;

public class MsgView extends RecyclerView.Adapter<MsgView.MyViewHolder> {
    private Context context;
    private List<Msg> msgs;
    private List<User> users;
    private User user;
    public MsgView(Context context, List<Msg> msgs) {
        this.context = context;
        this.msgs = msgs;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMsg;
        TextView textViewPostMsg;
        TextView textViewDate;
        ImageView textViewImage;
        ConstraintLayout cardView;
        Button Agree,Reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMsg = itemView.findViewById(R.id.lastMsg);
            textViewPostMsg = itemView.findViewById(R.id.PostMsg);
            textViewDate = itemView.findViewById(R.id.date);
            textViewImage = itemView.findViewById(R.id.UserImage);
            cardView = itemView.findViewById(R.id.msgview_id);
        }
    }
    public void setUser(User user){ this.user = user; }
    public MsgView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.msg_view,parent,false);
        return new MsgView.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MsgView.MyViewHolder holder, int position) {
        holder.textViewMsg.setText(msgs.get(position).getPublish()+": "+msgs.get(position).getText());
        holder.textViewPostMsg.setText(msgs.get(position).getMsgname());
        holder.textViewDate.setText(msgs.get(position).getDate());
        holder.textViewImage.setImageResource(msgs.get(position).getPhoto());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GenericMsgs.class);
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
    }
    public int getItemCount() { return msgs.size(); }
}