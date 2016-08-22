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
        LinearLayout layout, layout2;
        EditText et1, et2;
        pref = new SessionData(ctx);
        switch (btnFrom.getId()) {
            //Registration actions
            case R.id.btnRegister:
                //Send to Registration View
                pager.setCurrentItem(Constants.F_REGISTER);
                break;
            case R.id.btnSubmit:
                //Check the Registration Data
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
                        ///////Remove
                        Toast.makeText(ctx, "Codigo: " + pref.getValidationCode(),
                                Toast.LENGTH_LONG).show();
                        ///////////////////////////////////Insert code
                        //WS to send user info to store in BD
                        // send validation code via email
                        break;
                }
                break;
            case R.id.btnValidate:
                //Account Validate
                layout = (LinearLayout) btnFrom.getParent();
                et1 = (EditText) layout.findViewById(R.id.etCode);
                if (!et1.getText().toString().equals("")) {
                    if (validateCodes(Integer.valueOf(et1.getText().toString()), ctx))
                        pager.setCurrentItem(Constants.F_LOGIN);
                        ////Insert code send validation status to WS
                    else
                        Toast.makeText(ctx, Constants.CODE_VALIDATION_MESSAGE,
                                Toast.LENGTH_LONG).show();
                }
                break;
            // Restore Pasword actions
            case R.id.tvRestore:
                //Send to Restore View
                pager.setCurrentItem(Constants.F_RESTORE);
                break;
            case R.id.btnSendCode:
                //Send Validation code
                layout = (LinearLayout) btnFrom.getParent();
                layout = (LinearLayout) layout.getParent();

                et1 = (EditText) layout.findViewById(R.id.etRestoreMail);
                if (!isEmpty(et1)) {
                    if (validateMail(et1.getText().toString())) {
                        generateCode(ctx);

                        ///////Remove
                        Toast.makeText(ctx, "Codigo: " + pref.getValidationCode(),
                                Toast.LENGTH_LONG).show();
                        //////////

                        ////Launch WS to send Mail and code

                        layout2 = (LinearLayout) layout.findViewById(R.id.layout_correo);
                        layout2.setVisibility(View.GONE);
                        layout2 = (LinearLayout) layout.findViewById(R.id.layout_codigo);
                        layout2.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ctx, "El email no es correcto" + pref.getValidationCode(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(layout.getContext(), Constants.EMPTY_MESSAGE,
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvHaveCode:
                layout = (LinearLayout) btnFrom.getParent();
                layout = (LinearLayout) layout.getParent();
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_correo);
                layout2.setVisibility(View.GONE);
                layout2 = (LinearLayout) layout.findViewById(R.id.layout_codigo);
                layout2.setVisibility(View.VISIBLE);
                ///////Remove
                Toast.makeText(ctx, "Codigo: " + pref.getValidationCode(),
                        Toast.LENGTH_LONG).show();
                //////////
                break;
            case R.id.btnValidateRestore:
                layout = (LinearLayout) btnFrom.getParent();
                et1 = (EditText) layout.findViewById(R.id.etCodeRestore);
                if (!et1.getText().toString().equals("")) {
                    if (validateCodes(Integer.valueOf(et1.getText().toString()), ctx))
                        pager.setCurrentItem(Constants.F_SET_PASSWORD);
                    else
                        Toast.makeText(ctx, Constants.CODE_VALIDATION_MESSAGE,
                                Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSetPass:
                layout = (LinearLayout) btnFrom.getParent();
                et1 = (EditText) layout.findViewById(R.id.etSetPass1);
                et2 = (EditText) layout.findViewById(R.id.etSetPass2);

                if (!isEmpty(et1) && !isEmpty(et2)) {
                    String pass1 = et1.getText().toString().trim();
                    String pass2 = et2.getText().toString().trim();
                    if (validatePassword(pass1) && comparePasswords(pass1, pass2)) {
                        pager.setCurrentItem(Constants.F_LOGIN);

                        //Insert code, send new password to WS to update
                    } else {
                        Toast.makeText(ctx, Constants.PASSWORD_MESSAGE2,
                                Toast.LENGTH_LONG).show();
                    }
                }
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
