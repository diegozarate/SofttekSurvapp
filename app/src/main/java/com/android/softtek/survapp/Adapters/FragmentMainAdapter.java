package com.android.softtek.survapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.softtek.survapp.Fragments.FragmentLogin;
import com.android.softtek.survapp.Fragments.FragmentRegister;
import com.android.softtek.survapp.Fragments.FragmentRestore;
import com.android.softtek.survapp.Fragments.FragmentSetPassword;
import com.android.softtek.survapp.Fragments.FragmentValidation;

import static com.android.softtek.survapp.Util.Constants.*;


public class FragmentMainAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public FragmentMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentLogin();
            case 1:
                return new FragmentRegister();
            case 2:
                return new FragmentValidation();
            case 3:
                return new FragmentRestore();
            case 4:
                return new FragmentSetPassword();
            default:
                return null;
        }
    }
}
