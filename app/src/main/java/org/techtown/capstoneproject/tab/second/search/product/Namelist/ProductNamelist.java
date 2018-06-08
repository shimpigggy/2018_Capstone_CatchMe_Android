package org.techtown.capstoneproject.tab.second.search.product.Namelist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.ProductNameDTO;
import org.techtown.capstoneproject.tab.fouth.inquiry.CustomDialog;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.Check;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductNamelist extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ProductNamelistAdapter productNamelistAdapter;
    public static ArrayList<ProductNameDTO> arrayList = null;//뿌려주는 데이터
    ProductNameDTO data;//클릭한 listview에 대한 정보

    private final int LOADING = 0;
    private final int SUCCESS = 1;
    private final int CHEMICAL_LIST_ERROR = 2;
    private final int SERVER_ERROR = 6;
    private final int DONE = 7;
    private int loadingEnd = LOADING;

    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;
    ArrayList<ChemicalDTO> serverData; //서버로 부터 받은 화학성분 데이터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_product_namelist);

        init();
        listView.setOnItemClickListener(this);
    }//onCreate

    public void init() {
        listView = (ListView) findViewById(R.id.listview);

        arrayList = (ArrayList<ProductNameDTO>) getIntent().getSerializableExtra("data");

        productNamelistAdapter = new ProductNamelistAdapter(ProductNamelist.this, arrayList);
        listView.setAdapter(productNamelistAdapter);
    }//init

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar'

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //선택한 listview의 값
        data = (ProductNameDTO) parent.getItemAtPosition(position);

        serverData();
    }

    public void serverData() {
        loading();
        retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
        apiService_chemical = retrofit.create(ApiServiceChemical.class);

        Log.e(" data server", data.getProductName());
        Call<ResponseBody> getInfo = apiService_chemical.getProductChemicalList(data.getProductName());
        getInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    serverData = new ArrayList<>();
                    String temp = response.body().string();

                    Log.e("temp", temp);

                    JSONArray jsonArray = new JSONArray(temp);
                    ChemicalDTO[] chemicalDTOS = new ChemicalDTO[jsonArray.length()];

                    Log.e("LENGTH", jsonArray.length() + "");

                    int i = 0;
                    for (int j = 0; j < jsonArray.length(); j++) {
                        if (!jsonArray.isNull(j)) {
                            JSONObject jsonObject = jsonArray.getJSONObject(j);

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

                            serverData.add(chemicalDTOS[i]);
                            i++;
                        }
                    }
                    loadingEnd = SUCCESS;

                } catch (
                        Exception e)

                {
                    Log.e("error", e.getMessage());
                    loadingEnd = CHEMICAL_LIST_ERROR;
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Fail Error", call.toString());
                loadingEnd = SERVER_ERROR;
            }
        });
    }//serverData

    ProgressDialog progressDialog;

    public void loading() {
        progressDialog = ProgressDialog.show(ProductNamelist.this, "", "해당 제품의 성분을 분석하고 있습니다.");
        progressDialog.setCancelable(false);

        mHandler.sendEmptyMessageDelayed(10, 2000);
    }

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Log.e("loadEnd", loadingEnd + "");
            //msg의 값과 loadingEnd값이 같지 않으면 loading이 계속 됨
            if (loadingEnd == SUCCESS) { // 타임아웃이 발생하면
                progressDialog.dismiss(); // ProgressDialog를 종료
                loadingEnd = DONE;
                nextActivity();
            } else if (loadingEnd == CHEMICAL_LIST_ERROR) {
                progressDialog.dismiss();
                loadingEnd = LOADING;

                CustomDialog dialog = new CustomDialog(ProductNamelist.this, "다시 눌러주세요.");
                dialog.setCancelable(false);
                dialog.show();
            } else if (loadingEnd == SERVER_ERROR) {
                progressDialog.dismiss();
                loadingEnd = LOADING;

                CustomDialog dialog = new CustomDialog(ProductNamelist.this, "서버가 불안정 합니다.\n다시 실행해 주세요.");
                dialog.setCancelable(false);
                dialog.show();
            } else if (loadingEnd == LOADING) {
                //같지 않을 경우 다시 실행
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public void nextActivity() {
        loadingEnd = LOADING;

        Intent intent = new Intent(ProductNamelist.this, Check.class);
        intent.putExtra("data", serverData);
        startActivity(intent);
    }//nextActivity
}