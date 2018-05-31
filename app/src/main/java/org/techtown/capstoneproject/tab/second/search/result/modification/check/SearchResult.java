package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;

/*
 * Created by sj-kalin on 2018-05-07.
 * Modified by sj-kalin on 2018-05-22.
 * Modified by ShimPiggy on 2018-05-23. - receive info from intent
 */

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    RelativeLayout typeView;
    RelativeLayout typeViewResult;
    ImageView typeImage;
    public static ChemicalDTO chemicalDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_search_result);

        init();

        intent = getIntent();
        Toast.makeText(this, chemicalDTO.toString(), Toast.LENGTH_SHORT).show();
        Log.i("gg",chemicalDTO.toString());

        typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int visible = typeViewResult.getVisibility();
                if (visible == View.VISIBLE) {
                    typeViewResult.setVisibility(View.GONE);
                    typeImage.setImageResource(R.drawable.arrow_down);
                } else {
                    typeViewResult.setVisibility(View.VISIBLE);
                    typeImage.setImageResource(R.drawable.arrow_up);

                }
            }
        });
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void init() {
        intent = getIntent();

        typeView = (RelativeLayout) findViewById(R.id.used);
        typeViewResult = (RelativeLayout) findViewById(R.id.usedresult);
        typeImage = (ImageView) findViewById(R.id.used_image);

        chemicalSimpleDescription();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.allergy || v.getId() == R.id.functionfor || v.getId() == R.id.product) {
            int visible = v.getVisibility();
            if (visible == View.VISIBLE) {
                v.setVisibility(View.GONE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }//onClick

    public void chemicalSimpleDescription(){
        TextView nameK =(TextView)findViewById(R.id.nameK);
        nameK.setText(chemicalDTO.getNameK());

        TextView nameE =(TextView)findViewById(R.id.nameE);
        nameE.setText(chemicalDTO.getNameE());

        TextView cas =(TextView)findViewById(R.id.cas);
        cas.setText(chemicalDTO.getCas());

        TextView definition =(TextView)findViewById(R.id.definition);
        definition.setText(chemicalDTO.getDefinition());
    }//chemicalSimpleDescription

}//SearchResult