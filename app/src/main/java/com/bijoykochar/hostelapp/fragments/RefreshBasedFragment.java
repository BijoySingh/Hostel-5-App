package com.bijoykochar.hostelapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.items.AccessItem;
import com.bijoykochar.hostelapp.server.Access;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for recycler view and allowing refresh easily
 * Created by bijoy on 8/4/15.
 */
public abstract class RefreshBasedFragment<T> extends Fragment {

    public List<T> values = new ArrayList<>();
    Context context;
    String filename;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Access access;
    AccessItem accessItem;

    /**
     * Setup the access variables
     */
    public void setupAccess() {

    }

    /**
     * Setup the recyler view
     * @param rootView
     */
    public void setupRecyclerView(View rootView) {

    }

    /**
     * Stops the refresh indicator
     */
    public void stopRefreshIndicator() {
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Initialises the recycler view
     *
     * @param rootView
     */
    public void initializeRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.accent_color, R.color.primary_color);
        swipeRefreshLayout.setEnabled(true);
    }

    /**
     * Gets the data and is called on swipe down
     */
    public void refreshList() {
        ;
    }

    /**
     * Gets the list from the file
     */
    public void refreshFileList() {
        ;
    }

    /**
     * Gets the values
     *
     * @return
     */
    public List<T> getValues() {
        return values;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }
}
