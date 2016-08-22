package com.android.softtek.survapp.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionData {

    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionData(Context ctx) {
        context = ctx;
        pref = context.getSharedPreferences(Constants.PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserSession(String email) {
        editor.putString(Constants.KEY_EMAIL, email);
        editor.commit();
    }

    public String getUserSession() {
        return pref.getString(Constants.KEY_EMAIL, "");
    }

    public void setValidationCode(int code) {
        editor.putInt(Constants.KEY_CODE, code);
        editor.commit();
    }

    public int getValidationCode() {
        return pref.getInt(Constants.KEY_CODE, 0);
    }

    public void userSessionOut() {
        editor.clear();
        editor.commit();
    }
}
