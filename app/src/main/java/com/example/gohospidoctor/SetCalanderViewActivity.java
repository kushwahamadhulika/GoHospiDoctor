package com.example.gohospidoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.example.gohospidoctor.Adopter.SetClanderViewAdopter;
import com.example.gohospidoctor.IpadManagement.AppConstent;
import com.example.gohospidoctor.ModalClass.CalendrClass;
import com.example.gohospidoctor.ModalClass.SetCalenedrModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class SetCalanderViewActivity extends AppCompatActivity {

    private HorizontalCalendar horizontalCalendar;
    String et_id;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<SetCalenedrModal> setCalenedrModals;
    Button btn_setCalender;
    HorizontalCalendarView horizontalCalendarView;
    String formattedDate;
    SimpleDateFormat df;
    String url="http://linkup.site/hospital/App/get_calendar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_calander_view);
        recyclerView=findViewById(R.id.show_data);
        recyclerView.setHasFixedSize(true);
        btn_setCalender=findViewById(R.id.btnset_Calendar);

        et_id= SessionHandler.getInstance().get(SetCalanderViewActivity.this, AppConstent.userID);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        sendRequest();
        setCalenedrModals= new ArrayList<>();

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
                //Toast.makeText(SetCalanderActivity.this, defaultSelectedDate+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(SetCalanderViewActivity.this, defaultSelectedDate+"", Toast.LENGTH_SHORT).show();

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
                    SetCalenedrModal setCalenedrModal=new SetCalenedrModal();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (!formattedDate.equals("0")) {
                            setCalenedrModal.setFrom_time(jsonObject.getString("from_time"));
                            setCalenedrModal.setTo_time(jsonObject.getString("to_time"));
                            setCalenedrModal.setLocation(jsonObject.getString("location"));
                            setCalenedrModal.setNo_patient(jsonObject.getString("no_patient"));
                           setCalenedrModal.setDoctor_calendar_id(jsonObject.getString("doctor_calendar_id"));
                           setCalenedrModal.setDoctorid(jsonObject.getString("doctorid"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    setCalenedrModals.add(setCalenedrModal);

                }

                mAdapter = new SetClanderViewAdopter(setCalenedrModals,SetCalanderViewActivity.this);

                recyclerView.setAdapter(mAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(SetCalanderActivity.this, error+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(SetCalanderViewActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

    }
}