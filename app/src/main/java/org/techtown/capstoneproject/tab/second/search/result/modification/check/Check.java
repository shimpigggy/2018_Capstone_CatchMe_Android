package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.R;

import java.util.ArrayList;

/*
 * Created by ShimPiggy on 2018-05-14. - View
 * Modified by ShimPiggy on 2018-05-09 - receive info from Modification
 * Modified by ShimPiggy on 2018-05-23 - actionBar, image
 */

public class Check extends AppCompatActivity {
    ListView listView;
    CheckAdapter checkAdapter;
    ArrayList<TestDTO> arrayList;
    public static ArrayList<ChemicalDTO> data;

    RelativeLayout bottom;
    RelativeLayout explain;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_check);

        init();

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain.setVisibility(View.GONE);

                bottom.setBackgroundColor(getResources().getColor(R.color.background_remove));
                home.setVisibility(View.VISIBLE);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        // arrayList = new ArrayList<TestDTO>();

        bottom = (RelativeLayout) findViewById(R.id.bottom);
        explain = (RelativeLayout) findViewById(R.id.explain);
        home = (ImageButton) findViewById(R.id.home);
        home.setVisibility(View.INVISIBLE);

        //receive info from Modification
        arrayList = (ArrayList<TestDTO>) getIntent().getSerializableExtra("result");
        // data = (ArrayList<ChemicalDTO>) getIntent().getSerializableExtra("data");

        checkAdapter = new CheckAdapter(Check.this, arrayList);
        // checkAdapter = new CheckAdapter(Check.this, data);
        listView.setAdapter(checkAdapter);
    }//init

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}