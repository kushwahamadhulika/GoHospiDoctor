package com.example.gohospidoctor.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.Adopter.CalanderAdopter;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.ModalClass.CalendrClass;
import com.example.gohospidoctor.R;
import com.example.gohospidoctor.SessionHandler;
import com.example.gohospidoctor.ViewCalanderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class ViewCalanderFragment extends Fragment {
    //private HorizontalCalendar horizontalCalendar;

    private HorizontalCalendar horizontalCalendar;
    String et_id;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<CalendrClass> calendrClassList;
    Button btn_setCalender;

    SimpleDateFormat df;
    String url="http://linkup.site/hospital/App/get_calendar/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_calander_fragment, container, false);
        Calendar endDate = Calendar.getInstance();

        recyclerView=view.findViewById(R.id.show_data);
        recyclerView.setHasFixedSize(true);
        btn_setCalender=view.findViewById(R.id.btnset_Calendar);
        et_id= SessionHandler.getInstance().get(getContext(), AppConstent.userID);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        sendRequest();
        calendrClassList = new ArrayList<>();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_WEEK, 7);
        Calendar startDate = Calendar.getInstance();
        df=new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(date.getTime());
        final Calendar defaultSelectedDate = Calendar.getInstance();
        Toast.makeText(getContext(),formattedDate+ "", Toast.LENGTH_SHORT).show();
        btn_setCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new SetCalenderFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

            }
        });
        horizontalCalendar = new HorizontalCalendar.Builder((Activity) getContext(),R.id.calendarView)
                .range(startDate, date)
                .datesNumberOnScreen(7)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.BLACK)
                .end()
                .defaultSelectedDate(startDate).build();
              // Random random=new Random();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //String setdateformate=
            }
        });
        return view;
    }


    public void sendRequest()
    {
        String url1=url+et_id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    CalendrClass calendrClass=new CalendrClass();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        calendrClass.setFrom_time(jsonObject.getString("from_time"));
                        calendrClass.setTo_time(jsonObject.getString("to_time"));
                        calendrClass.setLocation(jsonObject.getString("location"));
                        calendrClass.setNo_patient(jsonObject.getString("no_patient"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    calendrClassList.add(calendrClass);

                }

                mAdapter = new CalanderAdopter(calendrClassList, getContext());

                recyclerView.setAdapter(mAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    }
