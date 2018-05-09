package org.techtown.capstoneproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.techtown.capstoneproject.com.catchme.search.Write;

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
                return new Fragment1();
            case 1:
                return new Camera();
            case 2:
                return new Write();
            case 3:
                return new Email();
        }
        return null;
    }

    @Override
    public int getCount(){
        return tabCount;
    }
}
