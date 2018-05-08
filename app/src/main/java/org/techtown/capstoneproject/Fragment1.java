package org.techtown.capstoneproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ShimPiggy on 2018-05-07.
 */

public class Fragment1 extends Fragment {
    public Fragment1(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_fragment1, container,false);
    }

    @Override
    public void onCreate( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}


