package org.techtown.capstoneproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by ShimPiggy on 2018-05-12.
 */

public class ListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListviewItem> arrayList;

    private TextView tv_num;
    private TextView tv_name;
    private ImageButton ib_nodify;
    private ImageButton ib_delete;

    public ListviewAdapter(Context context, ArrayList<ListviewItem> array){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = array;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //리스트뷰에서 아이템과 xml를 연결하여 화면에 표시해주는 가장 중요한 부분
        //convertView -> 만든 item.xml를 불러와야함
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_listview, null);

            tv_num = (TextView) convertView.findViewById(R.id.num);
            tv_name = (TextView) convertView.findViewById(R.id.name);

            ib_nodify = (ImageButton) convertView.findViewById(R.id.nodify);
            ib_delete = (ImageButton) convertView.findViewById(R.id.delete);
        }

        tv_num.setText(arrayList.get(position).getNum());
        tv_name.setText(arrayList.get(position).getName());

        return convertView;
    }
}
