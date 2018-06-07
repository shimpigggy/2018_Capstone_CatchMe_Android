package org.techtown.capstoneproject.tab.second.search.result.modification.check;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.SharedPreferencesUtil;
import org.techtown.capstoneproject.service.dto.ChemicalDTO;

/*
 * Created by sj-kalin on 2018-05-07.
 * Modified by sj-kalin on 2018-05-22.
 * Modified by ShimPiggy on 2018-05-23. - receive info from intent
 */

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    RelativeLayout[] contentsTabs;
    RelativeLayout[] contentsViewResult;
    ImageView[] contentsImages;

    public static ChemicalDTO chemicalDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_search_result);

        chemicalDTO = (ChemicalDTO) getIntent().getSerializableExtra("data");

        dataMaching();

        Log.e("result", chemicalDTO.toString());
    }

    public void actionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }//actionBar

    public void dataMaching() {
        intent = getIntent();

        relativeLayoutSetting();

        chemicalSimpleDescription();
        chemicalUsed();//용도
        chemicalType();//피부 타입
        chemicalFuncionFor();//기능성
        chemicalAllergy();//알러지
        chemicalUseLimit();//사용상의 제한
        chemicalProduct();//제품 리스트
    }//dataMaching

    public void relativeLayoutSetting() {
        int[] tvIDTab = {R.id.used, R.id.type, R.id.functionfor, R.id.allergy, R.id.use_limit, R.id.product};
        int[] tvIDResult = {R.id.usedresult, R.id.typeresult, R.id.functionfor_result,
                R.id.allergy_result, R.id.use_limit_result, R.id.product_result};
        int[] imageID = {R.id.used_image, R.id.type_image, R.id.functionfor_image, R.id.allergy_image,
                R.id.use_limit_image, R.id.product_image};

        contentsTabs = new RelativeLayout[tvIDTab.length];
        for (int i = 0; i < tvIDTab.length; i++) {
            contentsTabs[i] = (RelativeLayout) findViewById(tvIDTab[i]);
            contentsTabs[i].setOnClickListener(this);
        }

        contentsViewResult = new RelativeLayout[tvIDResult.length];
        for (int i = 0; i < tvIDResult.length; i++) {
            contentsViewResult[i] = (RelativeLayout) findViewById(tvIDResult[i]);
            contentsViewResult[i].setVisibility(View.GONE);
        }

        contentsImages = new ImageView[imageID.length];
        for (int i = 0; i < imageID.length; i++) {
            contentsImages[i] = (ImageView) findViewById(imageID[i]);
        }
    }//relativeLayoutSetting

    public void chemicalSimpleDescription() {
        String nameK = chemicalDTO.getNameK();
        String nameE = chemicalDTO.getNameE();
        String cas = chemicalDTO.getCas();
        String definition = chemicalDTO.getDefinition();

        TextView tvNameK = (TextView) findViewById(R.id.nameK);
        if (nameK == null || nameK.equals(""))
            tvNameK.setText("NULL");
        else {
            tvNameK.setText(nameK);
        }

        TextView tvNameE = (TextView) findViewById(R.id.nameE);
        if (nameE == null || nameE.equals(""))
            tvNameE.setText("NULL");
        else {
            tvNameE.setText(nameE);
        }

        TextView tvCas = (TextView) findViewById(R.id.cas);
        if (cas == null || cas.equals(""))
            tvCas.setText("NULL");
        else {
            tvCas.setText(cas);
        }

        TextView tvDefinition = (TextView) findViewById(R.id.definition);
        if (definition == null || definition.equals(""))
            tvDefinition.setText("NULL");
        else {
            tvDefinition.setText(definition);
        }

    }//chemicalSimpleDescription

    public void chemicalUsed() {//용도
        String used = chemicalDTO.getUsed();

        Log.e("USED", used);

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
            String[] result = used.split("/");
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
                            Log.e("result_", result[0]);
                            resultContext0 = result[0].split(":");

                            textViews[0].setText(resultContext0[0]);
                            if (resultContext0[0].equals("향료"))
                                textViews[1].setText("향을 일으키는 물질");
                            else
                                textViews[1].setText(resultContext0[1]);
                        }
                        break;
                    case 1:
                        if (result[1] != null) {

                            Log.e("result_", result[1]);
                            resultContext1 = result[1].split(":");

                            textViews[2].setText(resultContext1[0]);
                            if (resultContext1[0].equals("향료"))
                                textViews[3].setText("향을 일으키는 물질");
                            else
                                textViews[3].setText(resultContext1[1]);
                        }
                        break;
                    case 2:
                        if (result[2] != null) {

                            Log.e("result_", result[2]);
                            resultContext2 = result[2].split(":");

                            textViews[4].setText(resultContext2[0]);
                            if (resultContext2[0].equals("향료"))
                                textViews[5].setText("향을 일으키는 물질");
                            else
                                textViews[5].setText(resultContext2[1]);
                        }
                        break;
                    case 3:
                        if (result[3] != null) {

                            Log.e("result_", result[3]);
                            resultContext3 = result[3].split(":");

                            textViews[6].setText(resultContext3[0]);
                            if (resultContext3[0].equals("향료"))
                                textViews[7].setText("향을 일으키는 물질");
                            else
                                textViews[7].setText(resultContext3[1]);
                        }
                        break;

                    case 4:
                        if (result[4] != null) {

                            Log.e("result_", result[4]);
                            resultContext4 = result[4].split(":");

                            textViews[8].setText(resultContext4[0]);
                            if (resultContext4[0].equals("향료"))
                                textViews[9].setText("향을 일으키는 물질");
                            else
                                textViews[9].setText(resultContext4[1]);
                        }
                        break;
                }//switch
            }//for

            Log.e("i >> ", i + "");

            for (; i < 5; i++) {
                textViews[i * 2].setVisibility(View.GONE);
                textViews[i * 2 + 1].setVisibility(View.GONE);
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
            if (SharedPreferencesUtil.getDryPreferences(this)) {
                context[0].setTextColor(Color.RED);
            }
        }

        String dryBad = chemicalDTO.getDryBad();
        if (dryBad == null || dryBad.equals("")) {
            title[1].setVisibility(View.GONE);
            context[1].setVisibility(View.GONE);
        } else {
            context[1].setText(dryBad);
            if (SharedPreferencesUtil.getDryPreferences(this)) {
                context[1].setTextColor(Color.RED);
            }
        }

        String oilGood = chemicalDTO.getOilGood();
        if (oilGood == null || oilGood.equals("")) {
            title[2].setVisibility(View.GONE);
            context[2].setVisibility(View.GONE);
        } else {
            context[2].setText(oilGood);
            if (SharedPreferencesUtil.getOilPreferences(this)) {
                context[2].setTextColor(Color.RED);
            }
        }

        String oilBad = chemicalDTO.getOilBad();
        if (oilBad == null || oilBad.equals("")) {
            title[3].setVisibility(View.GONE);
            context[3].setVisibility(View.GONE);
        } else {
            context[3].setText(oilBad);
            if (SharedPreferencesUtil.getOilPreferences(this)) {
                context[3].setTextColor(Color.RED);
            }
        }

        String sensitiveGood = chemicalDTO.getSensitiveGood();
        if (sensitiveGood == null || sensitiveGood.equals("")) {
            title[4].setVisibility(View.GONE);
            context[4].setVisibility(View.GONE);
        } else {
            context[4].setText(sensitiveGood);
            if (SharedPreferencesUtil.getSensitivePreferences(this)) {
                context[4].setTextColor(Color.RED);
            }
        }

        String sensitiveBad = chemicalDTO.getSensitiveBad();
        if (sensitiveBad == null || sensitiveBad.equals("")) {
            title[5].setVisibility(View.GONE);
            context[5].setVisibility(View.GONE);
        } else {
            context[5].setText(sensitiveBad);
            if (SharedPreferencesUtil.getSensitivePreferences(this)) {
                context[5].setTextColor(Color.RED);
            }
        }

        String complexBad = chemicalDTO.getComplexBad();
        if (complexBad == null || complexBad.equals("")) {
            title[6].setVisibility(View.GONE);
            context[6].setVisibility(View.GONE);
        } else {
            context[6].setText(complexBad);
            if (SharedPreferencesUtil.getComplexPreferences(this)) {
                context[6].setTextColor(Color.RED);
            }
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

            if (split.length > 1) {
                TextView tv2 = (TextView) findViewById(R.id.functionfor_result_2);
                tv2.setText(split[1]);
            }else{
                TextView tv2 = (TextView) findViewById(R.id.functionfor_result_2);
                tv2.setVisibility(View.GONE);
            }
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
            } else {
                if (SharedPreferencesUtil.getAllergyPreferences(this))
                    context[1].setTextColor(Color.RED);
            }
            if (korea == null) {
                context[3].setText("해당사항 없음");
            } else {
                if (SharedPreferencesUtil.getAllergyPreferences(this))
                    context[3].setTextColor(Color.RED);
            }
        }
    } //chemicalAllergy

    public void chemicalUseLimit() {//사용상의 제한
        String warning = chemicalDTO.getWarning();

        int[] tvIdResult = {R.id.warning, R.id.warning_result,
                R.id.acne, R.id.acne_result};
        TextView[] textViews = new TextView[tvIdResult.length];

        for (int i = 0; i < tvIdResult.length; i++) {
            textViews[i] = (TextView) findViewById(tvIdResult[i]);
        }

        if (warning == null || warning.equals("")) {
            textViews[0].setText("해당 사항 없음");
            textViews[1].setVisibility(View.GONE);
        } else {
            String[] warningResult = warning.split(":");
            if (warningResult.length > 1) {
                String[] warningResultContext = warningResult[1].split("/");

                textViews[0].setText(warningResult[0]);

                String warningContext = "";
                for (int i = 0; i < warningResultContext.length; i++) {
                    warningContext += warningResultContext[i] + "\n";
                }
                textViews[1].setText(warningContext);
            }
        }

        String acne = chemicalDTO.getAcne();

        if (acne == null || acne.equals("")) {
            textViews[3].setText("해당 사항 없음");
        } else {
            String[] acneResult = acne.split("/");

            String acneContext = "";
            for (int i = 0; i < acneResult.length; i++) {
                acneContext += acneResult[i] + "\n";
            }
            textViews[3].setText(acneContext);
            if (SharedPreferencesUtil.getAcnePreferences(this)) {
                textViews[3].setTextColor(Color.RED);
            }
        }
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
        switch (v.getId()) {
            case R.id.used:
                viewSettingVisibility(contentsViewResult[0], contentsImages[0]);
                break;
            case R.id.type:
                viewSettingVisibility(contentsViewResult[1], contentsImages[1]);
                break;
            case R.id.functionfor:
                viewSettingVisibility(contentsViewResult[2], contentsImages[2]);
                break;
            case R.id.allergy:
                viewSettingVisibility(contentsViewResult[3], contentsImages[3]);
                break;
            case R.id.use_limit:
                viewSettingVisibility(contentsViewResult[4], contentsImages[4]);
                break;
            case R.id.product:
                viewSettingVisibility(contentsViewResult[5], contentsImages[5]);
                break;
        }
    }//onClick

    public void viewSettingVisibility(RelativeLayout result, ImageView image) {
        int visible = result.getVisibility();

        if (visible == View.VISIBLE) {
            result.setVisibility(View.GONE);
            image.setImageResource(R.drawable.arrow_down);
        } else {
            result.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.arrow_up);
        }
    }//viewSettingVisibility
}//SearchResult