package com.example.gohospidoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.Adopter.CalanderAdopter;
import com.example.gohospidoctor.Fragment.LoginFragment;
import com.example.gohospidoctor.Fragment.SetCalenderFragment;
import com.example.gohospidoctor.Fragment.SetFeeFragment;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.ModalClass.CalendrClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class ViewCalanderActivity extends AppCompatActivity {
    private HorizontalCalendar horizontalCalendar;
    String et_id;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<CalendrClass> calendrClassList;
    Button btn_setCalender;
    HorizontalCalendarView horizontalCalendarView;
     String formattedDate;
    SimpleDateFormat df;
    String url="http://linkup.site/hospital/App/get_calendar/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calander);
        recyclerView=findViewById(R.id.show_data);
        recyclerView.setHasFixedSize(true);
        btn_setCalender=findViewById(R.id.btnset_Calendar);

        et_id= SessionHandler.getInstance().get(getApplicationContext(), AppConstent.userID);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
         sendRequest();
        calendrClassList = new ArrayList<>();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_WEEK, 7);
        Calendar startDate = Calendar.getInstance();
        final Calendar defaultSelectedDate = Calendar.getInstance();
        df=new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(defaultSelectedDate.getTime());

        Toast.makeText(this,formattedDate+ "", Toast.LENGTH_SHORT).show();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
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
                .defaultSelectedDate(defaultSelectedDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(ViewCalanderActivity.this, defaultSelectedDate+"", Toast.LENGTH_SHORT).show();

            }
        });


      btn_setCalender.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent I=new Intent(ViewCalanderActivity.this,SetCalanderActivity.class);
              startActivity(I);


          }
      });

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
                         if (!formattedDate.equals("0")) {
                             calendrClass.setFrom_time(jsonObject.getString("from_time"));
                             calendrClass.setTo_time(jsonObject.getString("to_time"));
                             calendrClass.setLocation(jsonObject.getString("location"));
                             calendrClass.setNo_patient(jsonObject.getString("no_patient"));

                         }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    calendrClassList.add(calendrClass);

                }

                mAdapter = new CalanderAdopter(calendrClassList, ViewCalanderActivity.this);

                recyclerView.setAdapter(mAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewCalanderActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
}