package com.example.musthafa.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyviewHolder> {
    private Context context;
    private List<Trainers> trainerList;

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.trainer_list,parent,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final Trainers trainers=trainerList.get(position);
        String t_name=trainers.getContext().getResources().getString(R.string.name);
        holder.name.setText(t_name+trainers.getTrainerName());
        //holder.name.setText(trainers.getTrainerName());
        String t_age=trainers.getContext().getResources().getString(R.string.age);
        holder.age.setText(t_age+trainers.getTrainerAge());
        String t_phone=trainers.getContext().getResources().getString(R.string.phone);
        holder.phone.setText(t_phone+trainers.getTrainerPhone());
        String Url="http://www.slight.fabstudioz.com/fitness/"+trainers.getImage();
        Glide.with(trainers.getContext()).load(Url).apply(new RequestOptions().circleCropTransform()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView phone;
        public ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txt_trainer_name);
            age=(TextView)itemView.findViewById(R.id.txt_trainer_age);
            phone=(TextView)itemView.findViewById(R.id.txt_trainer_phone);
            image=(ImageView)itemView.findViewById(R.id.imageview_trainer_id);
        }
    }
    public TrainerAdapter (List<Trainers> trainerList){
        this.trainerList=trainerList;
    }
    public TrainerAdapter(Context context){
        this.context = context;

    }
}
