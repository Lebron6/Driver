package com.ocean.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.callback.OnTypeSelectImp;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.SettingResult;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.TitleManger;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.view.TypeSelectWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/4.
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.txt_updata_password)
    TextView txtUpdataPassword;
    @BindView(R.id.et_id_card_num)
    EditText etIdCardNum;
    @BindView(R.id.txt_must_chose)
    TextView txtMustChose;
    @BindView(R.id.txt_wx_bind)
    TextView txtWxBind;
    @BindView(R.id.et_driver_type)
    TextView etDriverType;
    @BindView(R.id.layout_driver_level)
    RelativeLayout layoutDriverLevel;
    @BindView(R.id.txt_phone_bind)
    TextView txtPhoneBind;
    @BindView(R.id.et_driver_num)
    EditText etDriverNum;
    @BindView(R.id.txt_emergency)
    TextView txtEmergency;
    @BindView(R.id.et_emergency)
    EditText etEmergency;
    @BindView(R.id.txt_emergency_num)
    TextView txtEmergencyNum;
    @BindView(R.id.et_emergency_num)
    EditText etEmergencyNum;
    @BindView(R.id.layout_about_e9)
    LinearLayout layoutAboutE9;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.view_line)
    View viewLine;
    private ArrayAdapter brandAdapter;
    private List<String> brands;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PersonalDataActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initViews() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().settingInfo()).settinInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<SettingResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingResult>> call, Response<ApiResponse<SettingResult>> response) {
                if (response.body().getCode() == 1) {
                    etName.setText(response.body().getData().getDriver().getName());
                    etIdCardNum.setText(response.body().getData().getDriver().getId_card());
                    etDriverNum.setText(response.body().getData().getDriver().getLicense_num());
                    etEmergency.setText(response.body().getData().getDriver().getTel_name());
                    etEmergencyNum.setText(response.body().getData().getDriver().getTel_num());
                    etDriverType.setText(response.body().getData().getDriver().getType());
                    brands = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().getLicense_type().size(); i++) {
                        brands.add(response.body().getData().getLicense_type().get(i).getName());
                    }
                    brandAdapter = new ArrayAdapter(PersonalDataActivity.this, R.layout.item_type, R.id.tv_popqusetion, brands);
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
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("个人信息");
        manger.setBack();
    }
    OnTypeSelectImp typeImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            etDriverType.setText(brands.get(postion));
        }
    };

    @OnClick({R.id.btn_commit,R.id.layout_driver_level})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_driver_level:
                TypeSelectWindow brandWindow = new TypeSelectWindow(this);
                brandWindow.showView(viewLine, brandAdapter, typeImpl);
                break;
            case R.id.btn_commit:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    ToastUtil.showToast("请输入姓名");
                    return;
                }

                if (TextUtils.isEmpty(etIdCardNum.getText().toString())) {
                    ToastUtil.showToast("请输入身份证号码");
                    return;
                }
                if (TextUtils.isEmpty(etDriverType.getText().toString())) {
                    ToastUtil.showToast("请输入驾驶证类型");
                    return;
                }
                if (TextUtils.isEmpty(etDriverNum.getText().toString())) {
                    ToastUtil.showToast("请输入驾驶证号");
                    return;
                }
                if (TextUtils.isEmpty(etEmergency.getText().toString())) {
                    ToastUtil.showToast("请输入紧急联系人姓名");
                    return;
                }
                if (TextUtils.isEmpty(etEmergencyNum.getText().toString())) {
                    ToastUtil.showToast("请输入紧急联系人号码");
                    return;
                }
                commit(etName.getText().toString(), etIdCardNum.getText().toString(),
                        etDriverType.getText().toString(), etDriverNum.getText().toString(),
                        etEmergency.getText().toString(), etEmergencyNum.getText().toString());
                break;
        }
    }

    private void commit(String name, String idCardNum, String driverType, String driverNum, String emergency, String emergencyNum) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().infoSave()).infoSave(PreferenceUtils.getInstance().getUserToken(), name, idCardNum, driverType, driverNum, emergency, emergencyNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("保存成功");
                    PreferenceUtils.getInstance().setFillInfo(false);
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:保存信息失败");
            }
        });
    }

}
