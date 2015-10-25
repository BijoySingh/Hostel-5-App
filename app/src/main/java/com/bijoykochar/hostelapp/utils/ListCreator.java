package com.bijoykochar.hostelapp.utils;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.bijoykochar.hostelapp.items.AccessItem;
import com.bijoykochar.hostelapp.items.EntryItem;
import com.bijoykochar.hostelapp.items.GalleryItem;
import com.bijoykochar.hostelapp.items.MenuItem;
import com.bijoykochar.hostelapp.items.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the lists from the JSON
 * Created by bijoy on 10/24/15.
 */
public class ListCreator {
    private static final String ERROR = "error";
    private static final String RESULTS = "results";

    Context context;

    public static ListCreator getInstance(Context context) {
        return new ListCreator(context);
    }

    public List<NewsItem> createNewsList(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray array = json.getJSONArray(RESULTS);
        List<NewsItem> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            list.add(new NewsItem(object));
        }

        return list;
    }

    public List<MenuItem> createMenuList(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray array = json.getJSONArray(RESULTS);
        List<MenuItem> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            list.add(new MenuItem(object));
        }

        return list;
    }

    public List<EntryItem> createEntryList(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray array = json.getJSONArray(RESULTS);
        List<EntryItem> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            list.add(new EntryItem(object));
        }

        return list;
    }


    public List<GalleryItem> createGalleryList(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray array = json.getJSONArray(RESULTS);
        List<GalleryItem> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            list.add(new GalleryItem(object));
        }

        return list;
    }

    private ListCreator(Context context) {
        this.context = context;
    }

    public List<EntryItem> createEmptyEntryList() {
        List<EntryItem> lst = new ArrayList<>();
        lst.add(new EntryItem("No Content", "There is no content to show.\n " +
                "- Please confirm your internet connection.\n " +
                "- Request your respective council members.", "Warning"));
        return lst;
    }
}
