package org.techtown.capstoneproject.result_check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.Item;
import org.techtown.capstoneproject.R;

import java.util.ArrayList;

public class ResultCheck extends AppCompatActivity {
    ListView listView;
    TextView title;
    ResultCheckAdapter listviewAdapter;
    ArrayList<Item> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_check);

        init();
        inputData();

        listviewAdapter = new ResultCheckAdapter(ResultCheck.this, arrayList);
        listView.setAdapter(listviewAdapter);
    }
    public void init() {
        listView = (ListView) findViewById(R.id.listview);
       // arrayList = new ArrayList<Item>();

        arrayList = (ArrayList<Item>)getIntent().getSerializableExtra("list");
    }

    public void inputData() {
       // for(int i=0; i< arrayList.size();i++){
       //     arrayList.get(i).setNum(i+1);
       // }
    }//inputData
}