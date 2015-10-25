package com.bijoykochar.hostelapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.NewsItem;
import com.bijoykochar.hostelapp.views.NewsViewHolder;

/**
 * The recylcer view adapter for the mess list
 * Created by bijoy on 10/24/15.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private Context context;
    private RefreshBasedFragment<NewsItem> fragment;

    public NewsListAdapter(Context context,
                           RefreshBasedFragment<NewsItem> fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {

        final NewsItem data = fragment.getValues().get(position);
        holder.title.setText(data.title);
        holder.description.setText(data.description);
        holder.name.setText(data.name);
        holder.post.setText(data.post);
        holder.council.setText(data.council);
        holder.category.setText(data.category);
        holder.create_timestamp.setText(data.create_timestamp);
        
    }

    @Override
    public int getItemCount() {
        return fragment.getValues().size();
    }

}
