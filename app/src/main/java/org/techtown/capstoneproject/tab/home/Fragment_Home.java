package org.techtown.capstoneproject.tab.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import org.techtown.capstoneproject.tab.tab1_home.AboutUsActivity;
import org.techtown.capstoneproject.R;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-23. - image
 */

public class Fragment_Home extends Fragment {
    private CarouselView carouselView;
    int[] carouselImage = {R.drawable.carousel_1, R.drawable.carousel_2, R.drawable.carousel_3, R.drawable.carousel_4, R.drawable.carousel_5};

    private ImageButton ib_aboutUs;
    private LinearLayout linearLayout;

    public Fragment_Home() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init(view);
        carouselSetting();

        return view;
    }

    public void init(final View view) {
        carouselView = (CarouselView) view.findViewById(R.id.carousel_view);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);

        ib_aboutUs = (ImageButton) view.findViewById(R.id.aboutus);

        ib_aboutUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goAboutUs();
            }
        });

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                goAboutUs();
                return false;
            }
        });
    }

    public void carouselSetting() {
        carouselView.setPageCount(carouselImage.length);
        carouselView.setSlideInterval(4000);

        carouselView.setViewListener(carouselviewListener);
    }

    ViewListener carouselviewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.tab1_carousel_view, null);
            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.fruitImageView);
            fruitImageView.setImageResource(carouselImage[position]);

            //page 순서 알려주는 것
            carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            return customView;
        }
    };//carouselviewListener

    public void goAboutUs(){
        Intent integer = new Intent(getActivity().getApplicationContext(), AboutUsActivity.class);
        startActivity(integer);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}