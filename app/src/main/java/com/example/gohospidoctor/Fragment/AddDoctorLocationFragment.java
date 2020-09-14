package com.example.gohospidoctor.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.Adopter.CalanderAdopter;
import com.example.gohospidoctor.Adopter.DoctorLocationAdopter;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.ModalClass.CalendrClass;
import com.example.gohospidoctor.ModalClass.DocLocation;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SessionHandler;
import com.example.gohospidoctor.ViewCalanderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDoctorLocationFragment extends Fragment {
    EditText sort_address,long_address;
    Button btn_update,btn_location;
    String setlocation="http://linkup.site/hospital/App/set_location/";
    String url1="http://linkup.site/hospital/App/get_location/";
    String update_location="http://linkup.site/hospital/App/update_location/1";
    String et_id;
    TextView tv_showName;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<DocLocation>docLocations;
    private ProgressDialog dialog;
    String DoctorName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_doctorlocation_fragment, container, false);
        dialog=new ProgressDialog(getContext());
        sort_address=view.findViewById(R.id.sort_address);
        long_address=view.findViewById(R.id.detailed_address);
        btn_update=view.findViewById(R.id.add_location1);
        tv_showName=view.findViewById(R.id.tv_doctorName);
        recyclerView=view.findViewById(R.id.recyclerview);
        DoctorName= SessionHandler.getInstance().get(getContext(), AppConstent.doctorname);
        tv_showName.setText(DoctorName);

        // btn_location=view.findViewById(R.id.view_address);
        recyclerView.setHasFixedSize(true);
        docLocations = new ArrayList<>();

        et_id= SessionHandler.getInstance().get(getContext(), AppConstent.userID);
         // sendRequest();
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
           sendRequest();
          btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sort_address.getText().toString().trim().length()==0){
                    sort_address.setError("");
                    sort_address.requestFocus();
                }
                if(long_address.getText().toString().trim().length()==0){
                    long_address.setError("");
                    long_address.requestFocus();
                }
                else {

                    UpdateLocation();
                }
            }
        });
        return view;
    }
    public void UpdateLocation()
    {
        dialog.show();
        String set_location1=setlocation+et_id;
        final String sort_add=sort_address.getText().toString().trim();
        final String long_add=long_address.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, set_location1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    final String status = jsonObject.getString("status");
                    final String message = jsonObject.getString("message");
                    if (status.equalsIgnoreCase("success") && message.equalsIgnoreCase("Add Successfully")) {
                        Fragment mFragment = null;
                        mFragment = new HomeFragment();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.linearlayout, mFragment).addToBackStack("login").commit();

                        Toast.makeText(getContext(), message + "Successful", Toast.LENGTH_SHORT).show();

                    }

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error+ "", Toast.LENGTH_SHORT).show();
            }
        }){@Override
        protected Map<String, String> getParams()  {
            Map<String,String>map=new HashMap<>();
            map.put("short_address",sort_add);
            map.put("detail_address",long_add);
            map.put("doctorid",et_id);
            return map;
        }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void sendRequest()
    {
        String set_location=url1+et_id;
        JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(Request.Method.POST, set_location, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    DocLocation docLocation=new DocLocation();
                   // Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        docLocation.setShort_address(jsonObject.getString("short_address"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    docLocations.add(docLocation);

                }
                mAdapter = new DoctorLocationAdopter(docLocations,getContext());

                recyclerView.setAdapter(mAdapter);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error+ "", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest1);
    }

}
