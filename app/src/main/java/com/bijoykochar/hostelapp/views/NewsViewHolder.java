package com.bijoykochar.hostelapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bijoykochar.hostelapp.R;

/**
 * A view holder for the mess card
 * Created by bijoy on 10/24/15.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView description;
    public TextView name;
    public TextView council;
    public TextView post;
    public TextView create_timestamp;
    public TextView category;


    public NewsViewHolder(final View root) {
        super(root);
        title = (TextView) root.findViewById(R.id.title);
        description = (TextView) root.findViewById(R.id.description);
        name = (TextView) root.findViewById(R.id.name);
        council = (TextView) root.findViewById(R.id.council);
        post = (TextView) root.findViewById(R.id.post);
        create_timestamp = (TextView) root.findViewById(R.id.create_timestamp);
        category = (TextView) root.findViewById(R.id.category);
    }
}
