package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.R;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class Check extends AppCompatActivity{
    ListView listView;
    CheckAdapter checkAdapter;

    RelativeLayout bottom;
    RelativeLayout explain;
    ImageButton home;

    public static ArrayList<ChemicalDTO> list;

    //server
    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;

    ChemicalDTO data;
    private ChemicalDTO dto;
    private int loadingEnd = 1;

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

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void init() {
        listView = (ListView) findViewById(R.id.listview);

        bottom = (RelativeLayout) findViewById(R.id.bottom);
        explain = (RelativeLayout) findViewById(R.id.explain);
        home = (ImageButton) findViewById(R.id.home);
        home.setVisibility(View.INVISIBLE);

        //receive info from Modification
        list = (ArrayList<ChemicalDTO>) getIntent().getSerializableExtra("data");

        checkAdapter = new CheckAdapter(Check.this, list);
        listView.setAdapter(checkAdapter);
    }//init
}