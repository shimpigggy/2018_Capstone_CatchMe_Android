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

import org.json.JSONArray;
import org.json.JSONException;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.tab.second.search.result.Item;
import org.techtown.capstoneproject.tab.second.search.result.check.ResultCheck;
import org.techtown.capstoneproject.service.api.ApiService_Chemical;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by ShimPiggy on 2018-05-12.
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. -actionbar, image
 */

public class ResultModification extends AppCompatActivity {
    ListView listView;
    TextView title;
    ImageButton im_check;
    ResultModificationAdapter resultModificationAdapter;

    ArrayList<Item> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_modification);

        init();
        inputData();

        im_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultCheck.class);

                intent.putExtra("list", arrayList);

                startActivity(intent);
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        im_check = (ImageButton) findViewById(R.id.check);

        //arrayList = (ArrayList<Item>) getIntent().getSerializableExtra("list");
        arrayList = new ArrayList<>();

        resultModificationAdapter = new ResultModificationAdapter(ResultModification.this, arrayList);
        listView.setAdapter(resultModificationAdapter);
    }

    public void inputData() {
        //임시 데이터
        Item[] items = new Item[5];

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i + 1, "asdf",true,true,true);
            arrayList.add(items[i]);
        }
    }//inputData

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}//ResultModification
