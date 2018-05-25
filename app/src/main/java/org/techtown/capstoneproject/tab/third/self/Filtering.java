package org.techtown.capstoneproject.tab.third.self;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.content.SharedPreferences;

import org.techtown.capstoneproject.R;

/*
 * Created by ShimPiggy on 2018-05-19.
 *  Modified by ShimPiggy on 2018-05-23. -modify
 */

public class Filtering extends AppCompatActivity implements View.OnClickListener {
    private TextView[] tvs;
    private ImageButton ib_check;

    private boolean[] flags;

    private final int textviewCount = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_filtering);

        init();
        buttonClick();
    }//onCreate

    public void actionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void init() {
        tvs = new TextView[textviewCount];
        textViewInit();

        SharedPreferences filtering = getSharedPreferences("filtering", MODE_PRIVATE);

        ib_check = (ImageButton) findViewById(R.id.check);

        flags = new boolean[textviewCount];
        for (int i = 0; i < textviewCount; i++) {
            if (!filtering.getBoolean("flags" + i + "", false))
                flags[i] = true;
            else
                flags[i] = false;
            textViewCheck(i);
        }
    }//init

    public void buttonClick() {
        ib_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Change!", Toast.LENGTH_SHORT).show();

                allClean();
                setFlags();
                finish();
            }
        });
    }//buttonClick

    private void setFlags() {
        //다시 setting
        SharedPreferences filtering = getSharedPreferences("filtering", MODE_PRIVATE);
        SharedPreferences.Editor editor = filtering.edit();

        for (int i = 0; i < textviewCount; i++) {
            editor.putBoolean("flags" + i + "", flags[i]);
        }
        editor.commit();
    }//setFlags

    private void allClean() {
        //모든 데이터 없애기
        SharedPreferences filtering = getSharedPreferences("filtering", MODE_PRIVATE);
        SharedPreferences.Editor editor = filtering.edit();
        editor.clear();
        editor.commit();
    }//allClean

    public void textViewInit() {
        tvs[0] = (TextView) findViewById(R.id.type1);
        tvs[1] = (TextView) findViewById(R.id.type2);
        tvs[2] = (TextView) findViewById(R.id.type3);
        tvs[3] = (TextView) findViewById(R.id.type4);
        tvs[4] = (TextView) findViewById(R.id.type5);
        tvs[5] = (TextView) findViewById(R.id.type6);
        tvs[6] = (TextView) findViewById(R.id.type7);

        for (int i = 0; i < textviewCount; i++)
            tvs[i].setOnClickListener(this);
    }//textViewInit

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type1:
                textViewCheck(0);
                break;
            case R.id.type2:
                textViewCheck(1);
                break;
            case R.id.type3:
                textViewCheck(2);
                break;
            case R.id.type4:
                textViewCheck(3);
                break;
            case R.id.type5:
                textViewCheck(4);
                break;
            case R.id.type6:
                textViewCheck(5);
                break;
            case R.id.type7:
                textViewCheck(6);
                break;
        }
    }//onClick

    public void textViewCheck(int arrayposition) {
        if (!flags[arrayposition]) {
            tvs[arrayposition].setBackgroundColor(Filtering.this.getResources().getColor(R.color.mainColor));
            tvs[arrayposition].setTextColor(Filtering.this.getResources().getColor(R.color.white));
            flags[arrayposition] = true;
        } else {
            tvs[arrayposition].setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
            tvs[arrayposition].setTextColor(Filtering.this.getResources().getColor(R.color.black));
            flags[arrayposition] = false;
        }
    }//textViewCheck
}//Filtering
