package com.example.gohospidoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.Adopter.CalanderAdopter;
import com.example.gohospidoctor.Adopter.ViewBookingAdopter;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.ModalClass.ViewBookingDetailsModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookingViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ViewBookingDetailsModal> viewBookingDetailsModals;
    ViewBookingAdopter adapter;

    String doctor_calendarid, doctorid, from_date;
   String url = "http://linkup.site/hospital/App/doctor_view_booking/1/14";
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);

        ShowData();

        recyclerView = findViewById(R.id.recyclerview);


        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        viewBookingDetailsModals= new ArrayList<>();
        recyclerView.setHasFixedSize(true);




        doctorid = getIntent().getExtras().getString("doctorid");
        doctor_calendarid = getIntent().getExtras().getString("doctor_calendar_id");

        Toast.makeText(this, doctor_calendarid + "", Toast.LENGTH_SHORT).show();

    }

    public void ShowData() {
        String Pt_Url = url +doctorid +doctor_calendarid;


                           StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   try {
                                       JSONObject jsonObject=new JSONObject(response);
                                       JSONArray jsonArray=jsonObject.getJSONArray("data");
                                       for (int i=0;i<jsonArray.length();i++)
                                       {
                                           JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                           ViewBookingDetailsModal viewBookingDetailsModal=new ViewBookingDetailsModal(jsonObject1.getString("patient_name"),jsonObject1.getString("age"),
                                                   jsonObject1.getString("sex"),jsonObject1.getString("mobile"));
                                           viewBookingDetailsModals.add(viewBookingDetailsModal);

                                       }
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                                   adapter= new ViewBookingAdopter(viewBookingDetailsModals, BookingViewActivity.this);

                                   recyclerView.setAdapter(adapter);


                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   Toast.makeText(BookingViewActivity.this, error+"", Toast.LENGTH_SHORT).show();
                               }
                           });
                           RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                           requestQueue.add(stringRequest);
        }


    }

