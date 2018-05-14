package org.techtown.capstoneproject.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.result_check.ResultCheck;

import java.util.ArrayList;

public class Result_ListView extends AppCompatActivity {

    ListView listView;
    TextView title;
    ImageButton im_check;
    ListviewAdapter listviewAdapter;
    ArrayList<ListviewItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__list_view);

        init();
        inputData(4);

        listviewAdapter = new ListviewAdapter(Result_ListView.this, arrayList);
        listView.setAdapter(listviewAdapter);

        im_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultCheck.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<ListviewItem>();
        im_check = (ImageButton) findViewById(R.id.check);
    }

    public void inputData(int size) {
        /*ListviewItem[] items = new ListviewItem[size];

        for(int i =0; i< size; i++){
            items[i].setNum(i+1);
            items[i].setName("");
            arrayList.add(items[i]);
        }*/

        ListviewItem item1 = new ListviewItem(1, "아세틸 글리콜");
        arrayList.add(item1);
        ListviewItem item2 = new ListviewItem(2, "알콜");
        arrayList.add(item2);
        ListviewItem item3 = new ListviewItem(3, "쉐어버터");
        arrayList.add(item3);
        ListviewItem item4 = new ListviewItem(4, "탄산수");
        arrayList.add(item4);
    }
}
