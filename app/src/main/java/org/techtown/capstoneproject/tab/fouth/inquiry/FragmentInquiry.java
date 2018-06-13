package org.techtown.capstoneproject.tab.fouth.inquiry;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-13. - modify view
 * Modified by ShimPiggy on 2018-05-20. - tab_inquiry form check, blank check
 * Modified by ShimPiggy on 2018-05-21. - Server
 * Modified by ShimPiggy on 2018-05-23. - image
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.SharedPreferencesUtil;
import org.techtown.capstoneproject.service.api.ApiService;
import org.techtown.capstoneproject.service.api.ApiServiceEmail;
import org.techtown.capstoneproject.service.dto.InquiryDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentInquiry extends Fragment {
    Spinner spinner;
    ImageButton buttonSend;
    TextView textEmail;
    EditText editTextTitle;
    EditText editTextContext;

    private final int SUCCESS = 1;
    private final int TITLE_X = 2;
    private final int SPINNER_X = 3;
    private final int CONTEXT_X = 4;

    //server
    Retrofit retrofit;
    ApiServiceEmail apiService;

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

        buttonSend.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //   dialog(SUCCESS);
                inquiryCheck();
            }//onClick
        });//setOnClickListener

        return view;
    }//onCreateView

    public void init(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        buttonSend = (ImageButton) view.findViewById(R.id.send);
        textEmail = (TextView) view.findViewById(R.id.email);
        editTextContext = (EditText) view.findViewById(R.id.content);
        editTextTitle = (EditText) view.findViewById(R.id.title_content);

        //email setting
        String email = SharedPreferencesUtil.getEmailPreferences(getContext());
        textEmail.setText(email);

        //server
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.ADDRESS).build();

        apiService = retrofit.create(ApiServiceEmail.class);
    }//init

    public void inquiryCheck() {
        //tab_inquiry form check
        if (spinner.getSelectedItemPosition() != 0)
            if (!editTextTitle.getText().toString().equals(""))
                if (!editTextContext.getText().toString().equals("")) {
                    // 서버에게 문의에 대한 값을 넘김
                    //  sendServeryInquiry();
                    dialog(SUCCESS);//임시
                } else {//context X
                    dialog(CONTEXT_X);
                }
            else {//title X
                dialog(TITLE_X);
            }
        else {//spinner X
            dialog(SPINNER_X);
        }
    }//inquiryCheck

    public void dialog(int distinction) {
        String context = "";
        switch (distinction) {
            case SUCCESS:
                context = "문의가 완료되었습니다.";
                clean();
                break;
            case TITLE_X:
                context = "문의에 대한 제목이 없습니다.";
                settingFocus(editTextTitle);
                break;
            case SPINNER_X:
                context = "문의에 대한 분류를 선택하지 않았습니다.";
                break;
            case CONTEXT_X:
                context = "문의에 대한 내용이 없습니다,";
                settingFocus(editTextContext);
                break;
        }
        CustomDialog oDialog = new CustomDialog(getContext(), context);
        oDialog.setCancelable(false);
        oDialog.show();
    }//dialog

    public void clean() {
        editTextTitle.setText("");
        editTextContext.setText("");
        spinner.setSelection(0);
    }//clean

    public void settingFocus(EditText editText) {
        editText.requestFocus();
    }

    public void sendServeryInquiry() {
        InquiryDTO dto = new InquiryDTO();

        dto.setContent(editTextContext.getText().toString());
        dto.setEmail(textEmail.getText().toString());
        dto.setTitle(editTextTitle.getText().toString());
        dto.setType(spinner.getSelectedItem().toString());

        Call<JSONObject> sample2 = apiService.getPostCommentStr(dto);

        sample2.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                //데이터가 받아지면 호출
                try {
                    String result = response.code() + "";
                    Log.e("Inquiry", result);
                    dialog(SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//onResponse

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                //데이터가 받아지는 것이 실패
                Log.e("Inquiry Fail", call.toString());
            }//onFailure
        });
    }//sendServeryInquiry
}//Fragment_Email
