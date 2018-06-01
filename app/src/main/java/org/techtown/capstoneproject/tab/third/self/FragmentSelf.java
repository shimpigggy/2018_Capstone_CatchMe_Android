package org.techtown.capstoneproject.tab.third.self;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.SharedPreferencesUtil;
import org.techtown.capstoneproject.tab.first.home.FragmentHome;
import org.techtown.capstoneproject.tab.fouth.inquiry.CustomDialog;
import org.techtown.capstoneproject.tab.fouth.inquiry.FragmentInquiry;

/*
 * Created by ShimPiggy on 2018-05-19.
 * Modified by ShimPiggy on 2018-05-23. - image
 * Modified by ShimPiggy on 2018-05-25. - change view
 */
public class FragmentSelf extends Fragment implements View.OnClickListener {
    private TextView[] tvs;
    private ImageButton ib_check;

    private boolean[] flags;
    private final String[] FILTERING = {"dry", "oil", "complex", "allergy", "sensitive", "acne"};


    private final int textviewCount = 6;

    public FragmentSelf() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtering, container, false);

        Init(view);
        buttonClick();

        return view;
    }//onCreateView

    public void Init(View view) {
        tvs = new TextView[textviewCount];
        textViewInit(view);

        ib_check = (ImageButton) view.findViewById(R.id.check);

        flags = new boolean[textviewCount];
        for (int i = 0; i < textviewCount; i++) {
            if (!SharedPreferencesUtil.getBooleanPreferences(getContext(), FILTERING[i]))
                flags[i] = true;
            else
                flags[i] = false;
            textViewCheck(i, view);
        }
    }//init

    public void buttonClick() {
        ib_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Change!", Toast.LENGTH_SHORT).show();
                SharedPreferencesUtil.removeFilteringAllPreferences(getContext());

                for (int i = 0; i < textviewCount; i++) {
                    SharedPreferencesUtil.saveBooleanPreferences(getContext(), FILTERING[i], flags[i]);
                }

                CustomDialog oDialog = new CustomDialog(getContext(), "선택하신 필터링으로 저장하였습니다.");
                oDialog.setCancelable(false);
                oDialog.show();
            }
        });
    }//buttonClick

    public void textViewInit(View view) {
        tvs[0] = (TextView) view.findViewById(R.id.type1);
        tvs[1] = (TextView) view.findViewById(R.id.type2);
        tvs[2] = (TextView) view.findViewById(R.id.type3);
        tvs[3] = (TextView) view.findViewById(R.id.type4);
        tvs[4] = (TextView) view.findViewById(R.id.type5);
        tvs[5] = (TextView) view.findViewById(R.id.type6);

        for (int i = 0; i < textviewCount; i++)
            tvs[i].setOnClickListener(this);
    }//textViewInit

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type1:
                textViewCheck(0, v);
                break;
            case R.id.type2:
                textViewCheck(1, v);
                break;
            case R.id.type3:
                textViewCheck(2, v);
                break;
            case R.id.type4:
                textViewCheck(3, v);
                break;
            case R.id.type5:
                textViewCheck(4, v);
                break;
            case R.id.type6:
                textViewCheck(5, v);
                break;
        }
    }//onClick

    public void textViewCheck(int arrayposition, View v) {
        if (!flags[arrayposition]) {
            tvs[arrayposition].setBackgroundColor(getContext().getResources().getColor(R.color.mainColor));
            tvs[arrayposition].setTextColor(getContext().getResources().getColor(R.color.white));
            flags[arrayposition] = true;
        } else {
            tvs[arrayposition].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.filtering_border_base));
            tvs[arrayposition].setTextColor(getContext().getResources().getColor(R.color.black));
            flags[arrayposition] = false;
        }
    }//textViewCheck
}//FragmentSelf
