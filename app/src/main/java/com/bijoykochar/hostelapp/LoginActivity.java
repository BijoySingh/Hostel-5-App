package com.bijoykochar.hostelapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijoykochar.hostelapp.server.Configurations;
import com.bijoykochar.hostelapp.server.SsoHelper;
import com.bijoykochar.hostelapp.utils.Preferences;

import java.net.URLDecoder;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Context context;
    TextView name;
    TextView status;
    TextView rollNo;
    ProgressBar progressBar;

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(Configurations.getInstance().appTitle);
        mToolbar.setSubtitle(Configurations.getInstance().subTitle);
        mToolbar.setLogo(R.drawable.app_logo);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_white_text));
        setSupportActionBar(mToolbar);
    }

    private String getName(String name) {
        String userName;
        try {
            userName = URLDecoder.decode(name, "UTF-8");
        } catch (Exception e) {
            userName = name.replaceAll("%20", " ");
        }
        return userName;
    }

    private void setupCardView(Map<String, String> options) {
        name = (TextView) findViewById(R.id.name);
        status = (TextView) findViewById(R.id.status);
        rollNo = (TextView) findViewById(R.id.roll_no);

        name.setText(getName(options.get("name")));
        rollNo.setText(options.get("rollno"));

        if (options.get("valid").equals("true")) {
            status.setText("You are being logged into the Hostel 5 app.\n" +
                    "Please wait while we load some necessary information...");
        } else {
            status.setText("You were not allowed to log into the Hostel 5 app.\n" +
                    "Please contact the system administrator about this issue.\n" +
                    "Please wait you will be redirected back...");
        }

    }

    private void storePreferences(Map<String, String> options) {
        Preferences preferences = Preferences.getInstance(this);
        preferences.save(Preferences.KEY_NAME, getName(options.get("name")));
        preferences.save(Preferences.KEY_TOKEN, options.get("token"));
        preferences.save(Preferences.KEY_LOGIN, options.get("valid"));
        preferences.save(Preferences.KEY_ROLL_NO, options.get("rollno"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_layout);
        context = this;

        Intent intent = getIntent();
        Uri data = intent.getData();

        Log.d(LoginActivity.class.getSimpleName(), data.toString());

        Map<String, String> options = SsoHelper.parseResponse(data.toString());

        initToolbar();
        setupCardView(options);
        storePreferences(options);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setIndeterminate(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);

    }

}
