package com.livtech.datetimepicker;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends Fragment {

    private Calendar calendar;
    private TimePicker.OnTimeChangedListener timeChangedListener;
    private View view;

    public void setSelectedDate(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setTimeChangedListener(TimePicker.OnTimeChangedListener timeChangedListener) {
        this.timeChangedListener = timeChangedListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_picker, container, false);
        initTimePicker();
        return view;
    }

    private void initTimePicker() {
        TimePicker timePicker = view.findViewById(R.id.time_picker);
        timePicker.setOnTimeChangedListener(timeChangedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(calendar.get(Calendar.MINUTE));
        } else {
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }
    }
}
