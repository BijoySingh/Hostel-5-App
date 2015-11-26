package com.bijoykochar.hostelapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bijoykochar.hostelapp.R;

/**
 * A view holder for the mess card
 * Created by bijoy on 10/24/15.
 */
public class NoticesViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView description;
    public TextView add_timestamp;
    public TextView file;
    public LinearLayout user_item;  

    public NoticesViewHolder(final View root) {
        super(root);
        title = (TextView) root.findViewById(R.id.title);
        description = (TextView) root.findViewById(R.id.description);
        add_timestamp = (TextView) root.findViewById(R.id.create_timestamp);
        file = (TextView) root.findViewById(R.id.category);
        user_item = (LinearLayout) root.findViewById(R.id.user_item);
        user_item.setVisibility(View.GONE);
    }
}
