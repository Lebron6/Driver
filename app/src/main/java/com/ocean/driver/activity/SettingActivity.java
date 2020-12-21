package com.ocean.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.dialog.VerifyPasswordDialog;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.SettingResult;
import com.ocean.driver.tools.AppManager;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.TitleManger;
import com.ocean.driver.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/4.
 * 设置
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_user_info)
    RelativeLayout layoutUserInfo;
    @BindView(R.id.txt_updata_password)
    TextView txtUpdataPassword;
    @BindView(R.id.tv_updata_password)
    TextView tvUpdataPassword;
    @BindView(R.id.iv_password_set)
    ImageView ivPasswordSet;
    @BindView(R.id.layout_password_updata)
    RelativeLayout layoutPasswordUpdata;
    @BindView(R.id.txt_wx_bind)
    TextView txtWxBind;
    @BindView(R.id.tv_wx_nickname)
    TextView tvWxNickname;
    @BindView(R.id.iv_wx_bind)
    ImageView ivWxBind;
    @BindView(R.id.layout_bind_wx)
    RelativeLayout layoutBindWx;
    @BindView(R.id.txt_phone_bind)
    TextView txtPhoneBind;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.iv_phone_bind)
    ImageView ivPhoneBind;
    @BindView(R.id.layout_phone_bind)
    RelativeLayout layoutPhoneBind;
    @BindView(R.id.btn_exit_login)
    Button btnExitLogin;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("设置");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
    }

    boolean hasPassword;
    @Override
    protected void initViews() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().settingInfo()).settinInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<SettingResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingResult>> call, Response<ApiResponse<SettingResult>> response) {
                if (response.body().getCode() == 1) {
                    if (!response.body().getData().isHas_password()){
                        tvUpdataPassword.setText("未设置");
                        hasPassword=false;
                    }else{
                        tvUpdataPassword.setText("已设置");
                        hasPassword=true;
                    }
                    tvPhoneNum.setText(response.body().getData().getPhone()+"");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取设置数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_user_info,R.id.layout_password_updata, R.id.layout_bind_wx, R.id.layout_phone_bind,  R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_user_info:
                PersonalDataActivity.actionStart(this);
                break;
            case R.id.layout_bind_wx:
                break;
            case R.id.layout_password_updata:
                if (hasPassword==false){
                    SettingNewPasswordActivity.actionStart(SettingActivity.this);
                }else{
                    VerifyPasswordDialog dialog = new VerifyPasswordDialog(this, R.style.MyDialog,0);
                    dialog.show();
                }

                break;
            case R.id.layout_phone_bind:
                VerifyPasswordDialog dialog1 = new VerifyPasswordDialog(this, R.style.MyDialog,1,tvPhoneNum.getText().toString());
                dialog1.show();
                break;
            case R.id.btn_exit_login:
                PreferenceUtils.getInstance().loginOut();
                AppManager.getAppManager().AppExit(SettingActivity.this);
               PasswordLoginActivity.actionStart(this);
                break;
        }
    }
}
