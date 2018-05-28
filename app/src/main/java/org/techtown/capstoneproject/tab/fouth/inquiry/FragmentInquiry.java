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
import android.text.Editable;
import android.text.Selection;
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

    private final int SUCCESS = 1;
    private final int TITLE_X = 2;
    private final int EMAIL_X = 3;
    private final int SPINNER_X = 4;
    private final int CONTEXT_X = 5;

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
                //   dialog(SUCCESS);

                //tab_inquiry form check
                String tab_inquiry = editText_email.getText() + "";

                if (checkEmailForm(tab_inquiry))
                    if (spinner.getSelectedItemPosition() != 0)
                        if (!editText_title.getText().toString().equals(""))
                            if (!editText_context.getText().toString().equals("")) {
                                // sendServeryInquiry();
                                dialog(SUCCESS);
                            } else {//context X
                                dialog(CONTEXT_X);
                                //  Toast.makeText(getActivity().getApplicationContext(), "내용이 없음", Toast.LENGTH_SHORT).show();
                            }
                        else {//title X
                            dialog(TITLE_X);
                            // Toast.makeText(getActivity().getApplicationContext(), "제목을 적지 않음", Toast.LENGTH_SHORT).show();
                        }
                    else {//spinner X
                        dialog(SPINNER_X);
                        // Toast.makeText(getActivity().getApplicationContext(), "분류를 고르지 않음", Toast.LENGTH_SHORT).show();
                    }
                else {//tab_inquiry check
                    dialog(EMAIL_X);
                    //Toast.makeText(getActivity().getApplicationContext(), "이메일 형식 안맞음", Toast.LENGTH_SHORT).show();
                }
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

    //tab_inquiry 체크 함수
    public boolean checkEmailForm(String src) {
        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        return Pattern.matches(emailRegex, src);
    }//checkEmailForm

    public void dialog(int distinction) {

        String context="";
        switch (distinction) {
            case SUCCESS:
                context = "문의가 완료되었습니다.";
                clean();
                break;
            case TITLE_X:
                context = "문의에 대한 제목이 없습니다.";

                break;
            case EMAIL_X:
                context = "이메일 형식에 맞지 않습니다.\n다시 확인 바랍니다.";
                Editable set = editText_email.getText();
                Selection.setSelection(set,editText_email.length() );
                break;
            case SPINNER_X:
                context = "문의에 대한 분류를 선택하지 않았습니다.";
                break;
            case CONTEXT_X:
                context = "문의에 대한 내용이 없습니다,";
                break;
        }
        /*
            private final int SUCCESS = 1;
    private final int TITLE_X = 2;
    private final int EMAIL_X = 3;
    private final int SPINNER_X = 4;
    private final int CONTEXT_X = 5;
        * */


        CustomDialog oDialog = new CustomDialog(getContext(), context);
        oDialog.setCancelable(false);
        oDialog.show();
    }//dialog

    public void clean() {
        editText_email.setText("");
        editText_title.setText("");
        editText_context.setText("");
        spinner.setSelection(0);
    }//clean

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
                    dialog(SUCCESS);
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
}//Fragment_Email
