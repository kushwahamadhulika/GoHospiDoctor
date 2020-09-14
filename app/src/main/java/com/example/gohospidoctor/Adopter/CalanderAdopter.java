package com.example.gohospidoctor.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohospidoctor.ModalClass.CalendrClass;
import com.example.gohospidoctor.ModalClass.DocLocation;
import com.example.gohospidoctor.R;

import java.util.List;

public class CalanderAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<CalendrClass> calendrClasses;
    Context context;

    public CalanderAdopter(List<CalendrClass> calendrClasses, Context context) {
        this.calendrClasses = calendrClasses;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewdetails_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ViewHolder myholder=(ViewHolder) holder;
        CalendrClass calendrClass = calendrClasses.get(position);
        myholder.from_date.setText(calendrClass.getFrom_time());
        myholder.to_date.setText(calendrClass.getTo_time());
        myholder.location.setText(calendrClass.getLocation());
        myholder.member_no.setText(calendrClass.getNo_patient());


    }

    @Override
    public int getItemCount() {
        return calendrClasses.size();
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
