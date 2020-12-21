package com.ocean.driver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.activity.PersonalDataActivity;

import butterknife.BindView;

/**
 * 驳回内容
 */
public class RejectContentDialog extends Dialog {


    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_close)
    TextView tvClose;
    private Context context;
    String content;String time;
    public RejectContentDialog(Context context) {
        super(context);
    }

    public RejectContentDialog(Context context, int theme,String content,String time) {
        super(context, theme);
        this.context = context;
        this.content = content;
        this.time = time;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reject_content);
        initView();
    }

    private void initView() {
        tvTime = findViewById(R.id.tv_sure);
        tvContent = findViewById(R.id.tv_content);
        tvClose = findViewById(R.id.tv_close);
        tvClose.setOnClickListener(onClickListener);
        tvTime.setText(time);
        tvContent.setText(content);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_close:
                    dismiss();
                    break;
            }
        }
    };

}
