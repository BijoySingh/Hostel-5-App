package com.bijoykochar.hostelapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.EntryItem;
import com.bijoykochar.hostelapp.views.EntryViewHolder;

/**
 * The recylcer view adapter for the mess list
 * Created by bijoy on 10/24/15.
 */
public class EntryListAdapter extends RecyclerView.Adapter<EntryViewHolder> {

    private Context context;
    private RefreshBasedFragment<EntryItem> fragment;

    public EntryListAdapter(Context context,
                            RefreshBasedFragment<EntryItem> fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout, parent, false);
        return new EntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, final int position) {

        final EntryItem data = fragment.getValues().get(position);
        holder.title.setText(data.title);
        holder.description.setText(data.description);
        holder.heading.setText(data.heading);
        holder.add_timestamp.setText(data.add_timestamp);

    }

    @Override
    public int getItemCount() {
        return fragment.getValues().size();
    }

}
