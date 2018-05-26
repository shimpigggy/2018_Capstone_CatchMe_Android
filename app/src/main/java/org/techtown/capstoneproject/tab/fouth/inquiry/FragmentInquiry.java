package org.techtown.capstoneproject.tab.fouth.inquiry;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-13. - modify view
 * Modified by ShimPiggy on 2018-05-20. - tab_inquiry form check, blank check
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. - image
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService_Email;
import org.techtown.capstoneproject.service.dto.InquiryDTO;
import org.techtown.capstoneproject.tab.first.home.FragmentHome;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentInquiry extends Fragment {
    Spinner spinner;
    ImageButton button_send;
    EditText editText_email;
    EditText editText_title;
    EditText editText_context;

    //server
    Retrofit retrofit;
    ApiService_Email apiService;

    public FragmentInquiry() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquiry, container, false);

        init(view);

        button_send.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                dialog();

                //끝나고 Home Tab으로 이동
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentHome fragment1 = new FragmentHome();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();

                /*
                //tab_inquiry form check
                String tab_inquiry = editText_email.getText() + "";

                if (checkEmailForm(tab_inquiry))
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
                else {//tab_inquiry check
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

    //tab_inquiry 체크 함수
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

        CustomDialog oDialog = new CustomDialog(getContext(), "문의가 완료되었습니다.");
        oDialog.setCancelable(false);
        oDialog.show();

      /*  //Dialog에서 보여줄 입력화면 View 객체 생성 작업
        //Layout xml 리소스 파일을 View 객체로 부불려 주는(inflate) LayoutInflater 객체 생성
        LayoutInflater inflater = getLayoutInflater();

        //res폴더>>layout폴더>>dialog_addmember.xml 레이아웃 리소스 파일로 View 객체 생성
        //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언
        final View dialogView = inflater.inflate(R.layout.tab4_dialog, null);

        //멤버의 세부내역 입력 Dialog 생성 및 보이기
        AlertDialog.Builder buider = new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
        buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅 (Customize)

        buider.setMessage("문의가 성공적으로 완료되었습니다.").setCancelable(false).setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                clean();

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

        AlertDialog dialog = buider.create();

        //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
        dialog.setCanceledOnTouchOutside(false);//없어지지 않도록 설정

        dialog.show();*/

    }//dialog
}//Fragment_Email
