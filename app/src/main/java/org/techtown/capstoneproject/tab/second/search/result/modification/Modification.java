package org.techtown.capstoneproject.tab.second.search.result.modification;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.Check;

import java.util.ArrayList;

/*
 * Created by ShimPiggy on 2018-05-12.
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. -actionbar, image
 */

public class Modification extends AppCompatActivity {
    ListView listView;
    TextView title;
    ImageButton im_check;
    ModificationAdapter resultModificationAdapter;

    static ArrayList<TestDTO> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_modification);

        init();

        im_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Check.class);

                intent.putExtra("result", arrayList);

                startActivity(intent);
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        im_check = (ImageButton) findViewById(R.id.check);

        arrayList = (ArrayList<TestDTO>) getIntent().getSerializableExtra("result");

        resultModificationAdapter = new ModificationAdapter(Modification.this, arrayList);
        listView.setAdapter(resultModificationAdapter);
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}//Modification
