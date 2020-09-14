package com.example.gohospidoctor.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohospidoctor.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {
    ArrayList personNames;
    ArrayList personImages;
    Context context;

    public CustomAdapter(ArrayList personNames, ArrayList personImages, Context context) {
        this.personNames = personNames;
        this.personImages = personImages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //RecyclerView.ViewHolder myholder=(RecyclerView.ViewHolder) holder;

       // RecyclerView.ViewHolder myholder=
        MyViewHolder myViewHolder=(MyViewHolder) holder;

        myViewHolder.name.setText((String) personNames.get(position));
        myViewHolder.image.setImageResource((Integer) personImages.get(position));

        // holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.


    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
