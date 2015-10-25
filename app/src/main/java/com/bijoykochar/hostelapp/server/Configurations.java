package com.bijoykochar.hostelapp.server;

/**
 * Created by bijoy on 10/24/15.
 */
public class Configurations {
    public String appTitle;
    public String subTitle;

    public Configurations() {
        appTitle = "  Hostel 5";
        subTitle = "   IIT Bombay";
    }

    public static Configurations getInstance() {
        return new Configurations();
    }
}
