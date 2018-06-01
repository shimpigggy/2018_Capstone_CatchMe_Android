package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;
import org.w3c.dom.Text;

/*
 * Created by sj-kalin on 2018-05-07.
 * Modified by sj-kalin on 2018-05-22.
 * Modified by ShimPiggy on 2018-05-23. - receive info from intent
 */

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    RelativeLayout[] contentsViews;
    RelativeLayout[] contentsViewResult;
    ImageView[] contentsImages;

    RelativeLayout typeView;
    RelativeLayout typeViewResult;
    ImageView typeImage;
    public static ChemicalDTO chemicalDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_search_result);

        dataMaching();

        intent = getIntent();
        Toast.makeText(this, chemicalDTO.toString(), Toast.LENGTH_SHORT).show();
        Log.i("gg", chemicalDTO.toString());

        typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int visible = typeViewResult.getVisibility();
                if (visible == View.VISIBLE) {
                    typeViewResult.setVisibility(View.GONE);
                    typeImage.setImageResource(R.drawable.arrow_down);
                } else {
                    typeViewResult.setVisibility(View.VISIBLE);
                    typeImage.setImageResource(R.drawable.arrow_up);
                }
            }
        });
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void dataMaching() {
        intent = getIntent();

        typeView = (RelativeLayout) findViewById(R.id.used);
        typeViewResult = (RelativeLayout) findViewById(R.id.usedresult);
        typeImage = (ImageView) findViewById(R.id.used_image);

        chemicalSimpleDescription();
        chemicalUsed();//용도
        chemicalType();//피부 타입
        chemicalFuncionFor();//기능성
        chemicalAllergy();//알러지
        chemicalUseLimit();//사용상의 제한
        chemicalProduct();//제품 리스트
    }

    public void relativeLayoutSetting() {

    }

    public void chemicalSimpleDescription() {
        TextView nameK = (TextView) findViewById(R.id.nameK);
        nameK.setText(chemicalDTO.getNameK());

        TextView nameE = (TextView) findViewById(R.id.nameE);
        nameE.setText(chemicalDTO.getNameE());

        TextView cas = (TextView) findViewById(R.id.cas);
        cas.setText(chemicalDTO.getCas());

        TextView definition = (TextView) findViewById(R.id.definition);
        definition.setText(chemicalDTO.getDefinition());
    }//chemicalSimpleDescription

    public void chemicalUsed() {//용도
        String used = chemicalDTO.getUsed();

        int[] tvIdResult = {R.id.used_result_1, R.id.used_result_2,
                R.id.used_result_3, R.id.used_result_4,
                R.id.used_result_5, R.id.used_result_6,
                R.id.used_result_7, R.id.used_result_8,
                R.id.used_result_9, R.id.used_result_10};
        TextView[] textViews = new TextView[tvIdResult.length];

        for (int k = 0; k < tvIdResult.length; k++) {
            textViews[k] = (TextView) findViewById(tvIdResult[k]);
        }

        if (used == null || used.equals("")) {
            TextView textView = (TextView) findViewById(R.id.used_result_1);
            textView.setText("해당사항 없음");

            for (int i = 1; i < tvIdResult.length; i++) {
                textViews[i] = (TextView) findViewById(tvIdResult[i]);
                textViews[i].setVisibility(View.GONE);
            }
        } else {
            //문자 나누기
            String[] result = null;
            result = used.split("/");
            String[] resultContext0 = null;
            String[] resultContext1 = null;
            String[] resultContext2 = null;
            String[] resultContext3 = null;
            String[] resultContext4 = null;

            int i;
            for (i = 0; i < result.length; i++) {
                switch (i) {
                    case 0:
                        if (result[0] != null) {
                            resultContext0 = result[0].split(":");

                            textViews[0].setText(resultContext0[0]);
                            textViews[1].setText(resultContext0[1]);
                        } /*else {
                            textViews[0].setVisibility(View.GONE);
                            textViews[1].setVisibility(View.GONE);
                        }*/
                        break;
                    case 1:
                        if (result[1] != null) {
                            resultContext1 = result[1].split(":");

                            textViews[2].setText(resultContext1[0]);
                            textViews[3].setText(resultContext1[1]);
                        } /*else {
                            textViews[2].setVisibility(View.GONE);
                            textViews[3].setVisibility(View.GONE);
                        }*/
                        break;
                    case 2:
                        if (result[2] != null) {
                            resultContext2 = result[2].split(":");

                            textViews[4].setText(resultContext2[0]);
                            textViews[5].setText(resultContext2[1]);
                        } else {
                            textViews[4].setVisibility(View.GONE);
                            textViews[5].setVisibility(View.GONE);
                        }
                        break;
                    case 3:
                        if (result[3] != null) {
                            resultContext3 = result[3].split(":");

                            textViews[6].setText(resultContext3[0]);
                            textViews[7].setText(resultContext3[1]);
                        } else {
                            textViews[6].setVisibility(View.GONE);
                            textViews[7].setVisibility(View.GONE);
                        }
                        break;

                    case 4:
                        if (result[4] != null) {
                            resultContext4 = result[4].split(":");

                            textViews[8].setText(resultContext4[0]);
                            textViews[9].setText(resultContext4[1]);
                        } else {
                            textViews[8].setVisibility(View.GONE);
                            textViews[9].setVisibility(View.GONE);
                        }
                        break;
                }//switch
            }//for

            for (i = i + 1; i < tvIdResult.length; i++) {
                textViews[i].setVisibility(View.GONE);
            }

        }//else
    }//chemicalUsed

    public void chemicalType() {//피부타입
        int[] tvIdResultContext = {R.id.dry_good_context, R.id.dry_bad_context,
                R.id.oil_good_context, R.id.oil_bad_context,
                R.id.sensitive_good_context, R.id.sensitive_bad_context, R.id.complex_bad_context};

        TextView[] context = new TextView[tvIdResultContext.length];
        for (int i = 0; i < tvIdResultContext.length; i++)
            context[i] = (TextView) findViewById(tvIdResultContext[i]);

        int[] tvIdResult = {R.id.dry_good, R.id.dry_bad,
                R.id.oil_good, R.id.oil_bad,
                R.id.sensitive_good, R.id.sensitive_bad, R.id.complex_bad};

        TextView[] title = new TextView[tvIdResult.length];
        for (int i = 0; i < tvIdResult.length; i++)
            title[i] = (TextView) findViewById(tvIdResult[i]);

        String dryGood = chemicalDTO.getDryGood();
        if (dryGood == null || dryGood.equals("")) {
            title[0].setVisibility(View.GONE);
            context[0].setVisibility(View.GONE);
        } else {
            context[0].setText(dryGood);
        }

        String dryBad = chemicalDTO.getDryBad();
        if (dryBad == null || dryBad.equals("")) {
            title[1].setVisibility(View.GONE);
            context[1].setVisibility(View.GONE);
        } else {
            context[1].setText(dryBad);
        }

        String oilGood = chemicalDTO.getOilGood();
        if (oilGood == null || oilGood.equals("")) {
            title[2].setVisibility(View.GONE);
            context[2].setVisibility(View.GONE);
        } else {
            context[2].setText(oilGood);
        }

        String oilBad = chemicalDTO.getOilBad();
        if (oilBad == null || oilBad.equals("")) {
            title[3].setVisibility(View.GONE);
            context[3].setVisibility(View.GONE);
        } else {
            context[3].setText(oilBad);
        }

        String sensitiveGood = chemicalDTO.getSensitiveGood();
        if (sensitiveGood == null || sensitiveGood.equals("")) {
            title[4].setVisibility(View.GONE);
            context[4].setVisibility(View.GONE);
        } else {
            context[4].setText(sensitiveGood);
        }

        String sensitiveBad = chemicalDTO.getSensitiveBad();
        if (sensitiveBad == null || sensitiveBad.equals("")) {
            title[5].setVisibility(View.GONE);
            context[5].setVisibility(View.GONE);
        } else {
            context[5].setText(sensitiveBad);
        }

        String complexBad = chemicalDTO.getComplexBad();
        if (complexBad == null || complexBad.equals("")) {
            title[6].setVisibility(View.GONE);
            context[6].setVisibility(View.GONE);
        } else {
            context[6].setText(complexBad);
        }
    }//chemicalType

    public void chemicalFuncionFor() {//기능성
        String functionFor = chemicalDTO.getFunctionFor();

        if (functionFor == null || functionFor.equals("")) {
            TextView tv1 = (TextView) findViewById(R.id.functionfor_result_1);
            tv1.setText("해당사항 없음");

            TextView tv2 = (TextView) findViewById(R.id.functionfor_result_2);
            tv2.setVisibility(View.GONE);
        } else {
            String[] split = functionFor.split(":");

            TextView tv1 = (TextView) findViewById(R.id.functionfor_result_1);
            tv1.setText(split[0]);

            TextView tv2 = (TextView) findViewById(R.id.functionfor_result_2);
            tv2.setText(split[1]);
        }
    }//chemicalFuncionFor

    public void chemicalAllergy() {//알러지
        String allergy = chemicalDTO.getAllergy();

        int[] tvIdResultContext = {R.id.allergy_result_1, R.id.allergy_result_2,
                R.id.allergy_result_3, R.id.allergy_result_4};

        TextView[] context = new TextView[tvIdResultContext.length];
        for (int i = 0; i < tvIdResultContext.length; i++)
            context[i] = (TextView) findViewById(tvIdResultContext[i]);

        if (allergy == null || allergy.equals("")) {
            context[1].setText("해당사항 없음");
            context[3].setText("해당사항 없음");
        } else {
            String[] country = allergy.split("/");
            String[] japan = null;
            String[] korea = null;

            for (int i = 0; i < country.length; i++) {
                if (country[i].contains("일본")) {
                    japan = country[i].split("-");
                } else {
                    korea = country[i].split("-");
                }
            }

            if (japan == null) {
                context[1].setText("해당사항 없음");
            }
            if (korea == null) {
                context[3].setText("해당사항 없음");
            }
        }
    } //chemicalAllergy

    public void chemicalUseLimit() {//사용상의 제한
        String warning = chemicalDTO.getWarning();

        int[] tvIdResult = {R.id.warning, R.id.warning_result,
                R.id.acne, R.id.acne_result};
        TextView[] textViews = new TextView[tvIdResult.length];

        for(int i =0;i<tvIdResult.length;i++){
            textViews[i] =(TextView) findViewById(tvIdResult[i]);
        }

        if(warning == null || warning.equals("")){
            textViews[0].setText("해당 사항 없음");
            textViews[1].setVisibility(View.GONE);
        }else{
            String[] result = warning.split(":");
            String[] resultContext = result[1].split("/");

            textViews[0].setText(result[0]);

            String context = null;
            for(int i=0;i<resultContext.length;i++){
                context += resultContext[i] +"\n";
            }
            textViews[1].setText(context);
        }

        String acne = chemicalDTO.getAcne();

        if(acne == null || acne.equals("")){
            textViews[3].setText("해당 사항 없음");
        }else{

            
        }

/*"allergy":"일본 NITE 분류-알레르기 피부 반응을 일으킬 수 있는 성분/식약처 고시-알레르기 유발 가능성이 있는 성분"
,"warning":"화장품 안전기준 등에 관한 규정 : 사용상의 제한이 필요한 원료(자외선차단제)/5%까지 함유 허용"
,"acne":"피부에 자극을 일으킬 수 있는 성분(일본 NITE 분류)/피부에 자극을 줄 수 있는 성분(미국 IRIS 평가)"
* */
    }//chemicalUseLimit

    public void chemicalProduct() {//제품 리스트
        String product = chemicalDTO.getProductList();

        int[] tvIdResult = {R.id.product_result_1, R.id.product_result_2, R.id.product_result_3,
                R.id.product_result_4, R.id.product_result_5};
        TextView[] textViews = new TextView[tvIdResult.length];

        if (product == null || product.equals("")) {
            TextView textView = (TextView) findViewById(R.id.product_result_1);
            textView.setText("해당사항 없음");

            for (int i = 1; i < tvIdResult.length; i++) {
                textViews[i] = (TextView) findViewById(tvIdResult[i]);
                textViews[i].setVisibility(View.GONE);
            }
        } else {
            String[] result = product.split("/");

            int i;
            for (i = 0; i < result.length; i++) {
                textViews[i] = (TextView) findViewById(tvIdResult[i]);
                textViews[i].setText(result[i]);
            }
            for (; i < tvIdResult.length; i++) {
                textViews[i] = (TextView) findViewById(tvIdResult[i]);
                textViews[i].setVisibility(View.GONE);
            }
        }
    }//chemicalProduct

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.allergy || v.getId() == R.id.functionfor || v.getId() == R.id.product) {
            int visible = v.getVisibility();
            if (visible == View.VISIBLE) {
                v.setVisibility(View.GONE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }//onClick
}//SearchResult