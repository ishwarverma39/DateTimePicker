package com.livtech.datetimepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends Fragment {
    private Date minDate;
    private Date maxDate;
    private DatePicker datePicker;
    private Calendar calendar;
    private DatePicker.OnDateChangedListener dateChangedListener;


    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void setDateChangedListener(DatePicker.OnDateChangedListener dateChangedListener) {
        this.dateChangedListener = dateChangedListener;
    }

    public void setSelectedDate(Calendar calendar) {
        this.calendar = calendar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        datePicker = view.findViewById(R.id.date_picker);
        setMinMaxDate();
        setSelectedDate();
    }

    private void setMinMaxDate() {
        if (maxDate != null) datePicker.setMaxDate(maxDate.getTime());
        if (minDate != null) datePicker.setMinDate(minDate.getTime());
    }

    private void setSelectedDate() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        datePicker.init(year, month, date, dateChangedListener);
    }
}
