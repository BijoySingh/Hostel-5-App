package com.bijoykochar.hostelapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bijoykochar.hostelapp.R;
import com.bijoykochar.hostelapp.adapters.EntryListAdapter;
import com.bijoykochar.hostelapp.items.AccessItem;
import com.bijoykochar.hostelapp.items.EntryItem;
import com.bijoykochar.hostelapp.items.Type;
import com.bijoykochar.hostelapp.server.Access;
import com.bijoykochar.hostelapp.server.Api;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.utils.ListCreator;

/**
 * Created by bijoy on 10/24/15.
 */
public class EntryFragment extends RefreshBasedFragment<EntryItem> {

    EntryListAdapter adapter;
    Integer contentType;

    public static EntryFragment getInstance(Integer contentType) {
        EntryFragment fragment = new EntryFragment();
        fragment.contentType = contentType;
        return fragment;
    }

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
        filename = "entry_fragment_" + contentType + ".txt";
        access = Access.getInstance(context);
        accessItem = new AccessItem(Api.getEntryLink(contentType), filename, false, Type.ENTRY);
    }

    @Override
    public void setupRecyclerView(View rootView) {
        initializeRecyclerView(rootView);
        adapter = new EntryListAdapter(context, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshFileList() {
        super.refreshFileList();
        String fileText = Functions.offlineDataReader(getActivity(), filename);
        if (!fileText.contentEquals("")) {
            try {
                values = ListCreator.getInstance(context).createEntryList(fileText);
                if (values.isEmpty()) {
                    values = ListCreator.getInstance(context).createEmptyEntryList();
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(RefreshBasedFragment.class.getSimpleName(), e.getMessage(), e);
                values = ListCreator.getInstance(context).createEmptyEntryList();
                adapter.notifyDataSetChanged();
            }
        } else {
            values = ListCreator.getInstance(context).createEmptyEntryList();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshList() {
        super.refreshList();
        access.getDataFromApi(accessItem, this);
    }
}
