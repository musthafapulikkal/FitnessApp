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

public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.MyviewHolder> {
    private Context context;
    private List<Centeres> centerList;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.fitness_list,parent,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final Centeres centeres=centerList.get(position);
        holder.name.setText(centeres.getCenterName());
        holder.place.setText(centeres.getCenterPlce());
        String Url="http://www.slight.fabstudioz.com/fitness/"+centeres.getImage();
        Glide.with(centeres.getContext()).load(Url).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return centerList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView place;
        public ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.txt_centre_name);
            place=(TextView) itemView.findViewById(R.id.txt_centre_place);
            image=(ImageView) itemView.findViewById(R.id.imageview_centre_id);

        }
    }
    public CenterAdapter (List<Centeres> centerList){
        this.centerList=centerList;
    }
    public CenterAdapter(Context context){
        this.context = context;

    }
}
