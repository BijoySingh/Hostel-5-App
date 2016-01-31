package com.bijoykochar.hostelapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.SplashActivity;
import com.bijoykochar.hostelapp.items.MenuItem;
import com.bijoykochar.hostelapp.utils.DayUtil;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.utils.ListCreator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bijoy on 1/30/16.
 */
public class MessMenuWidgetProvider extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        List<MenuItem> menu = loadFileInformation(context);
        MenuItem item = DayUtil.getTodaysMenuItem(menu);
        FoodItem food = getNowsMenu(item);

        Log.d("MESS MENU WIDGET", "UPDATE CALLED at "
                + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                + ":" + Calendar.getInstance().get(Calendar.MINUTE));

        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, SplashActivity.class);
            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.mess_menu_widget);
            views.setOnClickPendingIntent(R.id.layout, pending);

            if (food != null) {
                views.setTextViewText(R.id.day, food.day);
                views.setTextViewText(R.id.food_time, food.time);
                views.setTextViewText(R.id.food_type, food.foodType);
                views.setTextViewText(R.id.food, food.food);
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public List<MenuItem> loadFileInformation(Context context) {
        String filename = "mess_fragment.txt";
        String fileText = Functions.offlineDataReader(context, filename);
        List<MenuItem> values = new ArrayList<>();
        try {
            values = ListCreator.getInstance(context).createMenuList(fileText);

        } catch (Exception e) {
            Log.e(MessMenuWidgetProvider.class.getSimpleName(), e.getMessage(), e);
        }

        return values;
    }

    public FoodItem getNowsMenu(MenuItem item) {
        if (item == null) {
            return null;
        }

        FoodItem food = new FoodItem();
        food.day = item.day;

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (hourOfDay < 10 || hourOfDay >= 22) {
            // breakfast
            food.foodType = "Breakfast";
            food.food = item.breakfast;
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                food.time = "8am to 10am";
            } else {
                food.time = "7:30am to 9:30am";
            }
        } else if (hourOfDay < 14) {
            // lunch
            food.foodType = "Lunch";
            food.food = item.lunch;
            food.time = "12noon to 2pm";
        } else if (hourOfDay < 18) {
            // snacks
            food.foodType = "Snacks";
            food.food = item.tiffin;
            food.time = "4:30pm to 6:15pm";
        } else {
            // dinner
            food.foodType = "Dinner";
            food.food = item.dinner;
            food.time = "8pm to 10pm";
        }

        return food;
    }

    private class FoodItem {
        public String foodType;
        public String day;
        public String food;
        public String time;
    }
}
