package org.techtown.capstoneproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by ShimPiggy on 2018-05-07.
 */

public class Email extends Fragment {
    public Email() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_email, container, false);

        /*
        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), spinner.getItemAtPosition(position)+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/

        return v;//inflater.inflate(R.layout.fragment_email, container,false);


    }


    public void onClick(View v) {
        Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
      /*  AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());

        ad.setTitle("").setMessage("문의가 성공적으로 완료되었습니다.").setNeutralButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }

        }).create().show();*/
    }//onClick
}
