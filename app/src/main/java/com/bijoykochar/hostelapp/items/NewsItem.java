package com.bijoykochar.hostelapp.items;

import com.bijoykochar.hostelapp.keys.NewsKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item for the news
 * Created by bijoy on 10/24/15.
 */
public class NewsItem {
    public String id;
    public String title;
    public String description;
    public String category;
    public String create_timestamp;
    public String name;
    public String council;
    public String post;

    public NewsItem(JSONObject json) throws JSONException {
        NewsKeys keys = new NewsKeys();

        id = json.getString(keys.id);
        title = json.getString(keys.title);
        description = json.getString(keys.description);
        category = json.getString(keys.category);
        create_timestamp = json.getString(keys.create_timestamp);
        name = json.getString(keys.name);
        council = json.getString(keys.name);
        post = json.getString(keys.post);
    }

}
