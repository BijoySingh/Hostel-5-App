package com.bijoykochar.hostelapp.server;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.bijoykochar.hostelapp.utils.Preferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bijoy on 11/25/15.
 */
public class SsoHelper {
    public static final String CLIENT_ID = "rIGZhqcTh0lEnHfTLHwlYbPpEMVQMLZma7UK9awL";
    public static final String GYMKHANA = "http://gymkhana.iitb.ac.in/";
    public static final String SCOPE = "basic%20profile%20ldap%20program%20send_mail";
    public static final String LINK = GYMKHANA + "sso/oauth/authorize/?client_id=" +
            CLIENT_ID + "&response_type=code&scope=" + SCOPE + "&state=app";

    public static void openUrl(Context context) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK));
        context.startActivity(browserIntent);
    }

    public static void logout(Context context) {
        Preferences preferences = Preferences.getInstance(context);
        preferences.save(Preferences.KEY_LOGIN, "false");
        preferences.save(Preferences.KEY_ROLL_NO, "");
        preferences.save(Preferences.KEY_NAME, "");
        preferences.save(Preferences.KEY_TOKEN, "");
    }

    public static Map<String, String> parseResponse(String url) {
        String trimmedUrl = url.replace("hostel5://android/", "");

        String[] requiredParams = {"valid", "name", "token", "rollno"};
        String[] params = trimmedUrl.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            Log.d(SsoHelper.class.getSimpleName(), param);

            String[] paramSplit = param.split("=");
            if (paramSplit.length == 2) {
                String name = paramSplit[0];
                String value = paramSplit[1];
                map.put(name, value);
            }
        }

        for (String param : requiredParams) {
            if (!map.containsKey(param)) {
                map.put(param, "");
            }
        }

        return map;

    }

}
