package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.GenericMsgs;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Message> messages;
    private User user;
    final int ITEM_SENT = 1;
    final int ITEM_RECEVE = 2;
    public void setUser(User user){ this.user = user; }
    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return  new SentViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_receve,parent,false);
            return  new ReceveViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getClass() == SentViewHolder.class){
            SentViewHolder viewHolder = (SentViewHolder) holder;
        }
    }
    public int getItemViewType(int position){
        Message message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderID()))
            return ITEM_SENT;
        else
            return ITEM_RECEVE;
    }
    @Override
    public int getItemCount() { return messages.size(); }


    public class SentViewHolder extends RecyclerView.ViewHolder{
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class ReceveViewHolder extends RecyclerView.ViewHolder{

        public ReceveViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
