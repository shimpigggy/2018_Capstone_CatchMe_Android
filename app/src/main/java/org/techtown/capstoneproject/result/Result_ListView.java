package org.techtown.capstoneproject.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.techtown.capstoneproject.tab.Item;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.result_check.ResultCheck;

import java.util.ArrayList;

public class Result_ListView extends AppCompatActivity {

    ListView listView;
    TextView title;
    ImageButton im_check;
    ListviewAdapter listviewAdapter;
    ArrayList<Item> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list_view);

        init();
        inputData(4);

        listviewAdapter = new ListviewAdapter(Result_ListView.this, arrayList);
        listView.setAdapter(listviewAdapter);

        im_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultCheck.class);

                intent.putExtra("list",arrayList);

                startActivity(intent);
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<Item>();
        im_check = (ImageButton) findViewById(R.id.check);
    }

    public void inputData(int size) {
        /*ListviewItem[] items = new ListviewItem[size];

        for(int i =0; i< size; i++){
            items[i].setNum(i+1);
            items[i].setName("");
            arrayList.add(items[i]);
        }*/

        Item item1 = new Item(1, "아세틸 글리콜",true, true, true);
        arrayList.add(item1);
        Item item2 = new Item(2, "알콜",false, false, true);
        arrayList.add(item2);
        Item item3 = new Item(3, "쉐어버터",true, true, false);
        arrayList.add(item3);
        Item item4 = new Item(4, "탄산수",false, true, false);
        arrayList.add(item4);
    }
}
