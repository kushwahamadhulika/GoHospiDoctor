package com.example.gohospidoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gohospidoctor.Fragment.HomeFragment;
import com.example.gohospidoctor.IpadManagement.AppConstent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SetCalanderActivity extends AppCompatActivity {

    EditText et_location,et_member;
    Button et_addshift;
    StringRequest stringRequest;

    String url1="http://linkup.site/hospital/App/set_calendar";
    String ShortLocationUrl="http://linkup.site/hospital/App/get_location/";

    RequestQueue requestQueue;
    String doctorid="1";
    private Calendar mcalendar;
    private EditText mdob_et;
    private int day,month,year;
    String et_id;
    Spinner spinner;
    // Button datePicker;
    TextView pic_date,et_starttime,et_endtime;
    TextView tv_doctorname;
    String Doctor;
    ArrayList<String> LocationItem;

    // Button btn_starttime,btn_endtime;
    private int mYear, mMonth, mDay, mHour, mMinute,am_pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_calander);

        et_starttime=findViewById(R.id.et_from);
        et_endtime=findViewById(R.id.end_time);
        spinner=findViewById(R.id.location_list);
        et_member=findViewById(R.id.number_ofbooking);
        et_addshift=findViewById(R.id.et_addshift);
        pic_date=findViewById(R.id.set_date);
        tv_doctorname=findViewById(R.id.tv_doctorName);
        // btn_endtime=view.findViewById(R.id.btn_endtime);
        // btn_starttime=view.findViewById(R.id.btn_starttime);

        Doctor= SessionHandler.getInstance().get(getApplicationContext(), AppConstent.doctorname);
        et_id= SessionHandler.getInstance().get(getApplicationContext(), AppConstent.userID);

        tv_doctorname.setText(Doctor);
        LocationItem = new ArrayList<>();
        getLocationList();


        pic_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                Toast.makeText(getApplicationContext(),mDay+ "", Toast.LENGTH_SHORT).show();
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetCalanderActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                pic_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        et_starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(SetCalanderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                et_starttime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        et_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(SetCalanderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                et_endtime .setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                spinner.getSelectedItemId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        et_addshift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_member.getText().toString().trim().length()==0){
                    et_member.setError("");
                    et_member.requestFocus();
                }
                else {
                    setPostData();
                }
            }
        });
    }
    public void getLocationList()
    {
        String locationUrl=ShortLocationUrl+et_id;
        StringRequest stringRequest1=new StringRequest(Request.Method.POST,locationUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String sort_location=jsonObject.getString("short_address");
                        String locationid=jsonObject.getString("doctor_location_id");
                        LocationItem.add(sort_location);

                    }
                    spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,LocationItem));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue1= Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);
    }
    public void setPostData()
    {
        final String starttime=et_starttime.getText().toString().trim();
        final String endtime=et_endtime.getText().toString().trim();
        final String member=et_member.getText().toString().trim();
        // final String location=et_location.getText().toString().trim();
        final String et_date=pic_date.getText().toString().trim();


        requestQueue= Volley.newRequestQueue(getApplicationContext());
        stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    final String status=jsonObject.getString("status");
                    final String message=jsonObject.getString("message");
                    if (status.equalsIgnoreCase("success") && message.equalsIgnoreCase("Add Successfully"))
                    {
                        Intent I=new Intent(getApplicationContext(), ViewCalanderActivity.class);
                        startActivity(I);

                        Toast.makeText(getApplicationContext(),message+ "Successful", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error+ "", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>map=new HashMap<>();
                map.put("from_time",starttime);
                map.put("to_time",endtime);
                map.put("location","1");
                map.put("no_patient",member);
                map.put("doctorid",et_id);
                map.put("date",et_date);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }

}
