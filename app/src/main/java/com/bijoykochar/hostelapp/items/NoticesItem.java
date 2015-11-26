package com.bijoykochar.hostelapp.items;

import com.bijoykochar.hostelapp.keys.NewsKeys;
import com.bijoykochar.hostelapp.keys.NoticesKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item for the news
 * Created by bijoy on 10/24/15.
 */
public class NoticesItem {
    public String id;
    public String title;
    public String description;
    public String file;
    public String add_timestamp;
    public String author_id;

    public NoticesItem(JSONObject json) throws JSONException {
        NoticesKeys keys = new NoticesKeys();

        id = json.getString(keys.id);
        title = json.getString(keys.title);
        description = json.getString(keys.description);
        file = json.getString(keys.file);
        add_timestamp = json.getString(keys.add_timestamp);
        author_id = json.getString(keys.author_id);
    }

}
