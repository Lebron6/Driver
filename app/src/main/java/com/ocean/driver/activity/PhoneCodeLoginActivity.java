package com.ocean.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.LoginResult;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.tools.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/3.
 * 手机验证码登录
 */
public class PhoneCodeLoginActivity extends BaseActivity {
    @BindView(R.id.tv_to_password_login)
    TextView tvToPasswordLogin;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phone_code)
    EditText etPhoneCode;
    @BindView(R.id.tv_get_phone_code)
    TextView tvGetPhoneCode;
    @BindView(R.id.tv_e9_agreement)
    TextView tvE9Agreement;
    @BindView(R.id.btn_login)
    Button btnLogin;


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PhoneCodeLoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_phone_code_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_to_password_login, R.id.btn_login,R.id.tv_get_phone_code,R.id.tv_e9_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_to_password_login:
                PasswordLoginActivity.actionStart(this);
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneCode.getText().toString())) {
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                toLogin(etAccount.getText().toString(),etPhoneCode.getText().toString());
                break;
            case R.id.tv_get_phone_code:
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (!Utils.isMobileNO(etAccount.getText().toString())) {
                    ToastUtil.showToast("手机号码格式错误");
                    return;
                }
                getPhoneCode(etAccount.getText().toString());
                break;
            case R.id.tv_e9_agreement:
                WebViewActivity.actionStart(this,"关于E9","https://www.baidu.com");
                break;
        }
    }

    private void toLogin(String phoneNum,String phoneCode) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().codeLogin()).codeLogin(phoneNum,phoneCode).enqueue(new Callback<ApiResponse<LoginResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResult>> call, Response<ApiResponse<LoginResult>> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("登录成功");
                    PreferenceUtils.getInstance().setUserToken(response.body().getData().getToken());
                    PreferenceUtils.getInstance().setLoginStatus(true);
                    PreferenceUtils.getInstance().setUserID(response.body().getData().getUser_id());
                    PreferenceUtils.getInstance().setFillInfo(response.body().getData().isFill_info());
                        MainActivity.actionStart(PhoneCodeLoginActivity.this);
                        finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:登录失败");
            }
        });
    }

    private void getPhoneCode(String phoneNum) {
        tvGetPhoneCode.setEnabled(false);
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSMS()).sendSMS(phoneNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                   TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                    time.start();// 开始计时
                    ToastUtil.showToast("验证码已发送，注意查收");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取验证码失败");
            }
        });
    }

    // /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            try {
                tvGetPhoneCode.setEnabled(false);
                tvGetPhoneCode.setClickable(false);
                tvGetPhoneCode.setText(millisUntilFinished / 1000 + "秒");
            }catch (Exception e){

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvGetPhoneCode.setEnabled(true);
                tvGetPhoneCode.setText("重新验证");
                tvGetPhoneCode.setClickable(true);
                tvGetPhoneCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode(etAccount.getText().toString());
                    }
                });
            }catch (Exception E){}

        }

    }
}
