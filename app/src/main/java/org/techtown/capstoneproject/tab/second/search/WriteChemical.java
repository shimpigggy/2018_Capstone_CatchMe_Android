package org.techtown.capstoneproject.tab.second.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.Modification;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.SearchResult;

import java.util.ArrayList;
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
    LinearLayout layout;
    LinearLayout topView;
    Intent intent;

    public static final int TAB = 1;
    public static final int MODIFICATION = 2;
    public static int PAGE;

    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;

    private final int LOADING = 0;
    private final int SUCCESS = 1;
    private final int TAB_ERROR = 2;
    private final int MODIFICATION_ERROR = 3;
    private final int GALLERY_ERROR = 4;
    private final int WRITE_ERROR = 5;
    private final int SERVER_ERROR = 6;
    private final int DONE = 7;
    private int loadingEnd = LOADING;

    private ArrayList<TestDTO> arrayList;// 임시
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_write_chemical);

        init();//전역 함수에 대한 초기화

        intent = getIntent();
        getIntentInfo(intent);// 어디서 부터 이 activity를 넘겨 왔는지 알려주는 함수

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
                loading();
                retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
                apiService_chemical = retrofit.create(ApiServiceChemical.class);

                Log.i("ss", actv.getText().toString());

                if (PAGE == MODIFICATION) {
                    // modificationFromServer();
                    resultFromServer();
                } else if (PAGE == TAB) {
                    resultFromServer();
                }
            }//onItemClick
        });
    }//onCreate

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void init() {
        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        layout = (LinearLayout) findViewById(R.id.mainview);
        topView = (LinearLayout) findViewById(R.id.topview);
    }

    public void getIntentInfo(Intent intent) {
        String type = intent.getStringExtra("type");

        if (type.equals("result_modification")) {
            //result_modification에서 온 경우
            actv.setText(intent.getStringExtra("modify_name"));
            PAGE = MODIFICATION;

            arrayList = (ArrayList<TestDTO>) getIntent().getSerializableExtra("backResult");
            //position = getIntent().getIntExtra("position", 0);

        } else if (type.equals("tab")) {
            //tab에서 온 경우
            PAGE = TAB;
        }
    }//getIntentInfo

    public void resultFromServer() {
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

                    actv.setText("");
                    loadingEnd = 0;
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Fail Error", call.toString());
            }
        });
    }//resultFromServer

    public void modificationFromServer() {
        Call<ResponseBody> getInfo = apiService_chemical.getInfo(actv.getText().toString());
        getInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String temp = response.body().string();
                    JSONObject jsonObject = new JSONObject(temp);
                    ChemicalDTO chemicalDTO = new ChemicalDTO();

                    chemicalDTO.setNameK(jsonObject.getString("nameK"));
                    chemicalDTO.setNameE(jsonObject.getString("nameE"));
                    chemicalDTO.setCas(jsonObject.getString("cas"));
                    chemicalDTO.setDefinition(jsonObject.getString("definition"));
                    chemicalDTO.setUsed(jsonObject.getString("used"));
                    chemicalDTO.setDryGood(jsonObject.getString("dryGood"));
                    chemicalDTO.setDryBad(jsonObject.getString("dryBad"));
                    chemicalDTO.setOilGood(jsonObject.getString("oilGood"));
                    chemicalDTO.setOilBad(jsonObject.getString("oilBad"));
                    chemicalDTO.setSensitiveGood(jsonObject.getString("sensitiveGood"));
                    chemicalDTO.setSensitiveBad(jsonObject.getString("sensitiveBad"));
                    chemicalDTO.setComplexBad(jsonObject.getString("complexBad"));
                    chemicalDTO.setFunctionFor(jsonObject.getString("functionFor"));
                    chemicalDTO.setAllergy(jsonObject.getString("allergy"));
                    chemicalDTO.setWarning(jsonObject.getString("warning"));
                    chemicalDTO.setAcne(jsonObject.getString("acne"));
                    chemicalDTO.setBaby(jsonObject.getString("baby"));
                    chemicalDTO.setProductList(jsonObject.getString("productList"));

                    Log.d("searchDTO", chemicalDTO.toString());

                    Modification.data.set(position, chemicalDTO);
                    loadingEnd = 0;
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Fail Error", call.toString());
            }
        });
    }//modificationFromServer

    ProgressDialog progressDialog;

    public void loading() {
        progressDialog = ProgressDialog.show(WriteChemical.this, "", "성분 정보를 받고 있습니다.");
        progressDialog.setCancelable(true);

        mHandler.sendEmptyMessageDelayed(SUCCESS, 2000);
    }

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Log.e("loadEnd", loadingEnd + "");
            //msg의 값과 loadingEnd값이 같지 않으면 loading이 계속 됨
            if (msg.what == loadingEnd) { // 타임아웃이 발생하면
                progressDialog.dismiss(); // ProgressDialog를 종료
                loadingEnd = DONE;
                nextActivity();}
            else {
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public void nextActivity() {
        if (PAGE == MODIFICATION) {
            final Intent next = new Intent(WriteChemical.this, Modification.class);

            next.putExtra("result", arrayList);//임시
            startActivity(next);
        } else if (PAGE == TAB) {
            final Intent next = new Intent(WriteChemical.this, SearchResult.class);
            startActivity(next);
        }
    }
}