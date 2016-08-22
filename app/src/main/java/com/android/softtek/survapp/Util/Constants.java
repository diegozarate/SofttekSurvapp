package com.android.softtek.survapp.Util;

public class Constants {

    //Fragment positions
    public static final int F_LOGIN = 0;
    public static final int F_REGISTER = 1;
    public static final int F_VALIDATE = 2;
    public static final int F_RESTORE = 3;
    public static final int F_SET_PASSWORD = 4;

    //Validate Register
    public static final int EMPTY_SPACES = 0;
    public static final String EMPTY_MESSAGE = "Debes llenar todos los espacios";
    public static final int EMAIL_FAIL = 1;
    public static final String EMAIL_MESSAGE = "Debes usar tu cuenta de Softtek";
    public static final int PASSWORD_LENGHT = 2;
    public static final String PASSWORD_MESSAGE = "La contraseña debe ser de al menos 6 caracteres";
    public static final int PASSWORD_FAIL = 3;
    public static final String PASSWORD_MESSAGE2 = "Las contraseñas no coinciden";
    public static final int REGISTER_VALIDATED = 4;

    //Messages
    public static final String CODE_VALIDATION_MESSAGE = "El código es incorrecto";

    // Shared preferences file names
    public static final String PREFER_NAME = "Survapp_pref";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CODE = "validation_code";
}
