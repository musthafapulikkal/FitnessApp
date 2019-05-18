package com.example.musthafa.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.MyviewHolder> {
    private Context context;
    private List<Products> productsList;

    @NonNull
    @Override
    public AdminProductAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_product_list,parent,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductAdapter.MyviewHolder holder, int position) {
        final Products products = productsList.get(position);
        holder.product_name.setText(products.getName());
        holder.price.setText(products.getPrice());
        //holder.quantity.setText(products.getQuantity());
        String Url="http://www.slight.fabstudioz.com/fitness/fitness/"+products.getImage() ;
        Glide.with(products.getContext()).load(Url).into(holder.image);

    }

    @Override
    public int getItemCount() {

            return productsList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView price;
        // public TextView quantity;
        public ImageView image;
        public Button delete;
        public MyviewHolder(View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            //quantity=(TextView)itemView.findViewById(R.id.id_quantity);
            image=(ImageView)itemView.findViewById(R.id.imageviewproduct);


        }

    }


    public AdminProductAdapter (List<Products> productList){
        this.productsList=productList;
    }
    public AdminProductAdapter(Context context){
        this.context = context;

    }
}
