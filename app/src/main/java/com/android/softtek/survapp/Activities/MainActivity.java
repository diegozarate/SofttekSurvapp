package com.android.softtek.survapp.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.android.softtek.survapp.Controllers.MainController;
import com.android.softtek.survapp.R;
import com.android.softtek.survapp.Util.Constants;
import com.android.softtek.survapp.Adapters.FragmentMainAdapter;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        FragmentMainAdapter adapter = new FragmentMainAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(Constants.F_LOGIN);
    }

    public void onClick(View v) {
        MainController controller = new MainController();
        controller.onClickController(mPager, v, this);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0)
            finish();
        else
            mPager.setCurrentItem(0);
    }
}
