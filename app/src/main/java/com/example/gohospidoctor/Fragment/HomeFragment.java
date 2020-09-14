package com.example.gohospidoctor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SessionHandler;
import com.example.gohospidoctor.SetCalanderActivity;
import com.example.gohospidoctor.SetCalanderViewActivity;
import com.example.gohospidoctor.ViewCalanderActivity;

public class HomeFragment extends Fragment {
    CardView add_location,set_calander_card,view_booking_card,chang_fee;
    String DoctorName;
    TextView tv_doctorName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        add_location=view.findViewById(R.id.add_location);
        set_calander_card=view.findViewById(R.id.set_calnder_card);
        view_booking_card=view.findViewById(R.id.card_view_booking);
        chang_fee=view.findViewById(R.id.change_fee);
        tv_doctorName=view.findViewById(R.id.tv_doctorName);

        DoctorName= SessionHandler.getInstance().get(getContext(), AppConstent.doctorname);
        tv_doctorName.setText(DoctorName);


chang_fee.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Fragment mFragment = null;
        mFragment = new SetFeeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

    }
});
set_calander_card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       Intent I=new Intent(getContext(),ViewCalanderActivity.class);
       startActivity(I);
    }
});
view_booking_card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(getContext(), SetCalanderViewActivity.class);
        startActivity(i);
    }
});
        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new AddDoctorLocationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

            }
        });
        return view;
    }
}
