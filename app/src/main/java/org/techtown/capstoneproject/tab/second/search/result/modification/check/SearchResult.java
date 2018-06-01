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

    RelativeLayout typeView;
    RelativeLayout typeViewResult;
    ImageView typeImage;
    public static ChemicalDTO chemicalDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_search_result);

        init();

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

    public void init() {
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

        int[] tvIdResult = {R.id.used_result_1, R.id.used_result_2, R.id.used_result_3, R.id.used_result_4, R.id.used_result_5, R.id.used_result_6, R.id.used_result_7, R.id.used_result_8, R.id.used_result_9, R.id.used_result_10};
        TextView[] textViews = new TextView[tvIdResult.length];

        if (used == null) {
            TextView textView = (TextView) findViewById(R.id.used_result_1);
            textView.setText("해당사항 없음");

            for (int i = 1; i < tvIdResult.length; i++) {
                textViews[i] = (TextView) findViewById(tvIdResult[i]);
                textViews[i].setVisibility(View.GONE);
            }
        } else {
            String[] result = used.split(" : ");

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
        if (dryBad == null) {
            title[1].setVisibility(View.GONE);
            context[1].setVisibility(View.GONE);
        } else {
            context[1].setText(dryBad);
        }

        String oilGood = chemicalDTO.getOilGood();
        if (oilGood == null) {
            title[2].setVisibility(View.GONE);
            context[2].setVisibility(View.GONE);
        } else {
            context[2].setText(oilGood);
        }

        String oilBad = chemicalDTO.getOilBad();
        if (oilBad == null) {
            title[3].setVisibility(View.GONE);
            context[3].setVisibility(View.GONE);
        } else {
            context[3].setText(oilBad);
        }

        String sensitiveGood = chemicalDTO.getSensitiveGood();
        if (sensitiveGood == null) {
            title[4].setVisibility(View.GONE);
            context[4].setVisibility(View.GONE);
        } else {
            context[4].setText(sensitiveGood);
        }

        String sensitiveBad = chemicalDTO.getSensitiveBad();
        if (sensitiveBad == null) {
            title[5].setVisibility(View.GONE);
            context[5].setVisibility(View.GONE);
        } else {
            context[5].setText(sensitiveBad);
        }

        String complexBad = chemicalDTO.getComplexBad();
        if (complexBad == null) {
            title[6].setVisibility(View.GONE);
            context[6].setVisibility(View.GONE);
        } else {
            context[6].setText(complexBad);
        }
    }//chemicalType

    public void chemicalFuncionFor() {//기능성
        String functionFor = chemicalDTO.getFunctionFor();

        if (functionFor == null) {
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

    } //chemicalAllergy

    public void chemicalUseLimit() {//사용상의 제한

    }//chemicalUseLimit

    public void chemicalProduct() {//제품 리스트
        String product = chemicalDTO.getProductList();

        int[] tvIdResult = {R.id.product_result_1, R.id.product_result_2, R.id.product_result_3,
                R.id.product_result_4, R.id.product_result_5, R.id.product_result_6,
                R.id.product_result_7, R.id.product_result_8, R.id.product_result_9};
        TextView[] textViews = new TextView[tvIdResult.length];

        if (product == null) {
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
}//SearchResult