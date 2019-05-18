package com.example.musthafa.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyviewHolder> {
    private List<Invoice> invoiceList;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list,parent,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final Invoice invoice = invoiceList.get(position);
        holder.name.setText(invoice.getPname());
        holder.price.setText(invoice.getPrice());
        holder.quantity.setText(invoice.getQuantity());
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public TextView quantity;
        public MyviewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.invoice_name_id);
            price=(TextView)itemView.findViewById(R.id.invoice_price_id);
            quantity=(TextView)itemView.findViewById(R.id.invoice_quantity_id);
        }
    }
    public InvoiceAdapter (List<Invoice> invoiceList){
        this.invoiceList=invoiceList;
    }

}
