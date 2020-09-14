package com.example.gohospidoctor.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.LoginModel;
import com.example.gohospidoctor.MySharedpreferences;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SessionHandler;
import com.example.gohospidoctor.ViewCalanderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    Button Sign_up, login;
    EditText et_mobile, et_password;
    RadioButton radioButton1;
    String loginurl = "http://linkup.site/hospital/App/login_as";
    ProgressDialog progressDialog;
    String type = "String";
    RadioButton doctor, hospita, diagonatic;
    RadioGroup radioGroupbutton;
    String yourVote;
    String str = "";
    private ProgressDialog dialog;


    String rdValue = "null";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.login_fragment, container, false);
        //Sign_up=view.findViewById(R.id.sign_up);

        try {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog = new ProgressDialog(getContext());

        login = view.findViewById(R.id.login);
        et_mobile = view.findViewById(R.id.et_mobile);
        et_password = view.findViewById(R.id.phone_password);
        doctor = view.findViewById(R.id.set_doctor);
        hospita = view.findViewById(R.id.set_hospital);
        diagonatic = view.findViewById(R.id.radia_id3);
        radioGroupbutton = (RadioGroup) view.findViewById(R.id.groupradio);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_mobile.getText().toString().trim().length() == 0) {
                    et_mobile.setError("Enter Mobile No");
                    et_mobile.requestFocus();
                }
                if (et_password.getText().toString().trim().length() == 0) {
                    et_password.setError("Enter Password");
                    et_password.requestFocus();
                } else if (rdValue.equalsIgnoreCase("null")) {
                    Toast.makeText(getContext(), "Select Login Type", Toast.LENGTH_SHORT).show();
                } else {
                    setLogin();

                }

            }
        });


        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdValue = "Doctor";
            }
        });

        hospita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdValue = "Hospital";
            }
        });

        diagonatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdValue = "Diagonatic";
            }
        });


        return view;

    }


    public void setLogin() {
        // dialog.setMessage("Doing something, please wait.");
        dialog.show();

        final String mobile = et_mobile.getText().toString().trim();
        final String pass = et_password.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    final String status = object.getString("status");
                    final String message = object.getString("message");


                    if (status.equalsIgnoreCase("success") && message.equalsIgnoreCase("Successful")) {
                        JSONArray jsonArray = object.getJSONArray("data");

                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                         String loginid = jsonObject1.getString("id");
                         String doctorName=jsonObject1.getString("name");

                        Toast.makeText(getContext(), message + "", Toast.LENGTH_SHORT).show();
                        SessionHandler.getInstance().save(getContext(), AppConstent.userID, loginid);
                        SessionHandler.getInstance().save(getContext(),AppConstent.doctorname,doctorName);

                        goDashNoard();

                    } else {
                        Toast.makeText(getContext(), message + "", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("mobile", mobile);
                parms.put("password", pass);
                parms.put("type",rdValue);
                return parms;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private void goDashNoard() {
        Fragment mFragment = null;
        mFragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearlayout, mFragment).addToBackStack("login").commit();
    }
}



