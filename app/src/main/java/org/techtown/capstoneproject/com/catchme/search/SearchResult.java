package org.techtown.capstoneproject.com.catchme.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;

public class SearchResult extends AppCompatActivity {

    TextView tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        intent = getIntent();
        String word = intent.getStringExtra("gradiant");
        tv = (TextView) findViewById(R.id.gradiant);
        Toast.makeText(this,word,Toast.LENGTH_SHORT).show();
        tv.setText(word);
    }
}
