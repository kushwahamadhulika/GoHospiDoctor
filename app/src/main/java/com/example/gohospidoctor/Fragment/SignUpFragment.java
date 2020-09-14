package com.example.gohospidoctor.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gohospidoctor.R;

public class SignUpFragment extends Fragment {

    TextView login_back;
            Button getOtp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        login_back=view.findViewById(R.id.login_back);
        getOtp=view.findViewById(R.id.getotp);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

            }
        });

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new OtpFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

            }
        });
        return view;
    }

    }
