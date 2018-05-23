package org.techtown.capstoneproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.techtown.capstoneproject.tab.home.Fragment_Email;
import org.techtown.capstoneproject.tab.home.Fragment_Home;
import org.techtown.capstoneproject.tab.home.Fragment_Search;
import org.techtown.capstoneproject.tab.home.Fragment_Self;

/*
 * Created by ShimPiggy on 2018-04-27.
 * Modified by ShimPiggy on 2018-05-02. - Tab Host
 * Modified by ShimPiggy on 2018-05-08. - Tab Host -> Tab Layout
 * Modified by ShimPiggy on 2018-05-20. - ViewPager -> FrameLayout
 */

public class MainActivity extends AppCompatActivity {
    private final int Home = 1;
    private final int Search = 2;
    private final int Self = 3;
    private final int Email = 4;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCheck();

        init();
        tabAdd();

        callFragment(Home);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                callFragment(Integer.parseInt(tab.getTag() + ""));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

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

    public void tabAdd() {
        tabLayout.addTab(tabLayout.newTab().setText("Home").setTag(Home));
        tabLayout.addTab(tabLayout.newTab().setText("Search").setTag(Search));
        tabLayout.addTab(tabLayout.newTab().setText("Self").setTag(Self));
        tabLayout.addTab(tabLayout.newTab().setText("EMail").setTag(Email));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }//tabAdd

    private void callFragment(int num) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (num) {
            case Home:
                Fragment_Home fragment1 = new Fragment_Home();
                transaction.replace(R.id.fragment_container, fragment1);
                break;
            case Search:
                Fragment_Search fragment2 = new Fragment_Search();
                transaction.replace(R.id.fragment_container, fragment2);
                break;
            case Self:
                Fragment_Self fragment3 = new Fragment_Self();
                transaction.replace(R.id.fragment_container, fragment3);
                break;
            case Email:
                Fragment_Email fragment4 = new Fragment_Email();
                transaction.replace(R.id.fragment_container, fragment4);
                break;
        }//switch
        transaction.commit();
    }//callFragment
}//MainActivity
