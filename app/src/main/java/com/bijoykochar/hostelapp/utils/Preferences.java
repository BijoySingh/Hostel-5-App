package com.bijoykochar.hostelapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Stores and loads from the shared preferences
 * Created by bijoy on 10/9/15.
 */
public class Preferences {

    Context context;

    public static final String SHARED_PREFERENCES = "HOSTEL5_IITB";

    /**
     * Shared Preference Keys
     */
    public static final String KEY_LOGIN = "LOGIN";
    public static final String KEY_ROLL_NO = "ROLL_NO";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_TOKEN = "TOKEN";

    public static final String TRUE = "true";
    public static final String FALSE = "false";


    /**
     * Load the data from the shared preferences
     *
     * @param key the key of the data
     * @return the value stored or a default
     */
    public String load(String key) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERENCES, Activity.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * Saves the data into the shared preferences
     *
     * @param key   the key of the data
     * @param value the value to store
     */
    public void save(String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERENCES, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Saves a boolean variable
     *
     * @param key  the key
     * @param bool the bool to store
     */
    public void saveBoolean(String key, Boolean bool) {
        if (bool) {
            save(key, TRUE);
        } else {
            save(key, FALSE);
        }
    }

    /**
     * Gets the stored boolean value
     *
     * @param key         the key
     * @param defaultBool boolean if it wasnt stored before or is not a bool
     * @return the stored value as boolean
     */
    public Boolean loadBoolean(String key, Boolean defaultBool) {
        String stored = load(key);
        if (stored.contentEquals(TRUE)) {
            return true;
        } else if (stored.contentEquals(FALSE)) {
            return false;
        } else {
            return defaultBool;
        }
    }

    /**
     * Private Constructor
     *
     * @param context activity context
     */
    private Preferences(Context context) {
        this.context = context;
    }

    /**
     * Factory method to give the preferences
     *
     * @param context activity context
     * @return the preference object
     */
    public static Preferences getInstance(Context context) {
        return new Preferences(context);
    }

    public void reset(String key) {
        save(key, "");
    }

    public String getRollNumber() {
        return load(KEY_ROLL_NO);
    }

    public String getToken() {
        return load(KEY_TOKEN);
    }

    public String getName() {
        return load(KEY_NAME);
    }

    public Boolean isLoggedIn() {
        return load(KEY_LOGIN).contentEquals("true");
    }

}
