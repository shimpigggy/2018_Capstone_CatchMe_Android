package org.techtown.capstoneproject.tab.second.search;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService_Chemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.SearchResult;

import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by sj-kalin on 2018-05-09.
 * Modified by ShimPiggy on 2018-05-19.(tab 2 적용)
 * Modified by ShimPiggy on 2018-05-23. - result_modification에서 넘기기는 부분
 */

public class WriteChemical extends AppCompatActivity {
    public static String[] item = null;
    AutoCompleteTextView actv;
    TextView tv;
    LinearLayout layout;
    LinearLayout topView;
    Intent intent;

    Retrofit retrofit;
    ApiService_Chemical apiService_chemical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_write_chemical);

        Toast.makeText(this, Arrays.toString(item), Toast.LENGTH_SHORT).show();

        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        layout = (LinearLayout) findViewById(R.id.mainview);
        topView = (LinearLayout) findViewById(R.id.topview);
        TextView tv = (TextView) findViewById(R.id.tv);
        SearchResult.chemicalDTO = new ChemicalDTO();

        tv.setText(Arrays.toString(item));
        intent = getIntent();
        // getIntentInfo(intent);

        topView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setVisibility(View.VISIBLE);
                return false;
            }
        });

        actv.setAdapter(new ArrayAdapter<String>(WriteChemical.this, android.R.layout.simple_dropdown_item_1line, item));
        actv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setVisibility(View.GONE);
                return false;
            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(WriteChemical.this, SearchResult.class);
                retrofit = new Retrofit.Builder().baseUrl(ApiService_Chemical.API_URL).build();
                apiService_chemical = retrofit.create(ApiService_Chemical.class);
                Log.i("ss", actv.getText().toString());
                Call<ResponseBody> getInfo = apiService_chemical.getInfo(actv.getText().toString());
                getInfo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String temp = response.body().string();
                            JSONObject jsonObject = new JSONObject(temp);
                            SearchResult.chemicalDTO.setId(jsonObject.getString("id"));
                            SearchResult.chemicalDTO.setNameK(jsonObject.getString("nameK"));
                            SearchResult.chemicalDTO.setNameE(jsonObject.getString("nameE"));
                            SearchResult.chemicalDTO.setCas(jsonObject.getString("cas"));
                            SearchResult.chemicalDTO.setDefinition(jsonObject.getString("definition"));
                            SearchResult.chemicalDTO.setUsed(jsonObject.getString("used"));
                            SearchResult.chemicalDTO.setGoodFor("goodFor");
                            SearchResult.chemicalDTO.setBadFor("badFor");
                            SearchResult.chemicalDTO.setFunctionFor("functionFor");
                            SearchResult.chemicalDTO.setAllergy("allergy");
                            SearchResult.chemicalDTO.setWarning("warning");
                            SearchResult.chemicalDTO.setProductList("productList");

                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                startActivity(intent);
            }
        });
    }//onCreate

    public void getIntentInfo(Intent intent) {
        String type = intent.getStringExtra("type");

        if (type.equals("result_modification")) {
            //result_modification에서 온 경우
            actv.setText(intent.getStringExtra("modify_name"));

        } else if (type.equals("tab")) {
            //tab에서 온 경우
        }
    }//getIntentValue

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

}
