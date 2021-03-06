package com.android.softtek.survapp.Controllers;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.softtek.survapp.Activities.MainActivity;
import com.android.softtek.survapp.Fragments.FragmentValidation;
import com.android.softtek.survapp.Models.DrawerItem;
import com.android.softtek.survapp.Models.Question;
import com.android.softtek.survapp.Models.SurveyItem;
import com.android.softtek.survapp.R;
import com.android.softtek.survapp.Util.SessionData;

import java.util.ArrayList;

public class HomeController {

    public ArrayList<DrawerItem> getDrawerList(Context ctx) {
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        String[] titles = ctx.getResources().getStringArray(R.array.nav_drawer_items);
        int[] icons = ctx.getResources().getIntArray(R.array.nav_drawer_icons);

        for (int i = 0; i < titles.length; i++) {
            DrawerItem item = new DrawerItem();
            item.setTitle(titles[i]);
            item.setIcon(icons[i]);

            items.add(item);
        }
        return items;
    }

    //Test Method
    public ArrayList<Question> getQuestionsList(Context ctx) {
        ArrayList<Question> items = new ArrayList<Question>();
        String[] preguntas = ctx.getResources().getStringArray(R.array.questions_test);
        String[] opciones = ctx.getResources().getStringArray(R.array.opciones_test);
        int[] tipos = ctx.getResources().getIntArray(R.array.tipos_prueba);

        for (int i = 0; i < preguntas.length; i++) {
            Question item = new Question();
            item.setQuestion(preguntas[i]);
            item.setTipo(tipos[i]);
            if (tipos[i] == 2 || tipos[i] == 3) {
                item.setOpciones(opciones);
            }

            items.add(item);
        }
        return items;
    }

    //Test Method
    public ArrayList<SurveyItem> getSurveyList(Context ctx) {
        ArrayList<SurveyItem> items = new ArrayList<SurveyItem>();
        String[] names = ctx.getResources().getStringArray(R.array.survey_test);
        String[] dates = ctx.getResources().getStringArray(R.array.survey_date_test);

        for (int i = 0; i < names.length; i++) {
            SurveyItem item = new SurveyItem(names[i], dates[i]);
            items.add(item);
        }
        return items;
    }

    public void initializeCalendar(final Context ctx, CalendarView calendar) {
        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);
        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);
        //sets the color for the dates of an unfocused month.
        //calendar.setUnfocusedMonthDateColor(ctx.getResources().getColor(R.color.transparent));
        //sets the color for the separator line between weeks.
        //calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        //calendar.setSelectedDateVerticalBar(R.color.colorPrimaryDark);
        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(ctx, day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createRadioButtons(View view, String[] options) {
        RadioButton[] buttons = new RadioButton[5];
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radioG);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new RadioButton(view.getContext());
            buttons[i].setText(options[i]);
            buttons[i].setId(i);

            rg.addView(buttons[i]);
        }
    }

    public ArrayAdapter<String> getAdapterSpinner(Context ctx) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, ctx.getResources().getStringArray(R.array.opciones_test));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return spinnerArrayAdapter;
    }

    public void logOut(Context ctx) {
        new SessionData(ctx).userSessionOut();
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public AlertDialog.Builder getDialog(Context ctx, String title, String message) {
        return new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message);
    }
}
