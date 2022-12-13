package com.example.proj1.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proj1.R;
import com.example.proj1.RecyclerViewInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<Product> productlist;

    public RecyclerViewAdapter(Context context, ArrayList<Product> productlist, RecyclerViewInterface recyclerViewInterface  ){

        this.context = context;
        this.productlist = productlist;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view,parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the card_view layout file based on
        // the position of the recycler view

        String Quantity = String.valueOf(productlist.get(position).getQuantity())+" u";
        String cad = productlist.get(position).getCaducity();
        if (Objects.equals(cad, "")){
            cad="No hay fecha caducidad";
        }
        holder.name.setText(productlist.get(position).getName());
        holder.caducity.setText(cad);
        holder.quantity.setText(Quantity);

        //faltan mas atributos amostrar
    }

    @Override
    public int getItemCount() {
    // the recycler view just wants to know the number of items you want displayed
        return productlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{//poner un static?
    //Grabbing the views from our card_view layout file, kinda like in the onCreate method
    TextView name, caducity, quantity;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            name = itemView.findViewById(R.id.text_productname);
            caducity = itemView.findViewById(R.id.text_caducity);
            quantity = itemView.findViewById(R.id.text_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();

                        if (pos!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();

                        if (pos!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }

}
