package com.bijoykochar.hostelapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bijoykochar.hostelapp.R;

/**
 * A view holder for the mess card
 * Created by bijoy on 10/24/15.
 */
public class MenuViewHolder extends RecyclerView.ViewHolder {

    public TextView day;
    public MenuItemViewHolder breakfast;
    public MenuItemViewHolder lunch;
    public MenuItemViewHolder tiffin;
    public MenuItemViewHolder dinner;

    public MenuViewHolder(final View root) {
        super(root);
        day = (TextView) root.findViewById(R.id.day);
        breakfast = new MenuItemViewHolder(root, R.id.breakfast);
        lunch = new MenuItemViewHolder(root, R.id.lunch);
        tiffin = new MenuItemViewHolder(root, R.id.tiffin);
        dinner = new MenuItemViewHolder(root, R.id.dinner);
    }
}
