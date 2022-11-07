package com.example.proj1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proj1.Activities.cards;
import com.example.proj1.Classes.Product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context context;
    ArrayList<Product> productsModels;

    public RecyclerViewAdapter(Context context, ArrayList<Product> productsModels ){

        this.context = context;
        this.productsModels = productsModels;
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
        holder.name.setText(productsModels.get(position).getName());
        holder.caducity.setText(productsModels.get(position).getCaducity());
        holder.quantity.setText(productsModels.get(position).getQuantity());
        //faltan mas atributos amostrar
    }

    @Override
    public int getItemCount() {
    // the recycler view just wants to know the number of items you want displayed
        return productsModels.size(); //
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    //Grabbing the views from our card_view layout file, kinda like in the onCreate method
    TextView name, caducity, quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_productname);
            caducity = itemView.findViewById(R.id.text_caducity);
            quantity = itemView.findViewById(R.id.text_quantity);
            //flatan mas atributos a mostrar


        }
    }
}
