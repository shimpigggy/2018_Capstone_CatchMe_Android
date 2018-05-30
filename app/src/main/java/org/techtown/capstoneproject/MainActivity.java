package org.techtown.capstoneproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.techtown.capstoneproject.tab.fouth.inquiry.FragmentInquiry;
import org.techtown.capstoneproject.tab.first.home.FragmentHome;
import org.techtown.capstoneproject.tab.second.search.FragmentSearch;
import org.techtown.capstoneproject.tab.third.self.FragmentSelf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/*
 * Created by ShimPiggy on 2018-04-27.
 * Modified by ShimPiggy on 2018-05-02. - Tab Host
 * Modified by ShimPiggy on 2018-05-08. - Tab Host -> Tab Layout
 * Modified by ShimPiggy on 2018-05-20. - ViewPager -> FrameLayout
 * Modified by ShimPiggy on 2018-05-23. - Image, actionbar
 * Modified by ShimPiggy on 2018-05-26. - icon resize
 */

public class MainActivity extends AppCompatActivity {
    private final int HOME = 1;
    private final int SEARCH = 2;
    private final int SELF = 3;
    private final int INQUIRY = 4;

    public static TabLayout tabLayout;
    private View home;
    private View search;
    private View self;
    private View inquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar();
        cameraPermission(this);
        setContentView(R.layout.activity_main);

        init();

        callFragment(HOME);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                callFragment(Integer.parseInt(tab.getTag() + ""));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }//onTabUnselected

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });//addOnTabSelectedListener
        try {
            PackageInfo info = getPackageManager().getPackageInfo("org.techtown.capstoneproject", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //facebook 회원활동 추적
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }//onCreate

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }

    public void cameraPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            //권한 없음
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 0);
        }
    }//permissionCheck

    public void init() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setIconSize();
        tabAdd();
    }

    private View getTabView(int imgDrawable) {
        View view = getLayoutInflater().inflate(R.layout.tabicon_setting, null);
        ImageView imgTab = (ImageView) view.findViewById(R.id.icon);
        imgTab.setImageDrawable(getResources().getDrawable(imgDrawable));

        return view;
    }

    public void setIconSize() {
        home = getTabView(R.drawable.tab_home_selector);
        search = getTabView(R.drawable.tab_search_selector);
        self = getTabView(R.drawable.tab_self_selector);
        inquiry = getTabView(R.drawable.tab_inquiry_selector);
    }

    public void tabAdd() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(home).setTag(HOME));
        tabLayout.addTab(tabLayout.newTab().setCustomView(search).setTag(SEARCH));
        tabLayout.addTab(tabLayout.newTab().setCustomView(self).setTag(SELF));
        tabLayout.addTab(tabLayout.newTab().setCustomView(inquiry).setTag(INQUIRY));
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
            case INQUIRY:
                FragmentInquiry fragment4 = new FragmentInquiry();
                transaction.replace(R.id.fragment_container, fragment4);
                break;
        }//switch
        transaction.commit();
    }//callFragment
}//MainActivity
