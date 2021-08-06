package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.CalendarConstraints;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class FragReservation extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationSub fragReservationSub;
    MaterialCalendarView calendarView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = getView().findViewById(R.id.mcv_calendar_reser);
        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);
        calendarView.setTopbarVisible(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationSub = new FragReservationSub();

        rootView.findViewById(R.id.btn_reser_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationSub);
            }
        });
        return rootView;
    } // onCreateView()


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    }
} // class
