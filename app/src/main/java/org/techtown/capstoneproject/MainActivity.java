package org.techtown.capstoneproject;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost fragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ; ts1.setContent(R.id.content1) ; ts1.setIndicator("Home") ; tabHost1.addTab(ts1) ;

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ; ts2.setContent(R.id.content2) ; ts2.setIndicator("Camera") ; tabHost1.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ; ts3.setContent(R.id.content3) ; ts3.setIndicator("Write") ; tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ; ts4.setContent(R.id.content3) ; ts4.setIndicator("EMail") ; tabHost1.addTab(ts4) ;
    }
}
