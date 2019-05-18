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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class   OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyviewHolder> {
    private Context context;
    private List<ProductList> productsList;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list,parent,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final ProductList products = productsList.get(position);
        holder.product_name.setText(products.getName());
        holder.price.setText(products.getPrice());
        //holder.quantity.setText(products.getQuantity());
        String Url="http://www.slight.fabstudioz.com/fitness/"+products.getImage() ;
        Glide.with(products.getContext()).load(Url).into(holder.image);
        holder.email.setText(products.getEmail());
        holder.phone.setText(products.getPhone());
        holder.address.setText(products.getAddress());
//        holder.delivered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView price;
        public TextView email;
        public TextView phone;
        public TextView address;
        public Button delivered;
        //public TextView quantity;
        public ImageView image;
        public MyviewHolder(View itemView) {
            super(itemView);
            product_name=(TextView)itemView.findViewById(R.id.txt_order_name);
            price=(TextView)itemView.findViewById(R.id.txt_order_price);
           // quantity=(TextView)itemView.findViewById(R.id.txt_qty_id);
            image=(ImageView)itemView.findViewById(R.id.imageview_order);
            email=(TextView)itemView.findViewById(R.id.txt_order_email);
            phone=(TextView)itemView.findViewById(R.id.txt_order_phone);
            address=(TextView)itemView.findViewById(R.id.txt_order_address);
            delivered=(Button)itemView.findViewById(R.id.btn_delivered);

            delivered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(getAdapterPosition());

                }
            });
        }
        public void removeAt(final int position){
            if(productsList.size()==0){

            }else {
                final ProductList products=productsList.get(position);
                productsList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, productsList.size());
//                final ProductList products = productsList.get(position);
                RequestQueue requestQueue = Volley.newRequestQueue(products.getContext());
                String Url = "http://www.slight.fabstudioz.com/fitness/delivered.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("item", products.getId());
                        //Log.v("ideee",products.getId());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        }
    }
    public OrderAdapter(List<ProductList> productlists){this.productsList=productlists;}
    public OrderAdapter(Context context){
        this.context = context;

    }
}
