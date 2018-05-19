package org.techtown.capstoneproject.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.techtown.capstoneproject.R;

/**
 * Created by ShimPiggy on 2018-05-19.
 */
public class Fragment_Self extends Fragment {
    private Button btn_filtering;
    private Button btn_allowance;

    public Fragment_Self(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_self, container, false);

        Init(view);
        buttonSetting();

        return view;
    }

    public void Init(View view) {
        btn_filtering = (Button) view.findViewById(R.id.btn_filtering);
        btn_allowance = (Button) view.findViewById(R.id.btn_allowance);
    }//init

    public void buttonSetting() {
        btn_filtering.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Filtering!",Toast.LENGTH_SHORT).show();
                ButtonFilteringListener(v);
            }
        });

        btn_allowance.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Allowance!",Toast.LENGTH_SHORT).show();
                ButtonAllowanceListener(v);
            }
        });
    }//buttonSetting

    public void ButtonFilteringListener(View v) {

    }//ButtonFilteringListener

    public void ButtonAllowanceListener(View v) {

    }//ButtonAllowanceListener
}//Fragment_Self
