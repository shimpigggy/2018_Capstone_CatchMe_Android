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

    //server
    Retrofit retrofit;
    ApiService_Chemical apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_modification);

        init();
        inputData();

        resultModificationAdapter = new ResultModificationAdapter(ResultModification.this, arrayList);
        listView.setAdapter(resultModificationAdapter);

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
        arrayList = new ArrayList<Item>();
        im_check = (ImageButton) findViewById(R.id.check);

        //server
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService_Chemical.API_URL).build();

        apiService = retrofit.create(ApiService_Chemical.class);
    }

    public void inputData() {
        //server
        Call<ResponseBody> getNameList = apiService.getNameList("");
        getNameList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //데이터가 받아지면 호출
                try {
                    String result = response.body().string();
                    Log.e(">>>>>TEST", result);

                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        Item[] items = new Item[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            items[i] = new Item();
                            items[i].setNum(i+1);
                            items[i].setName(jsonArray.getString(i));
                            items[i].setBool(true,true,true);
                            Log.e(">>>>>TEST", items[i].getName());
                            arrayList.add(items[i]);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }//JSONArray

                } catch (IOException e) {
                    e.printStackTrace();
                }//IO
            }//onResponse

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Fail", call.toString());
                Log.e("Fail", "Fail");
                //데이터가 받아지는 것이 실패
            }//onFailure
        });

        /*Item item1 = new Item(1, "아세틸 글리콜", true, true, true);
        arrayList.add(item1);
        Item item2 = new Item(2, "알콜", false, false, true);
        arrayList.add(item2);
        Item item3 = new Item(3, "쉐어버터", true, true, false);
        arrayList.add(item3);
        Item item4 = new Item(4, "탄산수", false, true, false);
        arrayList.add(item4);*/
    }//inputData

    public void actionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}//ResultModification
