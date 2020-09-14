package com.example.gohospidoctor.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohospidoctor.BookingViewActivity;
import com.example.gohospidoctor.ModalClass.CalendrClass;
import com.example.gohospidoctor.ModalClass.SetCalenedrModal;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SetCalanderActivity;
import com.example.gohospidoctor.ViewDetailsActivity;

import java.util.List;

public class SetClanderViewAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SetCalenedrModal> setCalenedrModals;
    Context context;

    public SetClanderViewAdopter(List<SetCalenedrModal> setCalenedrModals, Context context) {
        this.setCalenedrModals = setCalenedrModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_calenderviewdetaile, parent, false);
        return new SetClanderViewAdopter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myholder=(ViewHolder) holder;
        final SetCalenedrModal setCalenedrModal=setCalenedrModals.get(position);
        myholder.from_date.setText(setCalenedrModal.getFrom_time());
        myholder.to_date.setText(setCalenedrModal.getTo_time());
        myholder.location.setText(setCalenedrModal.getLocation());
        myholder.member_no.setText(setCalenedrModal.getNo_patient());

        myholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(context, BookingViewActivity.class);

               // I.putExtra("doctor_calendar_id",setCalenedrModal.getDoctor_calendar_id());
                I.putExtra("doctorid",setCalenedrModal.getDoctorid());
                I.putExtra("doctor_calendar_id",setCalenedrModal.getDoctor_calendar_id());
                   context.startActivity(I);
            }
        });

    }

    @Override
    public int getItemCount() {
        return setCalenedrModals.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from_date,to_date,location,member_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            from_date=itemView.findViewById(R.id.view_from);
            to_date=itemView.findViewById(R.id.view_to);
            location=itemView.findViewById(R.id.view_location);
            member_no=itemView.findViewById(R.id.view_no);

        }
    }
    }
