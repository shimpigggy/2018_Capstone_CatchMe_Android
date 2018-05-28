package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView title;
    CheckAdapter checkAdapter;
    ArrayList<TestDTO> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_check);

        init();
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        // arrayList = new ArrayList<TestDTO>();

        //receive info from Modification
        arrayList = (ArrayList<TestDTO>) getIntent().getSerializableExtra("list");

        checkAdapter = new CheckAdapter(Check.this, arrayList);
        listView.setAdapter(checkAdapter);
    }//init

    public void inputData() {

    }//inputData

    public void actionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}