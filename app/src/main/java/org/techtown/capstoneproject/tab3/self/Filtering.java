package org.techtown.capstoneproject.tab3.self;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;

public class Filtering extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private ImageButton ib_check;

    private boolean[] flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        init();
        buttonClick();
        textViewClick();
    }

    public void init() {
        tv1 = (TextView) findViewById(R.id.type1);
        tv2 = (TextView) findViewById(R.id.type2);
        tv3 = (TextView) findViewById(R.id.type3);
        tv4 = (TextView) findViewById(R.id.type4);
        tv5 = (TextView) findViewById(R.id.type5);
        tv6 = (TextView) findViewById(R.id.type6);
        tv7 = (TextView) findViewById(R.id.type7);
        tv8 = (TextView) findViewById(R.id.type8);
        tv9 = (TextView) findViewById(R.id.type9);

        ib_check = (ImageButton) findViewById(R.id.check);
        flags = new boolean[9];
        for (int i = 0; i < 9; i++)
            flags[i] = false;
    }

    public void buttonClick(){
        ib_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(v.getContext(),"Go!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void textViewClick() {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[0]) {
                    tv1.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv1.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[0] = true;
                } else {
                    tv1.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv1.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[0] = false;
                }
            }
        });//tv1

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[1]) {
                    tv2.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv2.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[1] = true;
                } else {
                    tv2.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv2.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[1] = false;
                }
            }
        });//tv2

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[2]) {
                    tv3.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv3.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[2] = true;
                } else {
                    tv3.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv3.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[2] = false;
                }
            }
        });//tv3

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[3]) {
                    tv4.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv4.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[3] = true;
                } else {
                    tv4.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv4.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[3] = false;
                }
            }
        });//tv4

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[4]) {
                    tv5.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv5.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[4] = true;
                } else {
                    tv5.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv5.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[4] = false;
                }
            }
        });//tv5

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[5]) {
                    tv6.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv6.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[5] = true;
                } else {
                    tv6.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv6.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[5] = false;
                }
            }
        });//tv6

        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[6]) {
                    tv7.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv7.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[6] = true;
                } else {
                    tv7.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv7.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[6] = false;
                }
            }
        });//tv7

        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[7]) {
                    tv8.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv8.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[7] = true;
                } else {
                    tv8.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv8.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[7] = false;
                }
            }
        });//tv8

        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flags[8]) {
                    tv9.setBackgroundColor(Filtering.this.getResources().getColor(R.color.filtering));
                    tv9.setTextColor(Filtering.this.getResources().getColor(R.color.white));
                    flags[8] = true;
                } else {
                    tv9.setBackgroundDrawable(ContextCompat.getDrawable(Filtering.this, R.drawable.filtering_border_base));
                    tv9.setTextColor(Filtering.this.getResources().getColor(R.color.black));
                    flags[8] = false;
                }
            }
        });//tv9
    }//clickIt
}
