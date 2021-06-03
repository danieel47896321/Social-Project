package com.example.socialprojectsce.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialprojectsce.GuestFuncs.Login;
import com.example.socialprojectsce.OpeningScreen;
import com.example.socialprojectsce.R;
import com.example.socialprojectsce.TagsFuncs.About;
import com.example.socialprojectsce.TagsFuncs.Engineering;
import com.example.socialprojectsce.TagsFuncs.EngineeringYear;
import com.example.socialprojectsce.TagsFuncs.GenericHobbies;
import com.example.socialprojectsce.TagsFuncs.GenericStudies;
import com.example.socialprojectsce.TagsFuncs.Hobbies;
import com.example.socialprojectsce.TagsFuncs.TagsPage;
import com.example.socialprojectsce.TagsFuncs.Updates;
import com.example.socialprojectsce.TagsFuncs.Studies;
import com.example.socialprojectsce.TagsFuncs.Contact;

import java.util.List;

public class TagsView extends RecyclerView.Adapter<TagsView.MyViewHolder> {
    private Context context;
    private List<Tags> tags;
    private User user;
    private String Eng[] = {"Software engineering","Electrical Engineering","Industrial Engineering and Management","Building Engineering","Mechanical Engineering","Chemical Engineering"};
    private String Years[] = {"Year 1","Year 2","Year 3","Year 4"};
    private String Sem[] = {"Semester A","Semester B","Summer Semester"};
    private String Hobbies[] = {"Games","Sport","Music","Fun","Other"};

    public TagsView(Context context, List<Tags> tags) {
        this.context = context;
        this.tags = tags;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tagText);
            imageView = itemView.findViewById(R.id.tagImage);
            cardView = itemView.findViewById(R.id.tagview_id);
        }
    }
    public void setUser(User user){ this.user = user; }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.tag_view,parent,false);
        return new MyViewHolder(view);
    }
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(tags.get(position).getText());
        holder.imageView.setImageResource(tags.get(position).getPhoto());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TagsPage.class);
                if(holder.textView.getText().equals("Studies"))
                    intent = new Intent(context, Studies.class);
                else if(holder.textView.getText().equals("Hobbies"))
                    intent = new Intent(context, Hobbies.class);
                else if(holder.textView.getText().equals("Updates"))
                    intent = new Intent(context, Updates.class);
                else if(holder.textView.getText().equals("Contact"))
                    intent = new Intent(context, Contact.class);
                else if(holder.textView.getText().equals("About"))
                    intent = new Intent(context, About.class);
                else{
                    for(String eng : Eng)
                        if(holder.textView.getText().equals(eng))
                            intent = new Intent(context, Engineering.class);
                    for(String year : Years)
                        if(holder.textView.getText().equals(year))
                            intent = new Intent(context, EngineeringYear.class);
                    for(String sem : Sem)
                        if(holder.textView.getText().equals(sem))
                            intent = new Intent(context, GenericStudies.class);
                    for(String hobies : Hobbies)
                        if(holder.textView.getText().equals(hobies))
                            intent = new Intent(context, GenericHobbies.class);
                }
                intent.putExtra("title",holder.textView.getText());
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
    }
    public int getItemCount() { return tags.size(); }
}
