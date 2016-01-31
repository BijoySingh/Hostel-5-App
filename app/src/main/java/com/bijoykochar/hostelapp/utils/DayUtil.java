package com.bijoykochar.hostelapp.utils;

import com.bijoykochar.hostelapp.items.MenuItem;

import java.util.Calendar;
import java.util.List;

/**
 * Created by bijoy on 1/31/16.
 */
public class DayUtil {
    public static int getDayOfWeekId(int day) {
        if (day == Calendar.MONDAY) {
            return 1;
        } else if (day == Calendar.TUESDAY) {
            return 2;
        } else if (day == Calendar.WEDNESDAY) {
            return 3;
        } else if (day == Calendar.THURSDAY) {
            return 4;
        } else if (day == Calendar.FRIDAY) {
            return 5;
        } else if (day == Calendar.SATURDAY) {
            return 6;
        } else if (day == Calendar.SUNDAY) {
            return 7;
        }
        return 0;
    }

    public static Integer getTodaysId() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int id = getDayOfWeekId(dayOfWeek);
        if (hourOfDay >= 22) {
            id = id == 6 ? 7 : (id + 1)%7;
        }

        return id;
    }

    public static MenuItem getTodaysMenuItem(List<MenuItem> menu) {
        Integer id = getTodaysId();
        for (MenuItem item : menu) {
            if (Integer.valueOf(item.id) == id) {
                return item;
            }
        }
        return null;
    }
}
