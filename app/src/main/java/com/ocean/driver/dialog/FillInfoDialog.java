package com.ocean.driver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.activity.PersonalDataActivity;

/**
 * 绑定新手机成功
 */
public class FillInfoDialog extends Dialog {


    TextView tvSure;
    private Context context;

    public FillInfoDialog(Context context) {
        super(context);
    }

    public FillInfoDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fill_status);
        initView();
    }

    private void initView() {
        tvSure=findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_sure:
                    PersonalDataActivity.actionStart(context);
                    dismiss();
                    break;
            }
        }
    };

}
