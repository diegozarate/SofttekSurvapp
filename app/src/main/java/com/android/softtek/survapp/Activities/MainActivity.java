package com.android.softtek.survapp.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewParentCompat;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;

import com.android.softtek.survapp.Controllers.MainController;
import com.android.softtek.survapp.R;
import com.android.softtek.survapp.Util.Constants;
import com.android.softtek.survapp.Util.FragmentMainAdapter;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        FragmentMainAdapter adapter = new FragmentMainAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(Constants.F_LOGIN);
    }

    public void onClick(View v) {
        MainController controller = new MainController();
        controller.onClickController(mPager, v, this);
    }
}
