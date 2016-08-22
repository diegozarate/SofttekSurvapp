package com.android.softtek.survapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.softtek.survapp.R;
import com.android.softtek.survapp.Util.SessionData;

public class FragmentValidation extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_validation, container, false);
        EditText et = (EditText) view.findViewById(R.id.etCode);
        SessionData pref = new SessionData(getContext());
        //et.setText(pref.getValidationCode());
        return view;
    }
}
