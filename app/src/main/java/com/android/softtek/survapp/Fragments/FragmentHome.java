package com.android.softtek.survapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.softtek.survapp.Activities.SurveyActivity;
import com.android.softtek.survapp.Adapters.ListSurveyAdapter;
import com.android.softtek.survapp.Controllers.HomeController;
import com.android.softtek.survapp.R;

public class FragmentHome extends Fragment {

    private ListView mSurveyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSurveyList = (ListView) view.findViewById(R.id.list_survey);
        ListSurveyAdapter adapter = new ListSurveyAdapter(getContext(), new HomeController().getSurveyList(getContext()));
        mSurveyList.setAdapter(adapter);

        mSurveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), SurveyActivity.class));
            }
        });

        return view;
    }
}
