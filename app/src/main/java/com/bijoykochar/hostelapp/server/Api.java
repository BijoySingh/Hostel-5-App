package com.bijoykochar.hostelapp.server;

/**
 * Contains all the links
 * Created by bijoy on 10/24/15.
 */
public class Api {

    public static String getBaseLink() {
        return "http://gymkhana.iitb.ac.in/~hostel5/api/app/";
    }

    public static String getMenuLink() {
        return getBaseLink() + "mess.php";
    }

    public static String getNewsLink() {
        return getBaseLink() + "news.php";
    }

    public static String getGalleryLink() {
        return getBaseLink() + "gallery.php";
    }

    public static String getNoticeLink() {
        return getBaseLink() + "notices.php";
    }

    public static String getEntryLink(Integer content) {
        return getBaseLink() + "entry.php?content=" + content;
    }
}
