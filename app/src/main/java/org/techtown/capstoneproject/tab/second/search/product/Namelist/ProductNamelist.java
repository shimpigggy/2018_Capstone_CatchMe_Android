package org.techtown.capstoneproject.tab.second.search.product.Namelist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;
import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.Check;
import org.techtown.capstoneproject.tab.second.search.result.modification.check.SearchResult;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductNamelist extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ProductNamelistAdapter productNamelistAdapter;
    ArrayList<TestDTO> arrayList;//뿌려주는 데이터
    TestDTO data;//클릭한 listview에 대한 정보

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
        // arrayList = new ArrayList<TestDTO>();

        //receive info from Modification
        arrayList = (ArrayList<TestDTO>) getIntent().getSerializableExtra("result");

        productNamelistAdapter = new ProductNamelistAdapter(ProductNamelist.this, arrayList);
        listView.setAdapter(productNamelistAdapter);
    }//init

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar'

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        /*
                // 두가지 방법 모두 사용가능하다.
//      Data data = (Data) parent.getItemAtPosition(position);
        Data data = mList.get(position);

        // 다음 액티비티로 넘길 Bundle 데이터를 만든다.
        Bundle extras = new Bundle();
        extras.putString("title", data.getTitle());
        extras.putString("description", data.getDescription());
        extras.putInt("color", data.getColor());

        // 인텐트를 생성한다.
        // 컨텍스트로 현재 액티비티를, 생성할 액티비티로 ItemClickExampleNextActivity 를 지정한다.
        Intent intent = new Intent(this, ItemClickExampleNextActivity.class);

        // 위에서 만든 Bundle을 인텐트에 넣는다.
        intent.putExtras(extras);

        // 액티비티를 생성한다.
        startActivity(intent);
        * */

        //선택한 listview의 값
        data = (TestDTO) parent.getItemAtPosition(position);

        //serverData();

        //임시 데이터
        Intent intent = new Intent(ProductNamelist.this, Check.class);
        intent.putExtra("result", arrayList);

        startActivity(intent);
    }

    public void serverData() {
        retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
        apiService_chemical = retrofit.create(ApiServiceChemical.class);

        Call<ResponseBody> getInfo = apiService_chemical.getInfo(data.getName());
        getInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String temp = response.body().string();
                    JSONObject jsonObject = new JSONObject(temp);

                    //해당 제품에 대한 화학성분


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
}