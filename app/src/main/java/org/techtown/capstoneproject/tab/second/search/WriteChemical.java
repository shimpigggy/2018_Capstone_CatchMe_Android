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

import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.ProductNameDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.Modification;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.SearchResult;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private final int DONE = 7;
    private int loadingEnd = LOADING;

    //tab
    private ChemicalDTO dto;

    //modification
    private ArrayList<ProductNameDTO> arrayList;
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
                    modificationFromServer();
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
            PAGE = MODIFICATION;
            arrayList = (ArrayList<ProductNameDTO>) getIntent().getSerializableExtra("backResult");
            position = getIntent().getIntExtra("position", 0);
            actv.setText(getIntent().getStringExtra("modifyName"));

        } else if (type.equals("tab")) {
            //tab에서 온 경우
            PAGE = TAB;
        }
    }//getIntentInfo

    //tab에서 온거: SearchResult로 보내짐
    public void resultFromServer() {
        Call<ResponseBody> getInfo = apiService_chemical.getInfo(actv.getText().toString());
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
        String name = actv.getText().toString();

        ProductNameDTO change = new ProductNameDTO();
        change.setProductName(name);
        change.setNum(position+1);

        arrayList.set(position,change);
        Modification.data.set(position,change);
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
            next.putExtra("data", arrayList);
            startActivity(next);
        } else if (PAGE == TAB) {
            layout.setVisibility(View.INVISIBLE);

            final Intent next = new Intent(WriteChemical.this, SearchResult.class);
            next.putExtra("data",dto);
            startActivity(next);
        }
    }
}