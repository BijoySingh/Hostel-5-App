package com.bijoykochar.hostelapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.adapters.MessListAdapter;
import com.bijoykochar.hostelapp.adapters.NewsListAdapter;
import com.bijoykochar.hostelapp.items.AccessItem;
import com.bijoykochar.hostelapp.items.MenuItem;
import com.bijoykochar.hostelapp.items.NewsItem;
import com.bijoykochar.hostelapp.items.Type;
import com.bijoykochar.hostelapp.server.Access;
import com.bijoykochar.hostelapp.server.Api;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.utils.ListCreator;

/**
 * Created by bijoy on 10/24/15.
 */
public class NewsFragment extends RefreshBasedFragment<NewsItem> {

    NewsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_layout, container, false);
        setupRecyclerView(rootView);
        setupAccess();
        refreshFileList();
        refreshList();

        return rootView;
    }

    @Override
    public void setupAccess() {
        filename = "news_fragment.txt";
        access = Access.getInstance(context);
        accessItem = new AccessItem(Api.getNewsLink(), filename, false, Type.NEWS);
    }

    @Override
    public void setupRecyclerView(View rootView) {
        initializeRecyclerView(rootView);
        adapter = new NewsListAdapter(context, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshFileList() {
        super.refreshFileList();
        String fileText = Functions.offlineDataReader(getActivity(), filename);
        if (!fileText.contentEquals("")) {
            try {
                values = ListCreator.getInstance(context).createNewsList(fileText);
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(RefreshBasedFragment.class.getSimpleName(), e.getMessage(), e);
            }
        }
    }

    @Override
    public void refreshList() {
        super.refreshList();
        access.getDataFromApi(accessItem, this);
    }
}
