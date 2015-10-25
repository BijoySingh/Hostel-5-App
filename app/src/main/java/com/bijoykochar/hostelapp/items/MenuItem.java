package com.bijoykochar.hostelapp.items;

import com.bijoykochar.hostelapp.keys.MenuKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Item for the menu
 * Created by bijoy on 10/24/15.
 */
public class MenuItem {

    public String id;
    public String day;
    public String breakfast;
    public String lunch;
    public String tiffin;
    public String dinner;

    public MenuItem(JSONObject json) throws JSONException {
        MenuKeys keys = new MenuKeys();

        id = json.getString(keys.id);
        day = json.getString(keys.day);
        breakfast = json.getString(keys.breakfast).replace("\r", " ").replace("\n", " ");
        lunch = json.getString(keys.lunch).replace("\r", " ").replace("\n", " ");
        tiffin = json.getString(keys.tiffin).replace("\r", " ").replace("\n", " ");
        dinner = json.getString(keys.dinner).replace("\r", " ").replace("\n", " ");
    }
}
