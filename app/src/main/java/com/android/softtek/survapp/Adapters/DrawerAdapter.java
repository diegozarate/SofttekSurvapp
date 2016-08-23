package com.android.softtek.survapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.softtek.survapp.Models.DrawerItem;
import com.android.softtek.survapp.R;

import java.util.ArrayList;

public class DrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrawerItem> navDrawerItems;

    public DrawerAdapter(Context context, ArrayList<DrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) view.findViewById(R.id.icon);
        TextView txtTitle = (TextView) view.findViewById(R.id.title);

        imgIcon.setImageResource(navDrawerItems.get(i).getIcon());
        txtTitle.setText(navDrawerItems.get(i).getTitle());

        return view;
    }
}
