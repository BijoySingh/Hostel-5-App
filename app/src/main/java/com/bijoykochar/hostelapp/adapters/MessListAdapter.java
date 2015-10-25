package com.bijoykochar.hostelapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.MenuItem;
import com.bijoykochar.hostelapp.views.MenuViewHolder;

/**
 * The recylcer view adapter for the mess list
 * Created by bijoy on 10/24/15.
 */
public class MessListAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context context;
    private RefreshBasedFragment<MenuItem> fragment;

    public MessListAdapter(Context context,
                              RefreshBasedFragment<MenuItem> fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mess_layout, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {

        final MenuItem data = fragment.getValues().get(position);
        holder.day.setText(data.day);
        holder.breakfast.foodType.setText("Breakfast");
        holder.lunch.foodType.setText("Lunch");
        holder.tiffin.foodType.setText("Tiffin");
        holder.dinner.foodType.setText("Dinner");

        holder.breakfast.food.setText(data.breakfast);
        holder.lunch.food.setText(data.lunch);
        holder.tiffin.food.setText(data.tiffin);
        holder.dinner.food.setText(data.dinner);
    }

    @Override
    public int getItemCount() {
        return fragment.getValues().size();
    }

}
