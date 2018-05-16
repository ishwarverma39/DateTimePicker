package com.livtech.datetimepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimePickerFragment extends DialogFragment implements DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private DatePickerFragment datePickerFragment;
    private TimePickerFragment timePickerFragment;
    private View view;
    private DateTimePicker.Builder builder;
    private Calendar calendar;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;

    public void setBuilder(DateTimePicker.Builder builder) {
        this.builder = builder;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getLayoutInflater().inflate(R.layout.fragment_date_time_picker, container);
        setInitialDate();
        initButtons();
        initFragments();
        initTabLayout();
        showFragment(0);
        return view;
    }

    private void initFragments() {
        fragmentManager = getChildFragmentManager();
        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setSelectedDate(calendar);
        timePickerFragment.setTimeChangedListener(this);
        fragmentManager.beginTransaction().add(R.id.date_time_picker_container, timePickerFragment, "TIME_PICKER").commit();

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setMaxDate(builder.getMaxDate());
        datePickerFragment.setMinDate(builder.getMinDate());
        datePickerFragment.setSelectedDate(calendar);
        datePickerFragment.setDateChangedListener(this);
        fragmentManager.beginTransaction().add(R.id.date_time_picker_container, datePickerFragment, "DATE_PICKER").commit();
    }

    private void initTabLayout() {
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        if (builder.getIndicatorColor() != -1) {
            tabLayout.setSelectedTabIndicatorColor(builder.getIndicatorColor());
        }

        TabLayout.Tab dateTab = tabLayout.newTab();
        dateTab.setText(getDateText());
        tabLayout.addTab(dateTab);

        TabLayout.Tab timeTab = tabLayout.newTab();
        timeTab.setText(getTimeText());
        tabLayout.addTab(timeTab);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            showFragment(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private void showFragment(int position) {
        if (position == 0) {
            fragmentManager.beginTransaction().show(datePickerFragment).commit();
            fragmentManager.beginTransaction().hide(timePickerFragment).commit();
        } else {
            fragmentManager.beginTransaction().show(timePickerFragment).commit();
            fragmentManager.beginTransaction().hide(datePickerFragment).commit();
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DATE, dayOfMonth);
        updateDateTab();
    }


    private void setInitialDate() {
        calendar = Calendar.getInstance();
        if (builder.getSelectedDate() != null) {
            calendar.setTime(builder.getSelectedDate());
        }
    }

    private void updateDateTab() {
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null)
            tab.setText(getDateText());
    }

    private void updateTimeTab() {
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        if (tab != null)
            tab.setText(getTimeText());
    }

    private String getDateText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d", Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }

    private String getTimeText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        updateTimeTab();
    }

    private void initButtons() {
        Button okButton = view.findViewById(R.id.ok_button);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.getDateTimeSetListener() != null) {
                    builder.getDateTimeSetListener().onDateTimeSet(calendar.getTime());
                }
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

