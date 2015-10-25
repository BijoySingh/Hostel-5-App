package com.bijoykochar.hostelapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.GalleryItem;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.views.GalleryViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * The recylcer view adapter for the mess list
 * Created by bijoy on 10/24/15.
 */
public class GalleryListAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    private Context context;
    private RefreshBasedFragment<GalleryItem> fragment;
    private final ImageLoader imageLoader;

    public GalleryListAdapter(Context context,
                              RefreshBasedFragment<GalleryItem> fragment) {
        this.context = context;
        this.fragment = fragment;
        imageLoader = Functions.loadImageLoader(context);
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_layout, parent, false);
        return new GalleryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, final int position) {

        final GalleryItem data = fragment.getValues().get(position);
        holder.caption.setText(data.caption);
        if (data.caption.isEmpty()) {
            holder.caption.setText(data.gallery);
        }
        holder.gallery_heading.setText(data.gallery);
        holder.add_timestamp.setText(data.add_timestamp);

        ImageAware imageAware = new ImageViewAware(holder.image, false);
        imageLoader.displayImage(data.file, imageAware);
        holder.image.setTag(data.file);

    }

    @Override
    public int getItemCount() {
        return fragment.getValues().size();
    }

}
