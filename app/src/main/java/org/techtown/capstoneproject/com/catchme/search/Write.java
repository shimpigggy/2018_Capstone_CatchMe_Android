package org.techtown.capstoneproject.com.catchme.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;

/**
 * Created by ShimPiggy on 2018-05-07.
 * Modified by sj-kalin on 2018-05-09
 */

public class Write extends Fragment {
    public Write() {
    }

    String[] item = {"hello", "hell", "hhppp", "hhqwe", "heqwe"};
    AutoCompleteTextView actv;
    TextView tv;
    LinearLayout layout;
    LinearLayout topView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_write, container, false);
        actv = (AutoCompleteTextView) view.findViewById(R.id.actv);
        layout = (LinearLayout) view.findViewById(R.id.mainview);
        topView = (LinearLayout) view.findViewById(R.id.topview);

        topView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setVisibility(View.VISIBLE);
                return false;
            }
        });

        actv.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, item));
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
                Intent intent = new Intent(getContext(), SearchResult.class);
                intent.putExtra("gradiant", item[position]);
                startActivity(intent);
            }
        });

        return view;
    }

}
