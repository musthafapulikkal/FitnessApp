package com.example.musthafa.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyView> {
      private Context context;
    private List<Products> productsList;
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list,parent,false);
        return new MyView(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        final Products products = productsList.get(position);
        holder.product_name.setText(products.getName());
        holder.price.setText(products.getPrice());
        holder.quantity.setText(products.getQuantity());
        String Url="http://www.slight.fabstudioz.com/fitness/"+products.getImage() ;
        Glide.with(products.getContext()).load(Url).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView price;
        public TextView quantity;
        public ImageView image;
        public ImageButton delete;
        public MyView(View itemView) {
            super(itemView);
            product_name=(TextView)itemView.findViewById(R.id.txt_cart_name);
            price=(TextView)itemView.findViewById(R.id.txt_cart_price);
            quantity=(TextView)itemView.findViewById(R.id.txt_qty_id);
            image=(ImageView)itemView.findViewById(R.id.imageviewcart);
            delete=(ImageButton)itemView.findViewById(R.id.deletecart_id);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deleteAt(getAdapterPosition());
                }
            });
        }

    }

    private void deleteAt(int adapterPosition) {
        Log.v("size", String.valueOf(productsList.size()));
        if (productsList.size()==0){

        }
        else {
            productsList.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(adapterPosition,productsList.size());
            final Products products=productsList.get(adapterPosition);
            RequestQueue requestQueue = Volley.newRequestQueue(products.getContext());
            Log.v("id",products.getId());
            String Url = "http://www.slight.fabstudioz.com/fitness/delete.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("response",response);
                  if (response.equals("success")){
                      Toast.makeText(products.getContext(),"success",Toast.LENGTH_SHORT).show();
                  }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Log.v("response", String.valueOf(error));
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("id",products.getId());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public CartAdapter(List<Products> productlists){this.productsList=productlists;}
    public CartAdapter(Context context){
        this.context = context;

    }
}
