package org.techtown.capstoneproject.com.catchme.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.techtown.capstoneproject.R;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by sj-kalin on 2018-05-09.
 * Modified by ShimPiggy on 2018-05-19.(tab 2 적용)
 * Modified by ShimPiggy on 2018-05-23. - result_modification에서 넘기기는 부분
 */

public class WriteChemical extends AppCompatActivity {
    String[] item = {"hello", "hell", "hhppp", "hhqwe", "heqwe"};
    AutoCompleteTextView actv;
    TextView tv;
    LinearLayout layout;
    LinearLayout topView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_chemical);

        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        layout = (LinearLayout) findViewById(R.id.mainview);
        topView = (LinearLayout) findViewById(R.id.topview);

        intent = getIntent();
        getIntentInfo(intent);

        topView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setVisibility(View.VISIBLE);
                return false;
            }
        });

        actv.setAdapter(new ArrayAdapter<String>(WriteChemical.this, android.R.layout.simple_dropdown_item_1line, item));
        actv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setVisibility(View.GONE);
                return false;
            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WriteChemical.this, SearchResult.class);
                intent.putExtra("gradiant", item[position]);
                startActivity(intent);
            }
        });
    }//onCreate

    public void getIntentInfo(Intent intent){
        String type = intent.getStringExtra("type");

        if(type.equals("result_modification")){
            //result_modification에서 온 경우
            actv.setText(intent.getStringExtra("modify_name"));

        }else if(type.equals("tab")){
            //tab에서 온 경우
        }
    }//getIntentValue
}
