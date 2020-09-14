package com.example.gohospidoctor.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gohospidoctor.ModalClass.DocLocation;
import com.example.gohospidoctor.R;

import java.util.List;

public class DoctorLocationAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DocLocation> docLocations;
    Context context;
    private int lastSelectedPosition = -1;

    public DoctorLocationAdopter(List<DocLocation> docLocations, Context context) {
        this.docLocations = docLocations;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_doct_location_item, parent, false);
        return new DoctorLocationAdopter.ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHolder myholder = (ViewHolder) holder;
        final DocLocation docLocation = docLocations.get(position);
        myholder.radioButton.setText(docLocation.getShort_address());

       // myholder.radioButton.setOnClickListener(new  );
        ((ViewHolder) holder).radioButton.setChecked(lastSelectedPosition == position);


    }

    @Override
    public int getItemCount() {
        return docLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.show_sortlocation);
           radioButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   lastSelectedPosition = getAdapterPosition();
                   notifyDataSetChanged();
                  // view.setBackgroundColor();
                   hitService();
               }
           });


        }
    }

    private void hitService() {
    }


}