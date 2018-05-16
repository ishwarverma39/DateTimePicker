package com.livtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.livtech.datetimepicker.DateTimePicker;
import com.livtech.datetimepicker.DateTimeSetListener;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateTimePicker dateTimePicker =
                new DateTimePicker.Builder()
                        .setSelectedDate(Calendar.getInstance().getTime())
                        .setDateTimeSetListener(dateTimeSetListener)
                        .build();
        dateTimePicker.show(getSupportFragmentManager());
    }

    private DateTimeSetListener dateTimeSetListener = new DateTimeSetListener() {
        @Override
        public void onDateTimeSet(Date date) {
            Log.d("onDateTimeSet", "===" + date.toString());
        }
    };
}
