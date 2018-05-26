package org.techtown.capstoneproject.tab.fouth.inquiry;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-13. - modify view
 * Modified by ShimPiggy on 2018-05-20. - tab_email form check, blank check
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. - image
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService_Email;
import org.techtown.capstoneproject.service.dto.InquiryDTO;
import org.techtown.capstoneproject.tab.first.home.FragmentHome;
import org.techtown.capstoneproject.tab.second.search.result.modification.ResultModification;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentEmail extends Fragment {
    Spinner spinner;
    ImageButton button_send;
    EditText editText_email;
    EditText editText_title;
    EditText editText_context;

    //server
    Retrofit retrofit;
    ApiService_Email apiService;

    public FragmentEmail() {
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
                dialog();
                /*
                //tab_email form check
                String tab_email = editText_email.getText() + "";

                if (checkEmailForm(tab_email))
                    if (spinner.getSelectedItemPosition() != 0)
                        if (!editText_title.getText().toString().equals(""))
                            if (!editText_context.getText().toString().equals("")) {
                                // sendServeryInquiry();
                                dialog();
                            } else {//context X
                                editText_context.setText("");
                                Toast.makeText(getActivity().getApplicationContext(), "내용이 없음", Toast.LENGTH_SHORT).show();
                            }
                        else {//title X
                            editText_title.setText("");
                            Toast.makeText(getActivity().getApplicationContext(), "제목을 적지 않음", Toast.LENGTH_SHORT).show();
                        }
                    else {//spinner X
                        spinner.setSelection(0);
                        Toast.makeText(getActivity().getApplicationContext(), "분류를 고르지 않음", Toast.LENGTH_SHORT).show();
                    }
                else {//tab_email check
                    editText_email.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "이메일 형식 안맞음", Toast.LENGTH_SHORT).show();
                }*/
            }//onClick
        });//setOnClickListener
        return view;
    }//onCreateView

    public void init(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        button_send = (ImageButton) view.findViewById(R.id.send);
        editText_email = (EditText) view.findViewById(R.id.email);
        editText_context = (EditText) view.findViewById(R.id.content);
        editText_title = (EditText) view.findViewById(R.id.title_content);

        //server
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService_Email.API_URL).build();

        apiService = retrofit.create(ApiService_Email.class);
    }//init

    public void clean() {
        editText_email.setText("");
        editText_title.setText("");
        editText_context.setText("");
        spinner.setSelection(0);
    }//clean

    //tab_email 체크 함수
    public boolean checkEmailForm(String src) {
        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        return Pattern.matches(emailRegex, src);
    }//checkEmailForm

    public void sendServeryInquiry() {
        InquiryDTO dto = new InquiryDTO();

        dto.setContent(editText_context.getText().toString());
        dto.setEmail(editText_email.getText().toString());
        dto.setTitle(editText_title.getText().toString());
        dto.setType(spinner.getSelectedItem().toString());

        Call<JSONObject> sample2 = apiService.getPostCommentStr(dto);

        sample2.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                //데이터가 받아지면 호출
                try {
                    String result = response.code() + "";
                    Log.e(">>>>>ONE", result);
                    dialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//onResponse

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                //데이터가 받아지는 것이 실패
                Log.e("Fail", call.toString());
            }//onFailure
        });
    }//sendServeryInquiry

    public void dialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());

        ad.setTitle("").setMessage("문의가 성공적으로 완료되었습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                clean();

                /*//임시로 activity 로 가기
                Intent intent = new Intent(getActivity().getApplicationContext(), ResultModification.class);
                startActivity(intent);*/

                //끝나고 Home Tab으로 이동
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentHome fragment1 = new FragmentHome();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clean();
            }
        });

        AlertDialog dialog = ad.create();
        dialog.show();
    }//dialog
}//Fragment_Email
