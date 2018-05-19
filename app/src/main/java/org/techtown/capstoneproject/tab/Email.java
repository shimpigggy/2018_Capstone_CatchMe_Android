package org.techtown.capstoneproject.tab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.result.Result_ListView;

/**
 * Created by ShimPiggy on 2018-05-07.
 */

public class Email extends Fragment {
    Spinner spinner;
    ImageButton button_send;
    EditText editText_email;
    EditText editText_title;
    EditText editText_context;

    public Email() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        init(view);

        button_send.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();

                dialog();
            }
        });//setOnClickListener
        return view;
    }//onCreateView

    public void dialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());

        ad.setTitle("").setMessage("문의가 성공적으로 완료되었습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();

                clean();

                Intent intent = new Intent(getActivity().getApplicationContext(), Result_ListView.class);
                startActivity(intent);
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = ad.create();
        dialog.show();
    }//dialog

    public void init(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        button_send = (ImageButton) view.findViewById(R.id.send);
        editText_email = (EditText) view.findViewById(R.id.emailInput);
        editText_context = (EditText) view.findViewById(R.id.contextInput);
        editText_title = (EditText) view.findViewById(R.id.titleInput);
    }//init

    public void clean() {
        editText_email.setText("");
        editText_title.setText("");
        editText_context.setText("");

        spinner.setSelection(0);
    }
}//Email
