package com.example.gohospidoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.gohospidoctor.Fragment.AddDoctorLocationFragment;
import com.example.gohospidoctor.Fragment.HomeFragment;
import com.example.gohospidoctor.Fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout1;
    String pageValue;
    private Log login_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment mFragment = null;
        mFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).commit();

    }
}