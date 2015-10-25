package com.bijoykochar.hostelapp.items;

import com.bijoykochar.hostelapp.keys.EntryKeys;
import com.bijoykochar.hostelapp.keys.MenuKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item for the menu
 * Created by bijoy on 10/24/15.
 */
public class EntryItem {

    public String content_id;
    public String tab_id;
    public String title;
    public String description;
    public String author_id;
    public String add_timestamp;
    public String heading;

    public EntryItem(JSONObject json) throws JSONException {
        EntryKeys keys = new EntryKeys();

        content_id = json.getString(keys.content_id);
        tab_id = json.getString(keys.tab_id);
        title = json.getString(keys.title);
        description = json.getString(keys.description);
        author_id = json.getString(keys.author_id);
        add_timestamp = json.getString(keys.add_timestamp);
        heading = json.getString(keys.heading);

    }

    public EntryItem(String title, String description, String heading) {
        this.title = title;
        this.description = description;
        this.heading = heading;
        this.add_timestamp = "";
    }
}
