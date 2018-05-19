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

import java.util.regex.Pattern;

/**
 * Created by ShimPiggy on 2018-05-07.
 */

public class Fragment_Email extends Fragment {
    Spinner spinner;
    ImageButton button_send;
    EditText editText_email;
    EditText editText_title;
    EditText editText_context;

    public Fragment_Email() {
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
                String email = editText_email.getText() + "";
                if (checkEmailForm(email))
                    if (spinner.getSelectedItemPosition() != 0)
                        if (!editText_title.getText().toString().equals(""))
                            if(!editText_context.getText().toString().equals(""))
                                dialog();
                            else
                                Toast.makeText(getActivity().getApplicationContext(), "내용이 없음", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity().getApplicationContext(), "제목을 적지 않음", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity().getApplicationContext(), "분류를 고르지 않음", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity().getApplicationContext(), "이메일 형식 안맞음", Toast.LENGTH_SHORT).show();
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

    //email 체크 함수
    public boolean checkEmailForm(String src) {
        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        return Pattern.matches(emailRegex, src);
    }

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
}//Fragment_Email
