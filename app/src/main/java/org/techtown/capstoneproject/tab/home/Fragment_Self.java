package org.techtown.capstoneproject.tab.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.tab.tab3_self.Filtering;

/*
 * Created by ShimPiggy on 2018-05-19.
 * Modified by ShimPiggy on 2018-05-23. - image

 */
public class Fragment_Self extends Fragment {
    private ImageButton btn_filtering;
    private ImageButton btn_allowance;


    public Fragment_Self() {
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
    }//onCreateView

    public void Init(View view) {
        btn_filtering = (ImageButton) view.findViewById(R.id.btn_filtering);
        btn_allowance = (ImageButton) view.findViewById(R.id.btn_allowance);
    }//init

    public void buttonSetting() {
        btn_filtering.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Filtering!", Toast.LENGTH_SHORT).show();
                ButtonFilteringListener(v);
            }
        });

        btn_allowance.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Allowance!", Toast.LENGTH_SHORT).show();
                ButtonAllowanceListener(v);
            }
        });
    }//buttonSetting

    public void ButtonFilteringListener(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), Filtering.class);
        startActivity(intent);
    }//ButtonFilteringListener

    public void ButtonAllowanceListener(View v) {

    }//ButtonAllowanceListener
}//Fragment_Self
