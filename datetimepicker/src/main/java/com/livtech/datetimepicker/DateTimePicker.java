package com.livtech.datetimepicker;

import android.support.v4.app.FragmentManager;

import java.util.Date;

public class DateTimePicker {
    private Builder builder;

    private DateTimePicker(Builder builder) {
        this.builder = builder;
    }

    public void show(FragmentManager fragmentManager) {
        builder.fragmentManager = fragmentManager;
        DateTimePickerFragment fragment = new DateTimePickerFragment();
        fragment.setBuilder(builder);
        fragment.show(fragmentManager, "DATE_TIME_PICKER");
    }


    public static class Builder {
        private Date mSelectedDate;
        private Date mMinDate;
        private Date mMaxDate;
        private int mIndicatorColor;
        private DateTimeSetListener dateTimeSetListener;
        private FragmentManager fragmentManager;

        public Builder() {
            mIndicatorColor = -1;
        }

        public Builder setSelectedDate(Date mSelectedDate) {
            this.mSelectedDate = mSelectedDate;
            return this;
        }

        public Builder setMinDate(Date mMinDate) {
            this.mMinDate = mMinDate;
            return this;
        }

        public Builder setMaxDate(Date mMaxDate) {
            this.mMaxDate = mMaxDate;
            return this;
        }

        public Builder setIndicatorColor(int mIndicatorColor) {
            this.mIndicatorColor = mIndicatorColor;
            return this;
        }

        public Builder setDateTimeSetListener(DateTimeSetListener dateTimeSetListener) {
            this.dateTimeSetListener = dateTimeSetListener;
            return this;
        }

        public DateTimePicker build() {
            return new DateTimePicker(this);
        }

        public Date getSelectedDate() {
            return mSelectedDate;
        }

        public Date getMinDate() {
            return mMinDate;
        }

        public Date getMaxDate() {
            return mMaxDate;
        }

        public int getIndicatorColor() {
            return mIndicatorColor;
        }

        public DateTimeSetListener getDateTimeSetListener() {
            return dateTimeSetListener;
        }
    }

}
