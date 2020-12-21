package com.ocean.driver.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.entity.VehicleStatus;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/4.
 * 更新车载状态
 */
public class UpdateOnBoardStatusActivity extends Activity {
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.rb_empty)
    RadioButton rbEmpty;
    @BindView(R.id.rb_half)
    RadioButton rbHalf;
    @BindView(R.id.rb_full)
    RadioButton rbFull;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.et_weigth)
    EditText etWeigth;
    @BindView(R.id.txt_kg)
    TextView txtKg;
    @BindView(R.id.tv_last_weigth)
    TextView tvLastWeigth;
    @BindView(R.id.et_volume)
    EditText etVolume;
    @BindView(R.id.txt_volume)
    TextView txtVolume;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    private Unbinder unBinder;
    private VehicleStatus vehicleStatus;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UpdateOnBoardStatusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_on_board_status);
        unBinder = ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_empty:
                        etVolume.setText(vehicleStatus.getVehicle().getMax_volume());
                        etWeigth.setText(vehicleStatus.getVehicle().getMax_weight());
                        etVolume.setEnabled(false);
                        etWeigth.setEnabled(false);
                        break;
                    case R.id.rb_half:
                        etVolume.setEnabled(true);
                        etWeigth.setEnabled(true);
                        etVolume.setText(vehicleStatus.getVehicle().getRemain_volume());
                        etWeigth.setText(vehicleStatus.getVehicle().getRemain_weight());
                        break;
                    case R.id.rb_full:
                        etVolume.setText("0");
                        etWeigth.setText("0");
                        etVolume.setEnabled(false);
                        etWeigth.setEnabled(false);
                        break;
                }
            }
        });
    }

    private void initData() {
        HttpUtil.createRequest("UpdataOnBoardStatusActivity", BaseUrl.getInstence().getVehicleStatus()).getVehicleStatus(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<VehicleStatus>>() {
            @Override
            public void onResponse(Call<ApiResponse<VehicleStatus>> call, Response<ApiResponse<VehicleStatus>> response) {

                    if (response.body().getCode() == 1) {
                        vehicleStatus = response.body().getData();
                        switch (vehicleStatus.getVehicle().getStatus()) {
                            case "1"://空置
                                rbEmpty.setChecked(true);
                                etVolume.setEnabled(false);
                                etWeigth.setEnabled(false);
                                break;
                            case "2"://半载
                                rbHalf.setChecked(true);
                                etVolume.setEnabled(true);
                                etWeigth.setEnabled(true);
                                break;
                            case "3"://满载
                                rbFull.setChecked(true);
                                etVolume.setEnabled(false);
                                etWeigth.setEnabled(false);
                                break;
                        }
                        tvCarNum.setText(vehicleStatus.getVehicle().getNum()+"");
                        tvLastWeigth.setText("最大装载重量(kg)： "+vehicleStatus.getVehicle().getMax_weight());
                        tvVolume.setText("最大装载体积(m3)： "+vehicleStatus.getVehicle().getMax_volume()+"");
                        etWeigth.setText(vehicleStatus.getVehicle().getRemain_weight()+"");
                        etVolume.setText(vehicleStatus.getVehicle().getRemain_volume()+"");
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
            }

            @Override
            public void onFailure(Call<ApiResponse<VehicleStatus>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取车载状态失败");
            }
        });
    }

    @OnClick({R.id.btn_cancel, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_sure:
                int status=0;
                if (rbEmpty.isChecked()){
                    status=1;
                }
                if (rbHalf.isChecked()){
                    status=2;
                }
                if (rbFull.isChecked()){
                    status=3;
                }
                HttpUtil.createRequest("UpdataOnBoardStatusActivity",BaseUrl.getInstence().changeVehicleStatus()).changeVehicleStatus(PreferenceUtils.getInstance().getUserToken(),vehicleStatus.getVehicle().getVehicle_id(),status+"",etWeigth.getText().toString(),etVolume.getText().toString()).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("车载状态已更新");
                                finish();
                            } else {
                                ToastUtil.showToast(response.body().getMsg());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:获取车载状态失败");
                    }
                });
                break;
        }
    }
}
