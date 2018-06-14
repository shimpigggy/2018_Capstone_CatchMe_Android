package org.techtown.capstoneproject.tab.fouth.inquiry;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import org.techtown.capstoneproject.R;

public class CustomDialog extends Dialog {
    CustomDialog m_oDialog;
    String text;

    public CustomDialog(Context context, String text)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.9f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_custom_dialog);

        m_oDialog = this;

        TextView view = (TextView) this.findViewById(R.id.text);
        view.setText(text);

        ImageButton btnOK = (ImageButton)this.findViewById(R.id.check);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onClickBtn(v);
            }
        });
    }

    public void onClickBtn(View v){
        this.dismiss();
    }
}
