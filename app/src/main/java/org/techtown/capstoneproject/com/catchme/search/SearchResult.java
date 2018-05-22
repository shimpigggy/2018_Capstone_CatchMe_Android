package org.techtown.capstoneproject.com.catchme.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;

public class SearchResult extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    RelativeLayout typeView;
    RelativeLayout typeViewResult;
    ImageView typeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        intent = getIntent();
        String word = intent.getStringExtra("gradiant");
        Toast.makeText(this, word, Toast.LENGTH_SHORT).show();
        typeView = (RelativeLayout) findViewById(R.id.type);
        typeViewResult = (RelativeLayout) findViewById(R.id.type_result);
        typeImage = (ImageView) findViewById(R.id.type_image);


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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.alergy || v.getId() == R.id.functionfor || v.getId() == R.id.product) {
            int visible = v.getVisibility();
            if (visible == View.VISIBLE) {
                v.setVisibility(View.GONE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }
}