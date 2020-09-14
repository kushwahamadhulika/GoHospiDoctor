package com.example.gohospidoctor.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohospidoctor.ModalClass.ViewBookingDetailsModal;
import com.example.gohospidoctor.R;

import java.util.List;

public class ViewBookingAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ViewBookingDetailsModal>viewBookingDetailsModals;
    Context context;

    public ViewBookingAdopter(List<ViewBookingDetailsModal> viewBookingDetailsModals, Context context) {
        this.viewBookingDetailsModals = viewBookingDetailsModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookingview_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder myholder=(ViewHolder)holder;
       ViewBookingDetailsModal viewBookingDetailsModal=viewBookingDetailsModals.get(position);
        myholder.patient_name.setText(viewBookingDetailsModal.getPatient_name());
        myholder.patient_age.setText(viewBookingDetailsModal.getPatient_age());
        myholder.patient_sex.setText(viewBookingDetailsModal.getPatient_sex());
        myholder.patient_number.setText(viewBookingDetailsModal.getPatient_mobile());


    }

    @Override
    public int getItemCount() {
        return viewBookingDetailsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView patient_age,patient_name,patient_sex,patient_number;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            patient_name=itemView.findViewById(R.id.pt_name);
            patient_age=itemView.findViewById(R.id.pt_age);
            patient_sex=itemView.findViewById(R.id.pt_sex);
            patient_number=itemView.findViewById(R.id.pt_phonenumber);
        }
    }
}
