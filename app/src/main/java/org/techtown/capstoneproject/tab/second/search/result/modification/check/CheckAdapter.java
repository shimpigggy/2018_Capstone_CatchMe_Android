package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import org.json.JSONObject;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.tab.second.search.WriteChemical;
import org.techtown.capstoneproject.tab.second.search.result.modification.Modification;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/*
 * Created by ShimPiggy on 2018-05-14.
 * Modified by ShimPiggy on 2018-05-23. - ib_check handler, server
 */

public class CheckAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    // private ArrayList<TestDTO> arrayList;
    private ArrayList<ChemicalDTO> arrayList;

    private TextView tv_num;
    private TextView tv_name;
    private ImageButton ib_check;
    private ImageView iv_yellow;
    private ImageView iv_pink;
    private ImageView iv_blue;

    private int loadingEnd = 1;
    private ChemicalDTO dto;

    //server
    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;

    public CheckAdapter(Context context, ArrayList<ChemicalDTO> array) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        //이 리스트뷰가 몇개의 아이템을 가지고 있는 지 알려주는 함수
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //현재 어떤 아이템인지 알려주는 함수
        //arrayList에서 저장되어있는 객체 중 position에 해당 하는 것을 가져옴
        return this.arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //현재 어떤 포지션인지를 알려준느 부분
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //리스트뷰에서 아이템과 xml를 연결하여 화면에 표시해주는 가장 중요한 부분
        //convertView -> 만든 item.xml를 불러와야함
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.result_check_item, null);
        }
        init(convertView);
        settting(position);
        settingSkinType(position, convertView);

        return convertView;
    }//getView

    public void init(View convertView) {
        tv_num = (TextView) convertView.findViewById(R.id.num);
        tv_name = (TextView) convertView.findViewById(R.id.name);

        ib_check = (ImageButton) convertView.findViewById(R.id.choose);
        iv_yellow = (ImageView) convertView.findViewById(R.id.yellow);
        iv_pink = (ImageView) convertView.findViewById(R.id.pink);
        iv_blue = (ImageView) convertView.findViewById(R.id.blue);
    }//init

    public void settting(int position) {
        tv_num.setText(arrayList.get(position).getNum() + "");
        tv_name.setText(arrayList.get(position).getNameK());

        ib_check.setTag(position);
        ib_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareData(v);
            }
        });//setOnClickListener
    }//setUI

    public void settingSkinType(int position, View convertView) {
/*        if (!arrayList.get(position).isYellow_b())
            iv_yellow.setVisibility(convertView.INVISIBLE);
        if (!arrayList.get(position).isPink_b())
            iv_pink.setVisibility(convertView.INVISIBLE);
        if (!arrayList.get(position).isBlue_b())
            iv_blue.setVisibility(convertView.INVISIBLE);*/

        if (arrayList.get(position).getOilGood().equals("") || arrayList.get(position).getOilGood() == null)
            iv_yellow.setVisibility(convertView.INVISIBLE);
        if (arrayList.get(position).getDryGood().equals("") || arrayList.get(position).getDryGood() == null)
            iv_pink.setVisibility(convertView.INVISIBLE);
        if (arrayList.get(position).getSensitiveGood().equals("") || arrayList.get(position).getSensitiveGood() == null)
            iv_blue.setVisibility(convertView.INVISIBLE);
    }//settingSkinType

    public void prepareData(View view) {
        loading();
        int position = Integer.parseInt(view.getTag().toString());

        retrofit = new Retrofit.Builder().baseUrl(ApiService.ADDRESS).build();
        apiService_chemical = retrofit.create(ApiServiceChemical.class);

        Call<ResponseBody> getInfo = apiService_chemical.getInfo(arrayList.get(position).getNameK());
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
        progressDialog = ProgressDialog.show(context, "", "성분 정보를 받고 있습니다.");
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
                mHandler.sendEmptyMessageDelayed(0, 200);
            }
        }
    };

    public void nextActivity() {
        loadingEnd = 1;
        Intent intent = new Intent(context, SearchResult.class);
        intent.putExtra("data",dto);
        context.startActivity(intent);
    }

}//CheckAdapter
