package com.example.musthafa.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
//public interface RecyclerViewClickListener {
//    public void recyclerViewListClicked(View v, int position);
//}

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }
    private Context context;

    private List<Products> productsList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Products products = productsList.get(position);
        holder.product_name.setText(products.getName());
        holder.price.setText(products.getPrice());
        //holder.quantity.setText(products.getQuantity());
        Log.v("image",products.getImage());
        String Url="http://www.slight.fabstudioz.com/fitness/"+products.getImage();
       Glide.with(products.getContext()).load(Url).apply(new RequestOptions().override(100,40)).into(holder.image);


        //holder.image.setImageResource(Integer.parseInt(products.getImage()));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView price;
       // public TextView quantity;
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            //quantity=(TextView)itemView.findViewById(R.id.id_quantity);
           image=(ImageView)itemView.findViewById(R.id.imageviewproduct);


        }


    }
    public ProductAdapter (List<Products> productList){
        this.productsList=productList;
    }
    public ProductAdapter(Context context){
        this.context = context;

    }
}
