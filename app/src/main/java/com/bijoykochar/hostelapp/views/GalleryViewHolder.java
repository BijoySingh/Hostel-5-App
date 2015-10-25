package com.bijoykochar.hostelapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bijoykochar.hostelapp.R;

/**
 * A view holder for the mess card
 * Created by bijoy on 10/24/15.
 */
public class GalleryViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView caption;
    public TextView gallery_heading;
    public TextView add_timestamp;
    public ImageView image;

    public GalleryViewHolder(final View root) {
        super(root);
        this.root = root;
        caption = (TextView) root.findViewById(R.id.caption);
        add_timestamp = (TextView) root.findViewById(R.id.create_timestamp);
        gallery_heading = (TextView) root.findViewById(R.id.gallery_heading);
        image = (ImageView) root.findViewById(R.id.gallery_image);
    }
}
