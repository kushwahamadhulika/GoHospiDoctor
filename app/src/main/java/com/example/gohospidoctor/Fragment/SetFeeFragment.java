package com.example.gohospidoctor.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SessionHandler;
import com.example.gohospidoctor.ViewCalanderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetFeeFragment extends Fragment {

    EditText et_fee,et_Follqwup_fee;
    Button update;
    String setfee1="http://linkup.site/hospital/App/set_fee/";
    String location_Url="http://linkup.site/hospital/App/get_location/";
    String et_id;
    private ProgressDialog dialog;
    ArrayList<String>LocationItem;
    String locationid;
    Spinner spinner;
    TextView tv_doctorname;
    String DoctorName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setfee_fragment, container, false);
        getLocationList();


        dialog=new ProgressDialog(getContext());
        LocationItem = new ArrayList<>();

        et_id= SessionHandler.getInstance().get(getContext(), AppConstent.userID);
        et_fee=view.findViewById(R.id.et_fee);
        tv_doctorname=view.findViewById(R.id.tv_doctorName);
       et_Follqwup_fee=view.findViewById(R.id.et_followupfee);
       spinner=view.findViewById(R.id.location_list);
       update=view.findViewById(R.id.set_fee);
        DoctorName= SessionHandler.getInstance().get(getContext(), AppConstent.doctorname);
        tv_doctorname.setText(DoctorName);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            spinner.getSelectedItemId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (et_fee.getText().toString().trim().length() == 0) {
                    et_fee.setError("");
                    et_fee.requestFocus();
                }
                if (et_Follqwup_fee.getText().toString().trim().length() == 0) {
                    et_Follqwup_fee.setError("");
                    et_Follqwup_fee.requestFocus();
                } else {



                    UpdateFee();
                }
            }
        });
 return view;
    }
//start get location section
    public void getLocationList()
    {
        String locationUrl=location_Url+1;
        StringRequest stringRequest1=new StringRequest(Request.Method.POST,locationUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String sort_location=jsonObject.getString("short_address");
                               locationid=jsonObject.getString("doctor_location_id");
                        LocationItem.add(sort_location);

                    }
                    spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,LocationItem));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue1=Volley.newRequestQueue(getContext());
        requestQueue1.add(stringRequest1);
    }//end get location section

    // start set fee section
    public void UpdateFee()
    {
        dialog.show();
       final String fee = et_fee.getText().toString().trim();
      final String  followupfee = et_Follqwup_fee.getText().toString().trim();


        StringRequest stringRequest=new StringRequest(Request.Method.POST,setfee1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    final String status = jsonObject.getString("status");
                    final String message = jsonObject.getString("message");
                    if (status.equalsIgnoreCase("success") && message.equalsIgnoreCase("Update Successfully")) {
                        Fragment mFragment = null;
                        mFragment = new HomeFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.linearlayout, mFragment).addToBackStack("login").commit();

                        Toast.makeText(getContext(), message + "", Toast.LENGTH_SHORT).show();

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
                dialog.dismiss();
            }
        }){@Override
        protected Map<String, String> getParams()  {
        Map<String,String>map=new HashMap<>();
        map.put("fee",fee);
        map.put("followup_fee",followupfee);
        map.put("doctor_location_id","1");

         return map;
    }

            };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    //end fee section
}
