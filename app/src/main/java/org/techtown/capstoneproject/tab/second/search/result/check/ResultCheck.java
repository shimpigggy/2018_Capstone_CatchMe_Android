package org.techtown.capstoneproject.tab.second.search.result.check;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.tab.second.search.result.Item;
import org.techtown.capstoneproject.R;

import java.util.ArrayList;

/*
 * Created by ShimPiggy on 2018-05-14. - View
 * Modified by ShimPiggy on 2018-05-09 - receive info from ResultModification
 * Modified by ShimPiggy on 2018-05-23 - actionBar, image
 */

public class ResultCheck extends AppCompatActivity {
    ListView listView;
    TextView title;
    ResultCheckAdapter resultCheckAdapter;
    ArrayList<Item> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_check);

        init();

    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        // arrayList = new ArrayList<Item>();

        //receive info from ResultModification
        arrayList = (ArrayList<Item>) getIntent().getSerializableExtra("list");


        resultCheckAdapter = new ResultCheckAdapter(ResultCheck.this, arrayList);
        listView.setAdapter(resultCheckAdapter);
    }//init

    public void inputData() {

    }//inputData

    public void actionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}