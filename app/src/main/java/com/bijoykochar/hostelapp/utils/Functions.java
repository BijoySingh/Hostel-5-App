package com.bijoykochar.hostelapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Bijoy on 3/6/2015.
 * This file is the core function file of the entire application
 * All the functions here are static functions and form the basis of the app
 */
public class Functions {

    /**
     * It store the filedata into the file given by the filename
     *
     * @param context  the activity context
     * @param filename the filename to store in
     * @param filedata the string to be stored
     */
    public static void offlineDataWriter(Context context, String filename, String filedata) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(filedata.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e(Functions.class.getSimpleName(), e.getMessage(), e);
        }
    }

    /**
     * Reads the data stored in the file given by the filename
     *
     * @param context  the activity context
     * @param filename the name of the filename
     * @return the string read
     */
    public static String offlineDataReader(Context context, String filename) {

        try {
            FileInputStream fis = context.openFileInput(filename);
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }

            return builder.toString();
        } catch (Exception e) {
            Log.e(Functions.class.getSimpleName(), e.getMessage(), e);
        }

        return "";
    }

    /**
     * Makes a toast(this is much cleaner than the code for toast)
     *
     * @param context the activity context
     * @param text    the text to be printed
     */
    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a dialog which is transparent.
     *
     * @param context context of activity.
     * @param layout  the layout id.
     * @return the dialog
     */
    public static Dialog getTransparentDialog(Context context, Integer layout) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);

        final Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }

    /**
     * Converts dp value to pixel value
     *
     * @param context the app context
     * @param dp      the length in dp
     * @return the pixel size
     */
    public static int dpToPixels(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    /**
     * Loads the imageLoader question required for downloading the images
     *
     * @param context the application context
     * @return the image loader
     */
    public static ImageLoader loadImageLoader(Context context) {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.defaultDisplayImageOptions(displayImageOptions);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.memoryCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());

        return ImageLoader.getInstance();
    }
}
