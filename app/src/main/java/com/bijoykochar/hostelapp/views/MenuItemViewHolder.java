package com.bijoykochar.hostelapp.views;

import android.view.View;
import android.widget.TextView;

import com.bijoykochar.hostelapp.R;

/**
 * View holder for the individual item in mess card
 * Created by bijoy on 10/24/15.
 */
public class MenuItemViewHolder {
    public TextView foodType;
    public TextView food;

    public MenuItemViewHolder(View rootView, Integer layout) {
        View view = rootView.findViewById(layout);
        foodType = (TextView) view.findViewById(R.id.type);
        food = (TextView) view.findViewById(R.id.food);
    }
}
