package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.GenericMsgs;
import com.example.socialprojectsce.TagsFuncs.Hobbies;
import com.example.socialprojectsce.TagsFuncs.Studies;
import com.example.socialprojectsce.TagsFuncs.TagsPage;

import java.util.List;

public class MsgView extends RecyclerView.Adapter<MsgView.MyViewHolder> {
    private Context context;
    private List<Msg> msgs;
    private User user;
    private String Type;
    public MsgView(Context context, List<Msg> msgs) {
        this.context = context;
        this.msgs = msgs;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMsg;
        TextView textViewPublish;
        TextView textViewDate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMsg = itemView.findViewById(R.id.msgText);
            textViewPublish = itemView.findViewById(R.id.msgTextPublish);
            textViewDate = itemView.findViewById(R.id.msgTextDate);
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
        holder.textViewMsg.setText(msgs.get(position).getMsgname());
        holder.textViewPublish.setText("Open by: "+msgs.get(position).getPublish());
        holder.textViewDate.setText("Date: "+msgs.get(position).getDate());
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