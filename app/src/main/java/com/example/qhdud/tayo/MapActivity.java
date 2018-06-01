package com.example.qhdud.tayo;


import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.example.qhdud.tayo.R.id.create_button;
import static com.example.qhdud.tayo.R.id.time_button;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //create 버튼
        Button create_btn = (Button)findViewById(create_button);
        create_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                TextView CheckAll = (TextView)findViewById(R.id.tv);
                if(CheckAll.getText() == "")
                {
                    (Toast.makeText(getApplicationContext(),"시간을 선택해주세요!",Toast.LENGTH_LONG)).show();
                }
                else {
                    //startActivity(new Intent(this, 채팅.class));
                }
            }
        });

        //시간 설정 버튼
        Button time_btn = (Button) findViewById(time_button);
        time_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Dialog_TimePicker();
            }
        });

        //출발(출발지)
        final String[] Ddata = getResources().getStringArray(R.array.map_departure);
        Spinner departureSpinner = (Spinner)findViewById(R.id.departure);
        ArrayAdapter departureAdapter = ArrayAdapter.createFromResource(this, R.array.map_departure, android.R.layout.simple_spinner_item);
        departureAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        departureSpinner.setAdapter(departureAdapter);
       /* departureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //여기에 Ddata[i] 지도 보여주는거 넣으면 될듯
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });*/
        //도착(목적지)
        final String[] Adata = getResources().getStringArray(R.array.map_arrival);
        Spinner arrivalSpinner = (Spinner)findViewById(R.id.arrival);
        ArrayAdapter arrivalAdapter = ArrayAdapter.createFromResource(this, R.array.map_arrival, android.R.layout.simple_spinner_item);
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        arrivalSpinner.setAdapter(arrivalAdapter);
        /*arrivalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //여기에 Ddata[i] Adata[i] 지도 보여주는거 넣으면 될듯
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });*/
    }

    private void Dialog_TimePicker() {

        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                TextView tv = (TextView)findViewById(R.id.tv);
                tv.setText("");//clear
                tv.clearComposingText();
                int currentHour;
                if(hourOfDay > 12)
                {
                    currentHour = hourOfDay-12;
                    tv.setText(tv.getText() + "오후 "+ String.valueOf(currentHour) + "시" + String.valueOf(minute)+"분");
                }
                else if (hourOfDay == 12)
                {
                    currentHour = hourOfDay;
                    tv.setText(tv.getText() + "오후 "+ String.valueOf(currentHour) + "시" + String.valueOf(minute)+"분");
                }
                else//오전
                {
                    currentHour = hourOfDay;
                    tv.setText(tv.getText() + "오전 " + String.valueOf(currentHour) + "시" + String.valueOf(minute)+"분");
                }

            }
        };
        final Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        TimePickerDialog alert = new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog, mTimeSetListener,hour,minute, false);
        alert.show();
    }

}
