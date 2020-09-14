package com.example.gohospidoctor.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohospidoctor.Adopter.CustomAdapter;
import com.example.gohospidoctor.ModalClass.GPSTracker;
import com.example.gohospidoctor.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DashBoardFragment extends Fragment {

    GPSTracker gpsTracker;
    String Address;
    TextView location_show;

    ArrayList personNames = new ArrayList<>(Arrays.asList("Set \nCalender", "View \nBooking", "Running \n Late/Early", "Cancel \n Appointments", "Change\n Fee", "Add New \n Location"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.calenderset, R.drawable.cal, R.drawable.locat, R.drawable.cancle,
            R.drawable.inr, R.drawable.locat));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        RecyclerView recyclerView =view. findViewById(R.id.recyclerview);



       //GPS Code
        gpsTracker = new GPSTracker(getContext());
        Address = gpsTracker.getLocality(getContext());
        location_show.setText(Address);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),linearLayoutManager.HORIZONTAL, false));

  CustomAdapter customAdapter = new CustomAdapter(personNames,personImages,getContext());
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        return view;
    }

    }
