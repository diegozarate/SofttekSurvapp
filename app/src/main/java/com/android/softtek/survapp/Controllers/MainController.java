package com.android.softtek.survapp.Controllers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.softtek.survapp.R;
import com.android.softtek.survapp.Util.Constants;
import com.android.softtek.survapp.Util.SessionData;

import java.util.Random;

public class MainController {

    private SessionData pref;

    public void onClickController(ViewPager pager, View btnFrom, Context ctx) {
        LinearLayout layout;
        LinearLayout layout2;
        pref = new SessionData(ctx);
        switch (btnFrom.getId()) {
            //Registration actions
            case R.id.btnRegister:
                pager.setCurrentItem(Constants.F_REGISTER);
                break;
            case R.id.btnSubmit:
                layout = (LinearLayout) btnFrom.getParent();
                switch (validateRegister(layout)) {
                    case Constants.EMPTY_SPACES:
                        Toast.makeText(layout.getContext(), Constants.EMPTY_MESSAGE,
                                Toast.LENGTH_LONG).show();
                        break;
                    case Constants.EMAIL_FAIL:
                        Toast.makeText(ctx, Constants.EMAIL_MESSAGE,
                                Toast.LENGTH_LONG).show();
                        break;
                    case Constants.PASSWORD_LENGHT:
                        Toast.makeText(ctx, Constants.PASSWORD_MESSAGE,
                                Toast.LENGTH_LONG).show();
                        break;
                    case Constants.PASSWORD_FAIL:
                        Toast.makeText(ctx, Constants.PASSWORD_MESSAGE2,
                                Toast.LENGTH_LONG).show();
                        break;
                    case Constants.REGISTER_VALIDATED:
                        pager.setCurrentItem(Constants.F_VALIDATE);
                        generateCode(ctx);
                        Toast.makeText(ctx, "Codigo: " + pref.getValidationCode(),
                                Toast.LENGTH_LONG).show();
                        ///////////////////////////////////Insert code
                        break;
                }
                break;
            case R.id.btnValidate:
                layout = (LinearLayout) btnFrom.getParent();
                EditText et = (EditText) layout.findViewById(R.id.etCode);
                if (!et.getText().toString().equals("")) {
                    if (validateCodes(Integer.valueOf(et.getText().toString()), ctx))
                        pager.setCurrentItem(Constants.F_LOGIN);
                    else
                        Toast.makeText(ctx, Constants.CODE_VALIDATION_MESSAGE,
                                Toast.LENGTH_LONG).show();
                }
                break;
            // Restore Pasword actions
            case R.id.tvRestore:
                pager.setCurrentItem(Constants.F_RESTORE);
                break;
            case R.id.btnSendCode:
                layout = (LinearLayout) btnFrom.getParent();
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_correo);
                layout2.setVisibility(View.GONE);
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_codigo);
                layout2.setVisibility(View.VISIBLE);
                //////////////////////////Insert code
                break;
            case R.id.tvHaveCode:
                layout = (LinearLayout) btnFrom.getParent();
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_correo);
                layout2.setVisibility(View.GONE);
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_codigo);
                layout2.setVisibility(View.VISIBLE);
                break;
            case R.id.btnValidateRestore:
                pager.setCurrentItem(Constants.F_SET_PASSWORD);
                /////////////////////////Insert code
                break;
            case R.id.btnSetPass:
                pager.setCurrentItem(Constants.F_LOGIN);
                ///////////////////////7/Insert code
                break;
        }
    }

    public int validateRegister(View v) {
        EditText etMail = (EditText) v.findViewById(R.id.etMail);
        EditText etPass = (EditText) v.findViewById(R.id.etPass);
        EditText etPass2 = (EditText) v.findViewById(R.id.etPass2);
        String mail, pass, pass2;

        if (!isEmpty(etMail) && !isEmpty(etPass) && !isEmpty(etPass2)) {

            mail = etMail.getText().toString().trim();
            pass = etPass.getText().toString().trim();
            pass2 = etPass2.getText().toString().trim();

            if (validateMail(mail)) {

                if (validatePassword(pass)) {
                    if (comparePasswords(pass, pass2)) {
                        // Aqui se inicia el WS de registro
                        /////////////Insert code
                        return Constants.REGISTER_VALIDATED;
                        /////////////////////
                    } else {
                        restartRegister(etMail, etPass2, etPass, 1);
                        return Constants.PASSWORD_FAIL;
                    }
                } else {
                    restartRegister(etMail, etPass2, etPass, 1);
                    return Constants.PASSWORD_LENGHT;
                }

            } else {
                restartRegister(etMail, etPass2, etPass, 0);
                return Constants.EMAIL_FAIL;
            }
        } else {
            return Constants.EMPTY_SPACES;
        }

    }

    public boolean validateMail(String mail) {
        return mail.matches("[a-z]+\\.[a-z]+@softtek.com");
    }

    public boolean comparePasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    public boolean validatePassword(String pass) {
        return 6 <= pass.length();
    }

    public void restartRegister(EditText et1, EditText et2, EditText et3, int code) {
        if (code == 0) {
            et1.setText("");
            et2.setText("");
            et3.setText("");
        } else {
            et2.setText("");
            et3.setText("");
        }

    }

    public boolean isEmpty(EditText et) {
        return et != null && et.getText().toString().trim().length() == 0;
    }

    public void generateCode(Context ctx) {
        Random rand = new Random();
        int randomNum = rand.nextInt((999999 - 100000) + 1) + 100000;
        pref = new SessionData(ctx);
        pref.setValidationCode(randomNum);
    }

    public boolean validateCodes(int code, Context ctx) {
        pref = new SessionData(ctx);
        return code == pref.getValidationCode();
    }
}
