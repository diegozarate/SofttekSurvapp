package com.android.softtek.survapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.softtek.survapp.Controllers.HomeController;
import com.android.softtek.survapp.Models.Question;
import com.android.softtek.survapp.R;

import java.util.ArrayList;

public class SurveyQuestionsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Question> questions;

    public SurveyQuestionsAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int i) {
        return questions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater;
        TextView tvQuestion;
        if (view == null) {
            switch (questions.get(i).getTipo()) {
                case 0:
                    mInflater = (LayoutInflater)
                            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    view = mInflater.inflate(R.layout.question_text, null);
                    tvQuestion = (TextView) view.findViewById(R.id.tv_question);
                    tvQuestion.setText(questions.get(i).getQuestion());
                    break;
                case 1:
                    mInflater = (LayoutInflater)
                            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    view = mInflater.inflate(R.layout.question_text_area, null);
                    tvQuestion = (TextView) view.findViewById(R.id.tv_question);
                    tvQuestion.setText(questions.get(i).getQuestion());
                    break;
                case 2:
                    mInflater = (LayoutInflater)
                            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    view = mInflater.inflate(R.layout.question_radio, null);
                    tvQuestion = (TextView) view.findViewById(R.id.tv_question);
                    tvQuestion.setText(questions.get(i).getQuestion());
                    HomeController controller = new HomeController();
                    controller.createRadioButtons(view, questions.get(i).getOpciones());
                    break;
                case 3:
                    mInflater = (LayoutInflater)
                            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    view = mInflater.inflate(R.layout.question_combo, null);
                    tvQuestion = (TextView) view.findViewById(R.id.tv_question);
                    tvQuestion.setText(questions.get(i).getQuestion());
                    Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                    spinner.setAdapter(new HomeController().getAdapterSpinner(view.getContext()));
                    break;
            }
        }
        return view;
    }
}
