package com.android.softtek.survapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.softtek.survapp.Adapters.SurveyQuestionsAdapter;
import com.android.softtek.survapp.Controllers.HomeController;
import com.android.softtek.survapp.Models.Question;
import com.android.softtek.survapp.R;

import java.util.ArrayList;

public class SurveyActivity extends Activity implements View.OnClickListener {

    private ListView mQuestionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        mQuestionsList = (ListView) findViewById(R.id.list_questions);
        mQuestionsList.setAdapter(new SurveyQuestionsAdapter(this, new HomeController().getQuestionsList(this)));
        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ArrayList<Question> list = new ArrayList<>();
        View row;
        EditText et;
        Question question;
        ListAdapter adapter = mQuestionsList.getAdapter();

        for (int i = 0; i < adapter.getCount(); i++) {
            row = adapter.getView(i, null, null);
            question = (Question) adapter.getItem(i);

            switch (question.getTipo()) {
                case 0:
                    et = (EditText) row.findViewById(R.id.et_text);
                    question.setAnswer(et.getText().toString());
                    break;
                case 1:
                    et = (EditText) row.findViewById(R.id.et_text_area);
                    question.setAnswer(et.getText().toString());
                    break;
                case 2:
                    RadioGroup rg = (RadioGroup) row.findViewById(R.id.radioG);
                    question.setAnswer(rg.getChildAt(rg.getCheckedRadioButtonId()).toString());
                    break;
                case 3:
                    Spinner spinner = (Spinner) row.findViewById(R.id.spinner);
                    question.setAnswer(String.valueOf(spinner.getSelectedItemPosition()));
                    break;
            }
            list.set(i, question);
        }

        question = (Question) adapter.getItem(1);
        Toast.makeText(this, question.getQuestion(), Toast.LENGTH_LONG).show();
    }
}
