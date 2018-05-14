package org.techtown.capstoneproject.result_check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.R;

import java.util.ArrayList;

public class ResultCheck extends AppCompatActivity {
    ListView listView;
    TextView title;
    ResultCheckAdapter listviewAdapter;
    ArrayList<ResultCheckItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_check);

        init();
        inputData(4);

        listviewAdapter = new ResultCheckAdapter(ResultCheck.this, arrayList);
        listView.setAdapter(listviewAdapter);
    }
    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<ResultCheckItem>();

    }

    public void inputData(int size) {
        /*ListviewItem[] items = new ListviewItem[size];

        for(int i =0; i< size; i++){
            items[i].setNum(i+1);
            items[i].setName("");
            arrayList.add(items[i]);
        }*/

        ResultCheckItem item1 = new ResultCheckItem(1, "아세틸 글리콜",true,true,true);
        arrayList.add(item1);
        ResultCheckItem item2 = new ResultCheckItem(2, "알콜",false,false,true);
        arrayList.add(item2);
        ResultCheckItem item3 = new ResultCheckItem(3, "쉐어버터",true,true,false);
        arrayList.add(item3);
        ResultCheckItem item4 = new ResultCheckItem(4, "탄산수",false,true,false);
        arrayList.add(item4);
    }
}