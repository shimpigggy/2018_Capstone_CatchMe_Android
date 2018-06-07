package org.techtown.capstoneproject.tab.second.search.product.Namelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiServiceChemical;
import org.techtown.capstoneproject.service.dto.ProductNameDTO;

import java.util.ArrayList;

import retrofit2.Retrofit;

/**
 * Created by shimp on 2018-06-03.
 */

public class ProductNamelistAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private static ArrayList<ProductNameDTO> arrayList = null;

    private TextView tv_num;
    private TextView tv_name;

    //server
    Retrofit retrofit;
    ApiServiceChemical apiService_chemical;

    public ProductNamelistAdapter(Context context, ArrayList<ProductNameDTO> array) {
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
            convertView = inflater.inflate(R.layout.product_namelist_item, null);

            init(convertView);
        }
        settting(position);

        return convertView;
    }//getView

    public void init(View convertView) {
        tv_num = (TextView) convertView.findViewById(R.id.num);
        tv_name = (TextView) convertView.findViewById(R.id.product_name);
    }//init

    public void settting(int position) {
        tv_num.setText(arrayList.get(position).getNum()+"");
        tv_name.setText(arrayList.get(position).getProductName());
    }//setUI
}//CheckAdapter