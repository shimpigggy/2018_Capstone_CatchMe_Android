package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.techtown.capstoneproject.R;

import retrofit2.Retrofit;

public class CheckAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ChemicalDTO> arrayList;

    private TextView tv_num;
    private TextView tv_name;
    private ImageButton ib_check;
    private ImageView iv_yellow;
    private ImageView iv_pink;
    private ImageView iv_blue;

    private int loadingEnd = 1;
    private ChemicalDTO dto;

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
        iv_yellow.setVisibility(View.INVISIBLE);

        iv_pink = (ImageView) convertView.findViewById(R.id.pink);
        iv_pink.setVisibility(View.INVISIBLE);

        iv_blue = (ImageView) convertView.findViewById(R.id.blue);
        iv_blue.setVisibility(View.INVISIBLE);
    }//init

    public void settting(final int position) {
        tv_num.setText(arrayList.get(position).getNum() + "");

        tv_name.setText(arrayList.get(position).getNameK());
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResult.class);
                intent.putExtra("data", arrayList.get(position));
                context.startActivity(intent);
            }
        });

        ib_check.setTag(position);
        ib_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResult.class);
                intent.putExtra("data", arrayList.get(position));
                context.startActivity(intent);
            }
        });//setOnClickListener
    }//settting

    public void settingSkinType(int position, View convertView) {
        if (!arrayList.get(position).getOilGood().equals(""))
            iv_yellow.setVisibility(convertView.VISIBLE);
        if (!arrayList.get(position).getDryGood().equals(""))
            iv_pink.setVisibility(convertView.VISIBLE);
        if (!arrayList.get(position).getSensitiveGood().equals(""))
            iv_blue.setVisibility(convertView.VISIBLE);
    }//settingSkinType
}//CheckAdapter
