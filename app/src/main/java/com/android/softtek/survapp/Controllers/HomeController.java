package com.android.softtek.survapp.Controllers;


import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.softtek.survapp.Models.DrawerItem;
import com.android.softtek.survapp.R;

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
}
