package com.android.softtek.survapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.android.softtek.survapp.Controllers.HomeController;
import com.android.softtek.survapp.R;

public class FragmentCalendar extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendar = (CalendarView) view.findViewById(R.id.calendar);

        new HomeController().initializeCalendar(getContext(), calendar);

        return view;
    }
}
