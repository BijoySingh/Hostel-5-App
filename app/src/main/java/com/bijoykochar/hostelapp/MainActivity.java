package com.bijoykochar.hostelapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bijoykochar.hostelapp.fragments.EntryFragment;
import com.bijoykochar.hostelapp.fragments.GalleryFragment;
import com.bijoykochar.hostelapp.fragments.MessFragment;
import com.bijoykochar.hostelapp.fragments.NewsFragment;
import com.bijoykochar.hostelapp.server.Configurations;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViewPagerAndTabs();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(Configurations.getInstance().appTitle);
        mToolbar.setSubtitle(Configurations.getInstance().subTitle);
        mToolbar.setLogo(R.drawable.app_logo);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_white_text));
        setSupportActionBar(mToolbar);
    }

    private void initViewPagerAndTabs() {
        String[] pageTitles = getResources().getStringArray(R.array.tabs);

        final MaterialTabHost tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new MessFragment(), pageTitles[1]);
        pagerAdapter.addFragment(new NewsFragment(), pageTitles[2]);
        pagerAdapter.addFragment(new GalleryFragment(), pageTitles[9]);
        pagerAdapter.addFragment(EntryFragment.getInstance(1), pageTitles[3]);
        pagerAdapter.addFragment(EntryFragment.getInstance(2), pageTitles[4]);
        pagerAdapter.addFragment(EntryFragment.getInstance(4), pageTitles[6]);
        pagerAdapter.addFragment(EntryFragment.getInstance(5), pageTitles[7]);
        pagerAdapter.addFragment(EntryFragment.getInstance(6), pageTitles[8]);
        pagerAdapter.addFragment(EntryFragment.getInstance(3), pageTitles[5]);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        MaterialTabListener tabListener = new MaterialTabListener() {
            @Override
            public void onTabSelected(MaterialTab materialTab) {
                viewPager.setCurrentItem(materialTab.getPosition(), true);
            }

            @Override
            public void onTabReselected(MaterialTab materialTab) {
                viewPager.setCurrentItem(materialTab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(MaterialTab materialTab) {

            }
        };

        // insert all tabs from mPagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(tabListener));
        }
    }


    static class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
