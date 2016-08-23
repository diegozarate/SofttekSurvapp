package com.android.softtek.survapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.softtek.survapp.Models.SurveyItem;
import com.android.softtek.survapp.R;

import java.util.ArrayList;

public class ListSurveyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SurveyItem> surveyItems;

    public ListSurveyAdapter(Context context, ArrayList<SurveyItem> surveyItems) {
        this.context = context;
        this.surveyItems = surveyItems;
    }

    @Override
    public int getCount() {
        return surveyItems.size();
    }

    @Override
    public Object getItem(int i) {
        return surveyItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.survey_item_layout, null);
        }

        TextView txtName = (TextView) view.findViewById(R.id.tvSurveyName);
        TextView txtDate = (TextView) view.findViewById(R.id.tvEndDate);

        txtName.setText(surveyItems.get(i).getSurveyName());
        txtDate.setText(surveyItems.get(i).getSurveyDate());

        return view;
    }
}
