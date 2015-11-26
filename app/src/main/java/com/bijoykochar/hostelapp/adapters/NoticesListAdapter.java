package com.bijoykochar.hostelapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.NoticesItem;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.views.NoticesViewHolder;

/**
 * The recylcer view adapter for the mess list
 * Created by bijoy on 10/24/15.
 */
public class NoticesListAdapter extends RecyclerView.Adapter<NoticesViewHolder> {

    private Context context;
    private RefreshBasedFragment<NoticesItem> fragment;

    public NoticesListAdapter(Context context,
                              RefreshBasedFragment<NoticesItem> fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public NoticesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout, parent, false);
        return new NoticesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticesViewHolder holder, final int position) {

        final NoticesItem data = fragment.getValues().get(position);
        holder.title.setText(data.title);
        holder.description.setText(data.description);
        holder.add_timestamp.setText(data.add_timestamp);

        if (!data.file.isEmpty()) {
            holder.file.setText("Attachement");
            holder.file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.file));
                    context.startActivity(browserIntent);
                }
            });
        } else {
            holder.file.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return fragment.getValues().size();
    }

}
