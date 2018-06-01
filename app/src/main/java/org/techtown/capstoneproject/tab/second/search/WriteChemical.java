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
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.Modification;
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

    public static final int TAB = 1;
    public static final int MODIFICATION = 2;
    public static int page;

    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_write_chemical);

        Toast.makeText(this, Arrays.toString(item), Toast.LENGTH_SHORT).show();

        init();//전역 함수에 대한 초기화

        tv.setText(Arrays.toString(item));
        intent = getIntent();
        //getIntentInfo(intent);// 어디서 부터 이 activity를 넘겨 왔는지 알려주는 함수

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
                final Intent next = new Intent(WriteChemical.this, SearchResult.class);
                ;
               /* if (page == TAB) {
                    next = new Intent(WriteChemical.this, SearchResult.class);
                }else if(page == MODIFICATION){
                    next = new Intent(WriteChemical.this, Modification.class);
                }*/
                retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
                apiService_chemical = retrofit.create(ApiServiceChemical.class);

                Log.i("ss", actv.getText().toString());

                Call<ResponseBody> getInfo = apiService_chemical.getInfo(actv.getText().toString());
                getInfo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String temp = response.body().string();
                            JSONObject jsonObject = new JSONObject(temp);
                            SearchResult.chemicalDTO = new ChemicalDTO();
                            SearchResult.chemicalDTO.setNameK(jsonObject.getString("nameK"));
                            SearchResult.chemicalDTO.setNameE(jsonObject.getString("nameE"));
                            SearchResult.chemicalDTO.setCas(jsonObject.getString("cas"));
                            SearchResult.chemicalDTO.setDefinition(jsonObject.getString("definition"));
                            SearchResult.chemicalDTO.setUsed(jsonObject.getString("used"));
                            SearchResult.chemicalDTO.setDryGood(jsonObject.getString("dryGood"));
                            SearchResult.chemicalDTO.setDryBad(jsonObject.getString("dryBad"));
                            SearchResult.chemicalDTO.setOilGood(jsonObject.getString("oilGood"));
                            SearchResult.chemicalDTO.setOilBad(jsonObject.getString("oilBad"));
                            SearchResult.chemicalDTO.setSensitiveGood(jsonObject.getString("sensitiveGood"));
                            SearchResult.chemicalDTO.setSensitiveBad(jsonObject.getString("sensitiveBad"));
                            SearchResult.chemicalDTO.setComplexBad(jsonObject.getString("complexBad"));
                            SearchResult.chemicalDTO.setFunctionFor(jsonObject.getString("functionFor"));
                            SearchResult.chemicalDTO.setAllergy(jsonObject.getString("allergy"));
                            SearchResult.chemicalDTO.setWarning(jsonObject.getString("warning"));
                            SearchResult.chemicalDTO.setAcne(jsonObject.getString("acne"));
                            SearchResult.chemicalDTO.setBaby(jsonObject.getString("baby"));
                            SearchResult.chemicalDTO.setProductList(jsonObject.getString("productList"));
                            Log.d("searchDTO", SearchResult.chemicalDTO.toString());
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });
                startActivity(next);
            }
        });
    }//onCreate

    public void init() {
        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        layout = (LinearLayout) findViewById(R.id.mainview);
        topView = (LinearLayout) findViewById(R.id.topview);
        tv = (TextView) findViewById(R.id.tv);

        SearchResult.chemicalDTO = new ChemicalDTO();
    }

    public void getIntentInfo(Intent intent) {
        String type = intent.getStringExtra("type");

        if (type.equals("result_modification")) {
            //result_modification에서 온 경우
            actv.setText(intent.getStringExtra("modify_name"));
            page = MODIFICATION;

        } else if (type.equals("tab")) {
            //tab에서 온 경우
            page = TAB;
        }
    }//getIntentValue

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar
}
