package org.techtown.capstoneproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.techtown.capstoneproject.tab.fouth.inquiry.FragmentEmail;
import org.techtown.capstoneproject.tab.first.home.FragmentHome;
import org.techtown.capstoneproject.tab.second.search.FragmentSearch;
import org.techtown.capstoneproject.tab.third.self.FragmentSelf;

/*
 * Created by ShimPiggy on 2018-04-27.
 * Modified by ShimPiggy on 2018-05-02. - Tab Host
 * Modified by ShimPiggy on 2018-05-08. - Tab Host -> Tab Layout
 * Modified by ShimPiggy on 2018-05-20. - ViewPager -> FrameLayout
 * Modified by ShimPiggy on 2018-05-23. - Image, actionbar
 */

public class MainActivity extends AppCompatActivity {
    private final int HOME = 1;
    private final int SEARCH = 2;
    private final int SELF = 3;
    private final int EMAIL = 4;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar();
        setContentView(R.layout.activity_main);
        permissionCheck();

        init();
        tabAdd();

        callFragment(HOME);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (Integer.parseInt(tab.getTag().toString())) {
                    case HOME:
                        tab.setIcon(R.drawable.tab_home_select);
                        break;
                    case SEARCH:
                        tab.setIcon(R.drawable.tab_search_select);
                        break;
                    case SELF:
                        tab.setIcon(R.drawable.tab_self_select);
                        break;
                    case EMAIL:
                        tab.setIcon(R.drawable.tab_email_select);
                        break;
                }
                callFragment(Integer.parseInt(tab.getTag() + ""));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (Integer.parseInt(tab.getTag().toString())) {
                    case HOME:
                        tab.setIcon(R.drawable.tab_home);
                        break;
                    case SEARCH:
                        tab.setIcon(R.drawable.tab_search);
                        break;
                    case SELF:
                        tab.setIcon(R.drawable.tab_self);
                        break;
                    case EMAIL:
                        tab.setIcon(R.drawable.tab_email);
                        break;
                }
            }//onTabUnselected

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });//addOnTabSelectedListener
    }//onCreate

    public void permissionCheck() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            //권한 없음
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
        }
    }//permissionCheck

    public void init() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }

    public void tabAdd() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_home_select).setTag(HOME));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_search).setTag(SEARCH));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_self).setTag(SELF));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_email).setTag(EMAIL));
    }//tabAdd

    private void callFragment(int num) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (num) {
            case HOME:
                FragmentHome fragment1 = new FragmentHome();
                transaction.replace(R.id.fragment_container, fragment1);
                break;
            case SEARCH:
                FragmentSearch fragment2 = new FragmentSearch();
                transaction.replace(R.id.fragment_container, fragment2);
                break;
            case SELF:
                FragmentSelf fragment3 = new FragmentSelf();
                transaction.replace(R.id.fragment_container, fragment3);
                break;
            case EMAIL:
                FragmentEmail fragment4 = new FragmentEmail();
                transaction.replace(R.id.fragment_container, fragment4);
                break;
        }//switch
        transaction.commit();
    }//callFragment
}//MainActivity
