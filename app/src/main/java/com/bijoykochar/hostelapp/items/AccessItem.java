package com.bijoykochar.hostelapp.items;

/**
 * The access item
 * Created by bijoy on 10/24/15.
 */
public class AccessItem {
    public String url;
    public String filename;
    public boolean is_authenticated;
    public Integer type;

    public AccessItem(String url, String filename, boolean is_authenticated, Integer type) {
        this.url = url;
        this.filename = filename;
        this.is_authenticated = is_authenticated;
        this.type = type;
    }
}
