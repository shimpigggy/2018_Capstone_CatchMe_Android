package org.techtown.capstoneproject.tab.second.search.result.modification;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.ProductNameDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.Check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 * Created by ShimPiggy on 2018-05-12.
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. -actionbar, image
 */

public class Modification extends AppCompatActivity {
    ListView listView;
    ImageButton im_check;
    ModificationAdapter resultModificationAdapter;

    private Retrofit retrofit;
    private ApiServiceChemical apiService_chemical;

    private final int LOADING = 0;
    private final int SUCCESS = 1;
    private final int ERROR = 2;
    private final int SERVER_ERROR = 6;
    private int loadingEnd = LOADING;

    public static ArrayList<ProductNameDTO> data;
    private List<String> server;
    public ArrayList<ChemicalDTO> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_modification);

        init();

        im_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    server.add(data.get(i).getProductName());
                }

                getChemicalDetailList();
            }
        });
    }//onCreate

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        im_check = (ImageButton) findViewById(R.id.check);

        data = (ArrayList<ProductNameDTO>) getIntent().getSerializableExtra("data");

        resultModificationAdapter = new ModificationAdapter(Modification.this, data);
        listView.setAdapter(resultModificationAdapter);
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    //자동완성을 위한 성분리스트 전체 항목을 불러온다.
    //write부분에서 사용
    private void getChemicalDetailList() {
        loading("잠시만 기달려 주세요.");
        retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
        apiService_chemical = retrofit.create(ApiServiceChemical.class);
        Call<ResponseBody> getList = apiService_chemical.getChemicalChemicalList(server);
        getList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String temp = response.body().string();

                    JSONObject jsonArray = new JSONObject(temp);
                    ChemicalDTO[] chemicalDTOS = new ChemicalDTO[jsonArray.length()];
                    result = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i+"");
                        chemicalDTOS[i] = new ChemicalDTO();

                        chemicalDTOS[i].setNum(i + 1);
                        chemicalDTOS[i].setNameK(jsonObject.getString("nameK"));
                        chemicalDTOS[i].setNameE(jsonObject.getString("nameE"));
                        chemicalDTOS[i].setCas(jsonObject.getString("cas"));
                        chemicalDTOS[i].setDefinition(jsonObject.getString("definition"));
                        chemicalDTOS[i].setUsed(jsonObject.getString("used"));
                        chemicalDTOS[i].setDryGood(jsonObject.getString("dryGood"));
                        chemicalDTOS[i].setDryBad(jsonObject.getString("dryBad"));
                        chemicalDTOS[i].setOilGood(jsonObject.getString("oilGood"));
                        chemicalDTOS[i].setOilBad(jsonObject.getString("oilBad"));
                        chemicalDTOS[i].setSensitiveGood(jsonObject.getString("sensitiveGood"));
                        chemicalDTOS[i].setSensitiveBad(jsonObject.getString("sensitiveBad"));
                        chemicalDTOS[i].setComplexBad(jsonObject.getString("complexBad"));
                        chemicalDTOS[i].setFunctionFor(jsonObject.getString("functionFor"));
                        chemicalDTOS[i].setAllergy(jsonObject.getString("allergy"));
                        chemicalDTOS[i].setWarning(jsonObject.getString("warning"));
                        chemicalDTOS[i].setAcne(jsonObject.getString("acne"));
                        chemicalDTOS[i].setProductList(jsonObject.getString("productList"));

                        Log.e("Check", chemicalDTOS[i].toString());

                        result.add(chemicalDTOS[i]);
                    }

                    loadingEnd = SUCCESS;
                } catch (IOException e) {
                    Log.i("retrofiError", e.getMessage());
                    loadingEnd = ERROR;
                } catch (Exception e) {
                    loadingEnd = ERROR;
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Modification", call.toString());
                loadingEnd = SERVER_ERROR;
            }
        });
    }//getChemicalDetailList

    ProgressDialog progressDialog;

    public void loading(String Message) {
        progressDialog = ProgressDialog.show(Modification.this, "", Message);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        mHandler.sendEmptyMessageDelayed(SUCCESS, 2000);
    }

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Log.e("loadEnd", loadingEnd + "");
            //msg의 값과 loadingEnd값이 같지 않으면 loading이 계속 됨
            if (loadingEnd == SUCCESS) { // 타임아웃이 발생하면
                progressDialog.dismiss(); // ProgressDialog를 종료
                nextActivity();
            } else if (loadingEnd == LOADING) {
                //같지 않을 경우 다시 실행
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public void nextActivity() {
        Log.e("넘어왔냐?", "YEAHs");
        loadingEnd = LOADING;

        Intent intent = new Intent(getApplicationContext(), Check.class);

        intent.putExtra("data", result);
        Log.e("loadEnd AFTER", loadingEnd + "");
        startActivity(intent);
    }//nextActivity
}//Modification
