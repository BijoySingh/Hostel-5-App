package com.bijoykochar.hostelapp.items;

import com.bijoykochar.hostelapp.keys.GalleryKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item for the menu
 * Created by bijoy on 10/24/15.
 */
public class GalleryItem {

    public String id;
    public String gallery_id;
    public String file;
    public String caption;
    public String gallery;
    public String add_timestamp;

    public GalleryItem(JSONObject json) throws JSONException {
        GalleryKeys keys = new GalleryKeys();

        id = json.getString(keys.id);
        gallery_id = json.getString(keys.gallery_id);
        file = json.getString(keys.file);
        caption = json.getString(keys.caption);
        gallery = json.getString(keys.gallery);
        add_timestamp = json.getString(keys.add_timestamp);

    }

}
