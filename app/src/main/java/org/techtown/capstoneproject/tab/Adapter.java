package org.techtown.capstoneproject.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ShimPiggy on 2018-05-08.
 */

public class Adapter extends FragmentPagerAdapter{
    private int tabCount;

    public Adapter(FragmentManager fm, int tab){
        super(fm);
        tabCount = tab;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new Fragment_Home();
            case 1:
                return new Fragment_Search();
            case 2:
                return new Fragment_Self();
            case 3:
                return new Fragment_Email();
        }
        return null;
    }

    @Override
    public int getCount(){
        return tabCount;
    }
}
