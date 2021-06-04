package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialprojectsce.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private Context context;
    private List<Msg> messages;
    private User user;
    final int ITEM_SENT = 1;
    public void setUser(User user){ this.user = user; }
    public MessageAdapter(Context context, List<Msg> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.TextMsg.setText(messages.get(position).getPublish()+": "+messages.get(position).getText());
    }

    @Override
    public int getItemCount() { return messages.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TextMsg;
        Button Agree,Reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextMsg = itemView.findViewById(R.id.TextMsg);
        }
    }
}
