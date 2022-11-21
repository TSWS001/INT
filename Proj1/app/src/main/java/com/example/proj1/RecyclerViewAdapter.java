package com.example.proj1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proj1.Classes.Product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context context;
    ArrayList<Product> productlist;

    public RecyclerViewAdapter(Context context, ArrayList<Product> productlist ){

        this.context = context;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view,parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the card_view layout file based on
        // the position of the recycler view

        String Quantity = String.valueOf(productlist.get(position).getQuantity())+" u";

        holder.name.setText(productlist.get(position).getName());
        holder.caducity.setText(productlist.get(position).getCaducity());
        holder.quantity.setText(Quantity);
        //faltan mas atributos amostrar
    }

    @Override
    public int getItemCount() {
    // the recycler view just wants to know the number of items you want displayed
        return productlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    //Grabbing the views from our card_view layout file, kinda like in the onCreate method
    TextView name, caducity, quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_productname);
            caducity = itemView.findViewById(R.id.text_caducity);
            quantity = itemView.findViewById(R.id.text_quantity);
            //flatan mas atributos a mostrar?

        }
    }
    public void removeAt(int index){
        productlist.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index,productlist.size());
    }


}
