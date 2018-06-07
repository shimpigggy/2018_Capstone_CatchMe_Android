package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;
import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.ProductNameDTO;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 * Created by ShimPiggy on 2018-05-14. - View
 * Modified by ShimPiggy on 2018-05-09 - receive info from Modification
 * Modified by ShimPiggy on 2018-05-23 - actionBar, image
 */

public class Check extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    CheckAdapter checkAdapter;

    RelativeLayout bottom;
    RelativeLayout explain;
    ImageButton home;

    ArrayList<TestDTO> arrayList;
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

        listView.setOnItemClickListener(this);
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

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //선택한 listview의 값
        data = (ChemicalDTO) parent.getItemAtPosition(position);
        Log.e("List",data.getNameK());

        serverData();
    }

    public void serverData() {
        loading();
        retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
        apiService_chemical = retrofit.create(ApiServiceChemical.class);

        Call<ResponseBody> getInfo = apiService_chemical.getInfo(data.getNameK());
        getInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String temp = response.body().string();
                    JSONObject jsonObject = new JSONObject(temp);

                    dto = new ChemicalDTO();

                    dto.setNameK(jsonObject.getString("nameK"));
                    dto.setNameE(jsonObject.getString("nameE"));
                    dto.setCas(jsonObject.getString("cas"));
                    dto.setDefinition(jsonObject.getString("definition"));
                    dto.setUsed(jsonObject.getString("used"));
                    dto.setDryGood(jsonObject.getString("dryGood"));
                    dto.setDryBad(jsonObject.getString("dryBad"));
                    dto.setOilGood(jsonObject.getString("oilGood"));
                    dto.setOilBad(jsonObject.getString("oilBad"));
                    dto.setSensitiveGood(jsonObject.getString("sensitiveGood"));
                    dto.setSensitiveBad(jsonObject.getString("sensitiveBad"));
                    dto.setComplexBad(jsonObject.getString("complexBad"));
                    dto.setFunctionFor(jsonObject.getString("functionFor"));
                    dto.setAllergy(jsonObject.getString("allergy"));
                    dto.setWarning(jsonObject.getString("warning"));
                    dto.setAcne(jsonObject.getString("acne"));
                    dto.setProductList(jsonObject.getString("productList"));
                    Log.d("searchDTO", dto.toString());
                    loadingEnd = 0;
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    ProgressDialog progressDialog;

    public void loading() {
        progressDialog = ProgressDialog.show(Check.this, "", "성분 정보를 받고 있습니다.");
        progressDialog.setCancelable(true);

        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Log.e("loadEnd", loadingEnd + "");
            //msg의 값과 loadingEnd값이 같지 않으면 loading이 계속 됨
            if (msg.what == loadingEnd) { // 타임아웃이 발생하면
                progressDialog.dismiss(); // ProgressDialog를 종료

                nextActivity();
            } else {
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public void nextActivity() {
        loadingEnd = 1;
        Intent intent = new Intent(Check.this, SearchResult.class);
        intent.putExtra("data",dto);
        startActivity(intent);
    }


}