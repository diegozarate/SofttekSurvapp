package com.android.softtek.survapp.Models;

public class SurveyItem {

    private String surveyName;
    private String surveyDate;

    public SurveyItem(String surveyName, String surveyDate) {
        this.surveyName = surveyName;
        this.surveyDate = surveyDate;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }
}
