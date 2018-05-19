package org.techtown.capstoneproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.techtown.capstoneproject.tab.Adapter;

public class MainActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCheck();

        init();
        tabAdd();

        // Creating TabPagerAdapter adapter
        Adapter pagerAdapter = new Adapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });//addOnTabSelectedListener
    }//onCreate

    public void permissionCheck(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            //권한 없음
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},0);
        }
    }

    public void init(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
    }

    public void tabAdd(){
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Fragment_Search"));
        tabLayout.addTab(tabLayout.newTab().setText("Write"));
        tabLayout.addTab(tabLayout.newTab().setText("EMail"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}//MainActivity
