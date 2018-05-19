package org.techtown.capstoneproject.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import org.techtown.capstoneproject.R;

/**
 * Created by ShimPiggy on 2018-05-07.
 */

public class Fragment_Home extends Fragment {
    private CarouselView carouselView;
    private TextView textView;

    int[] carouselImage = {R.drawable.carousel_1, R.drawable.carousel_2, R.drawable.carousel_3, R.drawable.carousel_4, R.drawable.carousel_5};

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
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        textView = (TextView) view.findViewById(R.id.main_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"click!",Toast.LENGTH_SHORT).show();
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

            View customView = getLayoutInflater().inflate(R.layout.home_carousel_view, null);

            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.fruitImageView);

            fruitImageView.setImageResource(carouselImage[position]);

            //    carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);

            return customView;
        }
    };//carouselviewListener

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}


